package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.*;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.websocketmemory.message.ScMemoryView;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScEdgeImpl implements ScEdge {
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("el")
    private final String element = "edge";

    @JsonView(ScMemoryView.Address.class)
    private long address;

    @JsonView(ScMemoryView.Request.class)
    @JsonRawValue
    @JsonProperty("src")
    private final String source;

    @JsonIgnore
    private final ScElement sourceElement;

    @JsonView(ScMemoryView.Request.class)
    @JsonRawValue
    @JsonProperty("trg")
    private final String target;

    @JsonIgnore
    private final ScElement targetElement;

    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("type")
    private final EdgeType edgeType;

    public ScEdgeImpl(EdgeType edgeType, ScElement sourceElement, ScElement targetElement) {
        this.edgeType = edgeType;
        this.sourceElement = sourceElement;
        this.targetElement = targetElement;
        source = "{\"type\": \"addr\",\"value\":" + sourceElement.getAddress() + "}";
        target = "{\"type\": \"addr\",\"value\":" + targetElement.getAddress() + "}";
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
