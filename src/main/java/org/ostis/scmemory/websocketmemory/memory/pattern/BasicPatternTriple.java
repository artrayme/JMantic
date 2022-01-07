package org.ostis.scmemory.websocketmemory.memory.pattern;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import org.ostis.scmemory.model.pattern.ScPatternElement;
import org.ostis.scmemory.model.pattern.ScPatternTriplet;

/**
 * @author artrayme
 * @since 0.3.2
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BasicPatternTriple implements ScPatternTriplet {
    @JsonValue
    private final ScPatternElement[] elements = new ScPatternElement[3];

    public BasicPatternTriple(ScPatternElement element1,
                              ScPatternElement element2,
                              ScPatternElement element3) {
        elements[0] = element1;
        elements[1] = element2;
        elements[2] = element3;
    }

    public void set1(ScPatternElement element) {
        elements[0] = element;
    }

    public void set2(ScPatternElement element) {
        elements[1] = element;
    }

    public void set3(ScPatternElement element) {
        elements[2] = element;
    }

    @JsonIgnore
    public ScPatternElement[] getElements() {
        return elements;
    }

    @Override
    public ScPatternElement get1() {
        return elements[0];
    }

    @Override
    public ScPatternElement get2() {
        return elements[1];
    }

    @Override
    public ScPatternElement get3() {
        return elements[2];
    }
}
