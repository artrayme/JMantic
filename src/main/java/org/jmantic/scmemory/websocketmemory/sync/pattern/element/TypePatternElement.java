package org.jmantic.scmemory.websocketmemory.sync.pattern.element;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author artrayme
 * @since 0.3.2
 */
public final class TypePatternElement<T> implements PatternElement {
    @JsonProperty("type")
    private final String type = "type";
    @JsonProperty("value")
    private final T scElementType;
    @JsonProperty("alias")
    private final String alias;

    public TypePatternElement(T type, String alias) {
        this.scElementType = type;
        this.alias = alias;
    }

}
