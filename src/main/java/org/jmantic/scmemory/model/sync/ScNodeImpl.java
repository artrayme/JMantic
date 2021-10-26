package org.jmantic.scmemory.model.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;

/**
 * @author Michael
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScNodeImpl implements ScNode {
    private final String el = "node";
    @JsonProperty("type")
    private final NodeType nodeType;
    @JsonProperty("addr")
    @JsonUnwrapped
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
