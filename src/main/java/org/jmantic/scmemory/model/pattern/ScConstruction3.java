package org.jmantic.scmemory.model.pattern;

import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.ScEdge;

public interface ScConstruction3<T1 extends ScElement, T3 extends ScElement> {
    T1 get1();

    ScEdge getEdge();

    T3 get3();


}
