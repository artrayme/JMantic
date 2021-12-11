package org.jmantic.scmemory.websocketmemory.message.request;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum of supported {@link ScRequest} types
 *
 * @author Michael
 * @since 0.0.1
 */
public enum RequestType {
    CREATE_ELEMENTS("create_elements"),
    CHECK_ELEMENTS("check_elements"),
    DELETE_ELEMENTS("delete_elements"),
    SEARCH_TEMPLATE("search_template"),
    GENERATE_TEMPLATE("generate_template"),
    EVENTS("events"),
    KEYNODES("keynodes"),
    CONTENT("content");

    @JsonValue
    private final String type;

    RequestType(String type) {
        this.type = type;
    }
}
