/*
 * GRAKN.AI - THE KNOWLEDGE GRAPH
 * Copyright (C) 2018 Grakn Labs Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package grakn.core.common.exception;

import javax.annotation.CheckReturnValue;

/**
 * Enum containing error messages.
 *
 * Each error message contains a single format string, with a method {@link ErrorMessage#getMessage(Object...)} that
 * accepts arguments to be passed to the format string.
 *
 */
public enum ErrorMessage {
    //--------------------------------------------- Bootup Errors -----------------------------------------------
    GRAKN_PIDFILE_SYSTEM_PROPERTY_UNDEFINED("Unable to find the Java system property 'grakn.pidfile'. Don't forget to specify -Dgrakn.pidfile=/path/to/grakn.pid"),
    UNSUPPORTED_JAVA_VERSION("Unsupported Java version [%s] found. Grakn needs Java 1.8 in order to run."),
    UNABLE_TO_START_GRAKN("An error has occurred during boot-up. Please run 'grakn server status' or check the logs located under the 'logs' directory."),
    UNABLE_TO_START_SERVER_JAR_NOT_FOUND("Unable to start Server, No JAR files found! Please re-download the Grakn distribution."),
    UNABLE_TO_GET_GRAKN_HOME_FOLDER("Unable to find Grakn home folder"),
    UNABLE_TO_GET_GRAKN_CONFIG_FOLDER("Unable to find Grakn config folder"),
    UNCAUGHT_EXCEPTION("Uncaught exception at thread [%s]"),
    PID_ALREADY_EXISTS("Pid file already exists: '[%s]'. Overwriting..."),
    COULD_NOT_GET_PID("Couldn't get the PID of Grakn Server. Received '%s'"),

    //--------------------------------------------- Core Errors -----------------------------------------------
    VARIABLE_DOES_NOT_EXIST("the variable [%s] does not exist"),
    CANNOT_DELETE("Type [%s] cannot be deleted as it still has incoming edges"),
    SUPER_LOOP_DETECTED("By setting the super of concept [%s] to [%s]. You will be creating a loop. This is prohibited"),
    INVALID_UNIQUE_PROPERTY_MUTATION("Property [%s] of Concept [%s] cannot be changed to [%s] as it is already taken by Concept [%s]"),
    UNIQUE_PROPERTY_TAKEN("Property [%s] with value [%s] is already taken by concept [%s]"),
    INVALID_DATATYPE("The value [%s] of type [%s] must be of datatype [%s]"),
    INVALID_OBJECT_TYPE("The concept [%s] is not of type [%s]"),
    REGEX_INSTANCE_FAILURE("The regex [%s] of Attribute Type [%s] cannot be applied because value [%s] " +
            "does not conform to the regular expression"),
    REGEX_NOT_STRING("The Attribute Type [%s] is not of type String so it cannot support regular expressions"),
    CLOSED_CLEAR("The session for graph has been closed due to deleting the graph"),
    TRANSACTIONS_NOT_SUPPORTED("The graph backend [%s] does not actually support transactions. The transaction was not %s. The graph was actually effected directly"),
    IMMUTABLE_VALUE("The value [%s] cannot be changed to [%s] due to the property [%s] being immutable"),
    META_TYPE_IMMUTABLE("The meta type [%s] is immutable"),
    SCHEMA_LOCKED("Schema cannot be modified when using a batch loading graph"),
    HAS_INVALID("The type [%s] is not allowed to have an attribute of type [%s]"),
    BACKEND_EXCEPTION("Backend Exception."),
    INITIALIZATION_EXCEPTION("Graph for keyspace [%s] not properly initialized. Missing keyspace name resource"),
    CONNECTION_CLOSED("The connection to the database is closed"),
    TX_CLOSED("The transaction for keyspace [%s] is closed. Use the session to get a new transaction for the graph."),
    SESSION_CLOSED("The session for graph [%s] is closed. Create a new session to interact with the graph."),
    TX_CLOSED_ON_ACTION("The transaction was %s and closed for graph [%s]. Use the session to get a new transaction for the graph."),
    TXS_OPEN("Closed session on graph [%s] with [%s] open transactions"),
    LOCKING_EXCEPTION("Internal locking exception. Please clear the transaction and try again."),
    CANNOT_BE_KEY_AND_ATTRIBUTE("The Type [%s] cannot have the Attribute Type [%s] as a key and as an attribute"),
    ILLEGAL_TYPE_UNHAS_ATTRIBUTE_WITH_INSTANCE("Failed to: undefine [%s] [%s] [%s]. There exists instance of this pattern."),
    ILLEGAL_TYPE_UNHAS_ATTRIBUTE_INHERITED("Failed to: undefine [%s] [%s] [%s]. Attribute property is inherited from a super type."),
    ILLEGAL_TYPE_UNHAS_ATTRIBUTE_NOT_EXIST("Failed to: undefine [%s] [%s] [%s]. Attribute property does not exist."),
    TRANSACTION_ALREADY_OPEN("A transaction is already open on this thread for graph [%s]. Close the current transaction before opening a new one in the same thread."),
    TRANSACTION_READ_ONLY("This transaction on graph [%s] is read only"),
    IS_ABSTRACT("The Type [%s] is abstract and cannot have any instances \n"),
    CLOSE_FAILURE("Unable to close graph [%s]"),
    VERSION_MISMATCH("You are attempting to use Grakn Version [%s] with a graph build using version [%s], this is not supported."),
    NO_TYPE("Concept [%s] does not have a type"),
    INVALID_DIRECTION("Cannot traverse an edge in direction [%s]"),
    RESERVED_WORD("The word [%s] is reserved internally and cannot be used"),
    INVALID_PROPERTY_USE("The concept [%s] cannot contain vertex property [%s]"),
    UNKNOWN_CONCEPT("Unknown concept type [%s]"),
    INVALID_IMPLICIT_TYPE("Label [%s] is not an implicit label"),
    LABEL_TAKEN("The label [%s] has already been used"),
    BACKGROUND_TASK_UNHANDLED_EXCEPTION("An exception has occurred during the execution of a background task [%s]. Skipping..."),
    INVALID_KEYSPACE_NAME("Keyspace name is invalid: [%s]. Keyspace name cannot start with a number, " +
            "and can only contain maximum 48 characters of lower case, alphanumeric and underscore characters."),
    FILE_WRITE_EXCEPTION("Failed to write to file: %s"),

