package org.jmantic.scmemory.websocketmemory.sync;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.websocketmemory.message.ScMemoryView;

/**
 * @author Michael
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScEdgeImpl implements ScEdge {
    @JsonProperty("el")
    @JsonView(ScMemoryView.Request.class)
    private final String element = "edge";

    @JsonView(ScMemoryView.Address.class)
    private long address;

    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("src")
    private ScElement sourceElement;
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("trg")
    private ScElement targetElement;

    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("type")
    private final EdgeType edgeType;

    public ScEdgeImpl(EdgeType edgeType, ScElement sourceElement, ScElement targetElement) {
        this.edgeType = edgeType;
        this.sourceElement = sourceElement;
        this.targetElement = targetElement;
    }

    @JsonIgnore
    @Override
    public Long getAddress() {
        return address;
    }

    @JsonIgnore
    public void setAddress(long address) {
        this.address = address;
    }

    @JsonIgnore
    @Override
    public EdgeType getType() {
        return edgeType;
    }

    @JsonIgnore
    @Override
    public ScElement getSource() {
        return sourceElement;
    }

    @JsonIgnore
    @Override
    public ScElement getTarget() {
        return targetElement;
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "ScEdgeImpl{" +
                "el='" + element + '\'' +
                ", edgeType=" + edgeType +
                ", address=" + address +
                ", sourceElement=" + sourceElement +
                ", targetElement=" + targetElement +
                '}';
    }
}
