package org.jmantic.scmemory.websocketmemory.sync.pattern.element;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.ScElement;

/**
 * @author artrayme
 * @since 0.3.2
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class FixedPatternElement implements PatternElement {
    @JsonProperty("type")
    private final String type = "addr";
    @JsonProperty("value")
    private final Long fixedNode;

    public FixedPatternElement(ScElement element) {
        this.fixedNode = element.getAddress();
    }

}




