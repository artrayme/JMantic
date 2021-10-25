package org.jmantic.scmemory.model.impl;


import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;

/**
 * @author Michael
 */
class ScEdgeImpl implements ScEdge {
    private final EdgeType edgeType;
    private long address;
    private ScElement sourceElement;
    private ScElement targetElement;

    public ScEdgeImpl(EdgeType edgeType) {
        this.edgeType = edgeType;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public void setSourceElement(ScElement sourceElement) {
        this.sourceElement = sourceElement;
    }

    public void setTargetElement(ScElement targetElement) {
        this.targetElement = targetElement;
    }

    @Override
    public Long getAddress() {
        return address;
    }

    @Override
    public EdgeType getType() {
        return edgeType;
    }

    @Override
    public ScElement getFirst() {
        return sourceElement;
    }

    @Override
    public ScElement getSecond() {
        return targetElement;
    }
}
