package org.jmantic.scmemory.model.pattern;

import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;

public interface ScPattern5<T1 extends ScElement, T3, T5, expectedT3 extends ScElement, expectedT5 extends ScElement> {
    T1 get1();

    void set1(T1 element);

    EdgeType get2();

    void set2(EdgeType edgeType);

    T3 get3();

    void set3(T3 element);

    EdgeType get4();

    void set4(EdgeType edgeType);

    T5 get5();

    void set5(T5 element);


}
