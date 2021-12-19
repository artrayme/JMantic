package org.jmantic.scmemory.model.pattern;

import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.ScEdge;

public interface ScConstruction5<T1 extends ScElement, T3 extends ScElement, T5 extends ScElement> {
    T1 get1();

    ScEdge get2();

    T3 get3();

    ScEdge get4();

    T5 get5();
}