    //--------------------------------------------- Validation Errors
    VALIDATION("A structural validation error has occurred. Please correct the [`%s`] errors found. \n"),
    VALIDATION_RELATION_CASTING_LOOP_FAIL("The relation [%s] has a role player playing the role [%s] " +
            "which it's type [%s] is not connecting to via a relates connection \n"),
    VALIDATION_RELATION_WITH_NO_ROLE_PLAYERS("Cannot commit relation [%s] of type [%s] because it does not have any role players. \n"),

    VALIDATION_CASTING("The type [%s] of role player [%s] is not allowed to play Role [%s] \n"),
    VALIDATION_ROLE_TYPE_MISSING_RELATION_TYPE("Role [%s] does not have a relates connection to any Relation Type. \n"),
    VALIDATION_RELATION_TYPE("Relation Type [%s] does not have one or more roles \n"),

    VALIDATION_NOT_EXACTLY_ONE_KEY("Thing [%s] does not have exactly one key of type [%s]. It either has no keys assigned to it, or it has more than one. \n"),
    VALIDATION_MORE_THAN_ONE_USE_OF_KEY("There is more than one thing of type [%s] that owns the key [%s] of type [%s]. \n"),

    VALIDATION_RELATION_TYPES_ROLES_SCHEMA("The Role [%s] which is connected to Relation Type [%s] " +
            "does not have a %s Role Type which is connected to the %s Relation Type [%s] \n"),

    VALIDATION_REQUIRED_RELATION("The role player [%s] of type [%s] can only play the role of [%s] once but is currently doing so [%s] times \n"),

    //--------------------------------------------- Rule validation Errors

    VALIDATION_RULE_INVALID("The rule [%s] is not a valid rule: [%s]\n"),

    VALIDATION_RULE_NESTED_NEGATION("The rule [%s] contains a nested negation block\n"),

    VALIDATION_RULE_MULTIPLE_NEGATION_BLOCKS("The rule [%s] contains multiple negation blocks\n"),

    VALIDATION_RULE_CONTRADICTION_IN_TYPEGRAPH("The rule graph contains a contradiction - there is a positive and negative path between the types: [%s]-[%s]\n"),

    VALIDATION_RULE_GRAPH_NOT_STRATIFIABLE("The rule graph is not stratifiable - it contains following cycles with negation: [%s]\n"),

    VALIDATION_RULE_MISSING_ELEMENTS("The [%s] of rule [%s] refers to type [%s] which does not exist in the graph \n"),

    VALIDATION_RULE_DISJUNCTION_IN_BODY("The rule [%s] does not form a valid clause, as it contains a disjunction in the body\n"),

