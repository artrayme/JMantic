package org.jmantic.scmemory.model.element.link;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author artrayme
 * 11/5/21
 */
public enum LinkContentType {
    INTEGER("int"),
    FLOAT("float"),
    STRING("string"),
    BINARY("binary");

    @JsonValue
    private final String type;

    LinkContentType(String type) {
        this.type = type;
    }
}
