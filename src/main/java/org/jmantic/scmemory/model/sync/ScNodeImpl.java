package org.jmantic.scmemory.model.sync;

import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;

/**
 * @author Michael
 */
class ScNodeImpl implements ScNode {
    private final NodeType nodeType;
    private long address;

    public ScNodeImpl(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    @Override
    public Long getAddress() {
        return address;
    }

    @Override
    public NodeType getType() {
        return nodeType;
    }
}
