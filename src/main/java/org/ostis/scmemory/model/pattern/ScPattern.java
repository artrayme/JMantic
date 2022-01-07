package org.ostis.scmemory.model.pattern;

import java.util.List;
import java.util.stream.Stream;

public interface ScPattern {
    Stream<ScPatternTriplet> getElements();
    boolean addElement(ScPatternTriplet element);
    boolean removeElement(ScPatternTriplet element);
}