    VALIDATION_RULE_DISJUNCTION_IN_HEAD("The rule [%s] does not form a valid clause, as it contains a disjunction in the head\n"),

    VALIDATION_RULE_HEAD_NON_ATOMIC("The rule [%s] does not form a valid clause, as it contains a multi-atom head\n"),

    VALIDATION_RULE_ILLEGAL_ATOMIC_IN_HEAD("Atomic [%s] is not allowed to form a rule head of rule [%s]\n"),

    VALIDATION_RULE_ILLEGAL_HEAD_ATOM_WITH_UNBOUND_VARIABLE("Atom [%s] is not allowed to form a rule head of rule [%s] as it contains an unbound variable\n"),

    VALIDATION_RULE_ILLEGAL_HEAD_ATOM_WITH_AMBIGUOUS_SCHEMA_CONCEPT("Atom [%s] is not allowed to form a rule head of rule [%s] as it has an ambiguous schema concept\n"),

    VALIDATION_RULE_ILLEGAL_HEAD_ATOM_WITH_IMPLICIT_SCHEMA_CONCEPT("Atom [%s] is not allowed to form a rule head of rule [%s] as it has an implicit schema concept\n"),

    VALIDATION_RULE_ILLEGAL_HEAD_RELATION_WITH_IMPLICIT_ROLE("Relation [%s] is not allowed to form a rule head of rule [%s] as it has an implicit role\n"),

    VALIDATION_RULE_ILLEGAL_HEAD_RELATION_WITH_AMBIGUOUS_ROLE("Relation [%s] is not allowed to form a rule head of rule [%s] as it has an ambiguous (unspecified, variable or meta) role\n"),

    VALIDATION_RULE_ILLEGAL_HEAD_RESOURCE_WITH_AMBIGUOUS_PREDICATES("Attribute [%s] is not allowed to form a rule head of rule [%s] as it has ambiguous value predicates\n"),

    VALIDATION_RULE_ILLEGAL_HEAD_RESOURCE_WITH_NONSPECIFIC_PREDICATE("Attribute [%s] is not allowed to form a rule head of rule [%s] as it has a non-specific value predicate\n"),


    VALIDATION_RULE_INVALID_RELATION_TYPE("Rule [%s] attempts to define a relation pattern with type [%s] which is not a relation type\n"),

    VALIDATION_RULE_INVALID_ATTRIBUTE_TYPE("Rule [%s] attempts to define an attribute pattern with type [%s] which is not an attribute type\n"),

    VALIDATION_RULE_ATTRIBUTE_OWNER_CANNOT_HAVE_ATTRIBUTE("Rule [%s] attempts to define a rule containing an attribute pattern of type [%s] with type [%s] that cannot have this attribute\n"),

    VALIDATION_RULE_ROLE_CANNOT_BE_PLAYED("Rule [%s] attempts to define a rule containing a relation pattern with role [%s] which cannot be played in relation [%s]\n"),

    VALIDATION_RULE_TYPE_CANNOT_PLAY_ROLE("Rule [%s] attempts to define a rule containing a relation pattern with type [%s] that cannot play role [%s] in relation [%s]\n"),

    //--------------------------------------------- Factory Errors
    INVALID_PATH_TO_CONFIG("Unable to open config file [%s]"),
    CANNOT_PRODUCE_TX("Cannot produce a Grakn Transaction using the backend [%s]"),
    CANNOT_FIND_CLASS("The %s implementation %s must be accessible in the classpath"),

    //--------------------------------------------- Client Errors
    INVALID_SERVER_RESPONSE("Grakn Server located at [%s] returned response [%s], cannot proceed."),
    INVALID_FACTORY("Graph Factory [%s] is not valid"),
    MISSING_FACTORY_DEFINITION("Graph Factor Config ['knowledge-base.mode'] missing from provided config. " +
            "Cannot produce graph"),
    COULD_NOT_REACH_SERVER("Could not reach Grakn Server at [%s]"),

    //--------------------------------------------- Graql Errors -----------------------------------------------
    NO_TX("no graph provided"),

    SYNTAX_ERROR("syntax error at line %s: \n%s\n%s\n%s"),

    MUST_BE_ATTRIBUTE_TYPE("type '%s' must be an attribute-type"),
    ID_NOT_FOUND("id '%s' not found"),
    LABEL_NOT_FOUND("label '%s' not found"),
    NOT_A_ROLE_TYPE("'%s' is not a role type. perhaps you meant 'isa %s'?"),
    NOT_A_RELATION_TYPE("'%s' is not a relation type. perhaps you forgot to separate your statements with a ';'?"),
    NON_POSITIVE_LIMIT("limit %s should be positive"),
    NEGATIVE_OFFSET("offset %s should be non-negative"),
    INVALID_VALUE("unsupported attribute value type %s"),

