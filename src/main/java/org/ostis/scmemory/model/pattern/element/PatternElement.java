package org.ostis.scmemory.model.pattern.element;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum that make working with {@link org.ostis.scmemory.model.pattern.ScPattern} easier.
 * Also, it used at requests serialization.
 *
 * @author artrayme
 * @since 0.6.0
 */

public enum PatternElement {
    ALIAS("alias"), TYPE("type"), ADDR("addr");

    @JsonValue
    private final String type;

    PatternElement(String type) {
        this.type = type;
    }
}
