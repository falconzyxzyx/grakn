/*
 * Grakn - A Distributed Semantic Database
 * Copyright (C) 2016  Grakn Labs Limited
 *
 * Grakn is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Grakn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Grakn. If not, see <http://www.gnu.org/licenses/gpl.txt>.
 */

package ai.grakn.generator;

import ai.grakn.engine.GraknEngineConfig;
import ai.grakn.engine.factory.EngineGraknGraphFactory;
import ai.grakn.engine.lock.GenericLockProvider;
import ai.grakn.engine.lock.LockProvider;
import ai.grakn.engine.tasks.connection.RedisCountStorage;
import ai.grakn.engine.tasks.manager.TaskManager;
import ai.grakn.engine.tasks.manager.redisqueue.RedisTaskManager;
import ai.grakn.engine.util.EngineID;
import com.codahale.metrics.MetricRegistry;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * TaskManagers
 *
 * @author alexandraorth
 */
public class TaskManagers extends Generator<TaskManager> {

    private static final Logger LOG = LoggerFactory.getLogger(TaskManagers.class);

    // TODO TEMP FOR TESTING
    @SuppressWarnings("unchecked")
    private Class<? extends TaskManager>[] taskManagerClasses = new Class[]{
            RedisTaskManager.class, RedisTaskManager.class
    };

    private static Map<Class<? extends TaskManager>, TaskManager> taskManagers = new HashMap<>();

    public static void closeAndClear() {
        taskManagers.values().forEach(TaskManager::close);
        taskManagers.clear();
    }

    public TaskManagers() {
        super(TaskManager.class);
    }

    @Override
    public TaskManager generate(SourceOfRandomness random, GenerationStatus status) {
        // TODO restore the use of taskManagerClasses
        Class<? extends TaskManager> taskManagerToReturn = RedisTaskManager.class;

        GraknEngineConfig config = GraknEngineConfig.create();
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig,
                config.getProperty(GraknEngineConfig.REDIS_SERVER_URL),
                Integer.parseInt(config.getProperty(GraknEngineConfig.REDIS_SERVER_PORT)));
        RedisCountStorage redisCountStorage = RedisCountStorage.create(jedisPool);
        if (!taskManagers.containsKey(taskManagerToReturn)) {
            try {
                Constructor<? extends TaskManager> constructor =
                        taskManagerToReturn.getConstructor(EngineID.class, GraknEngineConfig.class,
                                RedisCountStorage.class, EngineGraknGraphFactory.class,
                                LockProvider.class, MetricRegistry.class);
                // TODO this doesn't take a Redis connection. Make sure this is what we expect
                taskManagers.put(taskManagerToReturn,
                        constructor.newInstance(EngineID.me(), config, redisCountStorage, null,
                                new GenericLockProvider(), new MetricRegistry()));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.error("Could not instantiate task manager", e);
                throw new RuntimeException(e);
            }
        }

        return taskManagers.get(taskManagerToReturn);
    }
}
