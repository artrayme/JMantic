package org.ostis.scmemory.model.element;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UnknownScElement {
    ELEMENT(0);

    @JsonValue
    private final int code;

    UnknownScElement(int i) {
        code = i;
    }
}
