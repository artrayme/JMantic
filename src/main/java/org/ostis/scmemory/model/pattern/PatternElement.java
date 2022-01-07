package org.ostis.scmemory.model.pattern;

public enum PatternElement {
    ALIAS("alias"),
    TYPE("type"),
    ADDR("addr");


    private final String type;

    PatternElement(String type) {
        this.type = type;
    }
}
