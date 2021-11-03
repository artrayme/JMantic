package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.websocketmemory.message.ScMemoryView;

import java.util.Objects;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScNodeImpl implements ScNode {
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("el")
    private final String element = "node";

    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("type")
    private final NodeType nodeType;

    @JsonView(ScMemoryView.Address.class)
    private long address;

    public ScNodeImpl(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @JsonIgnore
    public void setAddress(long address) {
        this.address = address;
    }

    @JsonIgnore
    @Override
    public Long getAddress() {
        return address;
    }

    @JsonIgnore
    @Override
    public NodeType getType() {
        return nodeType;
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "ScNodeImpl{" +
                "el='" + element + '\'' +
                ", nodeType=" + nodeType +
                ", address=" + address +
                '}';
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScNodeImpl scNode = (ScNodeImpl) o;
        return address == scNode.address;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
