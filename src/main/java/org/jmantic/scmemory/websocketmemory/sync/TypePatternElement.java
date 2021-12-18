package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.pattern.PatternElement;

class TypePatternElement<T> implements PatternElement {
    @JsonProperty("type")
    private final String type = "type";
    @JsonProperty("value")
    private final T scElementType;
//    @JsonProperty("alias")
//    private final String alias;

    TypePatternElement(T type) {
        this.scElementType = type;
    }

}
