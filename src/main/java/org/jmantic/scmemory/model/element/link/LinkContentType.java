package org.jmantic.scmemory.model.element.link;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author artrayme
 * @since 0.0.1
 */
public enum LinkContentType {
    INT("int"),
    FLOAT("float"),
    STRING("string"),
    BINARY("binary");

    @JsonValue
    private final String type;

    LinkContentType(String type) {
        this.type = type;
    }
}
