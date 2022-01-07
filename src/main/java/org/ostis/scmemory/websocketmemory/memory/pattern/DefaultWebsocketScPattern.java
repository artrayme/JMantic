package org.ostis.scmemory.websocketmemory.memory.pattern;

import org.ostis.scmemory.model.pattern.ScPattern;
import org.ostis.scmemory.model.pattern.ScPatternTriplet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DefaultWebsocketScPattern implements ScPattern {
    private final List<ScPatternTriplet> triplets = new ArrayList<>();

    @Override
    public Stream<ScPatternTriplet> getElements() {
        return triplets.stream();
    }

    @Override
    public boolean addElement(ScPatternTriplet element) {
        return triplets.add(element);
    }

    @Override
    public boolean removeElement(ScPatternTriplet element) {
        return triplets.remove(element);
    }
}
