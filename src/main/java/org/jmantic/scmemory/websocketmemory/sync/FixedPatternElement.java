package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.pattern.PatternElement;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class FixedPatternElement implements PatternElement {
    @JsonProperty("type")
    private final String type = "addr";
    @JsonProperty("value")
    private final Long fixedNode;

    FixedPatternElement(ScElement element) {
        this.fixedNode = element.getAddress();
    }

}




