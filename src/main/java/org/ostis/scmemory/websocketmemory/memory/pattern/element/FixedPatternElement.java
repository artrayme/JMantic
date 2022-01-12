package org.ostis.scmemory.websocketmemory.memory.pattern.element;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.pattern.ScFixedElement;

/**
 * @author artrayme
 * @since 0.3.2
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class FixedPatternElement implements ScFixedElement {
    @JsonProperty("type")
    private final String type = "addr";
    @JsonProperty("value")
    private final Long fixedNode;
    @JsonIgnore
    private final ScElement element;

    public FixedPatternElement(ScElement element) {
        this.element = element;
        this.fixedNode = element.getAddress();
    }

    @JsonIgnore
    @Override
    public ScElement getElement() {
        return element;
    }
}




