package org.ostis.scmemory.model.pattern;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PatternElement {
    ALIAS("alias"),
    TYPE("type"),
    ADDR("addr");

    @JsonValue
    private final String type;

    PatternElement(String type) {
        this.type = type;
    }
}
