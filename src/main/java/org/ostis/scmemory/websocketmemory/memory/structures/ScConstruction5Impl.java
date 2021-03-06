package org.ostis.scmemory.websocketmemory.memory.structures;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.pattern.pattern5.ScConstruction5;

/**
 * @author artrayme
 * @since 0.3.2
 */
public class ScConstruction5Impl<T1 extends ScElement, T3 extends ScElement, T5 extends ScElement> implements ScConstruction5<T1, T3, T5> {
    private T1 element1;
    private ScEdge edge2;
    private T3 element3;
    private ScEdge edge4;
    private T5 element5;

    public ScConstruction5Impl(T1 element1, ScEdge edge2, T3 element3, ScEdge edge4, T5 element5) {
        this.element1 = element1;
        this.edge2 = edge2;
        this.element3 = element3;
        this.edge4 = edge4;
        this.element5 = element5;
    }

    @Override
    public T1 get1() {
        return element1;
    }

    @Override
    public ScEdge get2() {
        return edge2;
    }

    @Override
    public T3 get3() {
        return element3;
    }

    @Override
    public ScEdge get4() {
        return edge4;
    }

    @Override
    public T5 get5() {
        return element5;
    }

}