    AGGREGATE_ARGUMENT_NUM("aggregate '%s' takes %s arguments, but got %s"),
    UNKNOWN_AGGREGATE("unknown aggregate '%s'"),

    MATCH_INVALID("cannot match on property of type [%s]"),
    MULTIPLE_TX("a graph has been specified twice for this query"),

    INSERT_UNDEFINED_VARIABLE("%s doesn't have an 'isa', a 'sub' or an 'id'"),
    INSERT_PREDICATE("cannot insert a concept with a predicate"),
    INSERT_RELATION_WITHOUT_ISA("cannot insert a relation without an isa edge"),
    INSERT_METATYPE("'%s' cannot be a subtype of '%s'"),
    INSERT_RECURSIVE("%s should not refer to itself"),
    INSERT_ABSTRACT_NOT_TYPE("the concept [%s] is not a type and cannot be set to abstract"),
    INSERT_RELATION_WITHOUT_ROLE_TYPE("attempted to insert a relation without all role types specified"),

    INVALID_STATEMENT("Value [%s] not of type [%s] in data [%s]"),

    //Templating
    TEMPLATE_MISSING_KEY("Key [%s] not present in data: [%s]"),

    UNEXPECTED_RESULT("the concept [%s] could not be found in results"),

    SERVER_STARTUP_ERROR("Could not start Grakn Server: [%s]"),
    UNAVAILABLE_PROPERTY("Property requested [%s] has not been defined. See configuration file [%s] for configured properties."),
    MISSING_MANDATORY_REQUEST_PARAMETERS("Missing mandatory query parameter [%s]"),
    MISSING_MANDATORY_BODY_REQUEST_PARAMETERS("Missing mandatory parameter in body [%s]"),
    MISSING_REQUEST_BODY("Empty body- it should contain the Graql query to be executed."),
    UNSUPPORTED_CONTENT_TYPE("Unsupported Content-Type [%s] requested"),
    CANNOT_DELETE_KEYSPACE("Could not delete keyspace [%s]"),

    //--------------------------------------------- Reasoner Errors -----------------------------------------------
    NON_ATOMIC_QUERY("Addressed query is not atomic: [%s]."),
    DISJUNCTIVE_NEGATION_BLOCK("Unsupported disjunction in a negation block."),
    UNSAFE_NEGATION_BLOCK("Query:\n[%s] is not negation safe - negated pattern variables are not bound."),
    USING_NEGATION_WITH_REASONING_OFF("Query [%s] contains negation blocks. Please turn the reasoning on."),
    NON_GROUND_NEQ_PREDICATE("Addressed query [%s] leads to a non-ground neq predicate when planning resolution."),
    INCOMPLETE_RESOLUTION_PLAN("Addressed query [%s] leads to an incomplete resolution plan."),
    ROLE_PATTERN_ABSENT("Addressed relation [%s] is missing a role pattern."),
    ROLE_ID_IS_NOT_ROLE("Assignment of a non-role id to a role variable in pattern [%s]."),
    NO_ATOMS_SELECTED("No atoms were selected from the query [%s]."),
    INVALID_CACHE_ENTRY("Query cache entry for query [%s] contains an invalid entry: [%s]."),
    UNIFICATION_ATOM_INCOMPATIBILITY("Attempted unification on incompatible atoms."),
    NON_EXISTENT_UNIFIER("Could not proceed with thr unification as the unifier doesn't exist."),
    ILLEGAL_ATOM_CONVERSION("Attempted illegal atom conversion of atom [%s] to type [%s]."),
    CONCEPT_NOT_THING("Attempted concept conversion from concept [%s] that is not a thing."),

    //--------------------------------------------- Analytics Errors -----------------------------------------------

    ATTRIBUTE_TYPE_NOT_SPECIFIED("No attribute type provided for compute query."),
    K_SMALLER_THAN_TWO("k can't be smaller than 2."),
    INSTANCE_DOES_NOT_EXIST("Instance does not exist in the subgraph."),
    MAX_ITERATION_REACHED("Max iteration of [%s] reached."),

    //--------------------------------------------- Shell Errors ---------------------------------------------------
    COULD_NOT_CONNECT("Could not connect to Grakn. Have you run 'grakn server start'?"),
    NO_VARIABLE_IN_QUERY("There was no variable specified in the query. Perhaps you forgot to escape `\\` a `$`?");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @CheckReturnValue
    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
