package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author artrayme
 * @since 0.3.2
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BasicPatternTriple {
    @JsonValue
    private final PatternElement[] elements = new PatternElement[3];

    public BasicPatternTriple(PatternElement element1,
                              PatternElement element2,
                              PatternElement element3) {
        elements[0] = element1;
        elements[1] = element2;
        elements[2] = element3;
    }

    public void setFirst(PatternElement element) {
        elements[0] = element;
    }

    public void setSecond(PatternElement element) {
        elements[1] = element;
    }

    public void setThird(PatternElement element) {
        elements[2] = element;
    }

    @JsonIgnore
    public PatternElement[] getElements() {
        return elements;
    }
}
