package org.ostis.scmemory.websocketmemory.memory.structures;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.pattern.ScConstruction3;

/**
 * @author artrayme
 * @since 0.3.2
 */
public class ScConstruction3Impl<T1 extends ScElement, T3 extends ScElement> implements ScConstruction3<T1, T3> {
    private T1 element1;
    private ScEdge edge;
    private T3 element3;

    public ScConstruction3Impl() {
    }

    public ScConstruction3Impl(T1 element1, ScEdge edge, T3 element3) {
        this.element1 = element1;
        this.edge = edge;
        this.element3 = element3;
    }

    @Override
    public T1 get1() {
        return element1;
    }

    @Override
    public ScEdge getEdge() {
        return edge;
    }

    @Override
    public T3 get3() {
        return element3;
    }

    public void setElement1(T1 element1) {
        this.element1 = element1;
    }

    public void setEdge(ScEdge edge) {
        this.edge = edge;
    }

    public void setElement3(T3 element3) {
        this.element3 = element3;
    }

}
