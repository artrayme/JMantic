package org.jmantic.scmemory.model.websocket.request;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Michael
 */
public enum RequestType {
    CREATE_ELEMENTS("create_elements"),
    CHECK_ELEMENTS("check_elements"),
    DELETE_ELEMENTS("delete_elements"),
    SEARCH_TEMPLATE("search_elements"),
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
