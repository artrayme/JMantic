package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.websocketmemory.message.ScMemoryView;

/**
 * @author Michael
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScNodeImpl implements ScNode {
    @JsonView(ScMemoryView.Request.class)
    private final String el = "node";
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("type")
    private final NodeType nodeType;
    @JsonView(ScMemoryView.Address.class)
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


    @JsonIgnore
    @Override
    public String toString() {
        return "ScNodeImpl{" +
                "el='" + el + '\'' +
                ", nodeType=" + nodeType +
                ", address=" + address +
                '}';
    }
}
