package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;

import java.util.Objects;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScEdgeImpl implements ScEdge {
    @JsonProperty("el")
    private final String element = "edge";
    @JsonProperty("src")
    private final EdgeSourceStruct source;
    @JsonIgnore
    private final ScElement sourceElement;
    @JsonProperty("trg")
    private final EdgeSourceStruct target;
    @JsonIgnore
    private final ScElement targetElement;
    @JsonProperty("type")
    private final EdgeType edgeType;
    @JsonIgnore
    private long address;

    public ScEdgeImpl(EdgeType edgeType, ScElement sourceElement, ScElement targetElement) {
        this.edgeType = edgeType;
        this.sourceElement = sourceElement;
        this.targetElement = targetElement;
        source = new EdgeSourceStruct(EdgeEndpointType.ADDR, sourceElement.getAddress());
        target = new EdgeSourceStruct(EdgeEndpointType.ADDR, targetElement.getAddress());
    }

    public ScEdgeImpl(EdgeType edgeType, ScElement sourceElement, ScElement targetElement, Long address) {
        this(edgeType, sourceElement, targetElement);
        this.address = address;
    }

    public ScEdgeImpl(EdgeType edgeType, long sourceRef, ScElement targetElement) {
        this.edgeType = edgeType;
        this.sourceElement = null;
        this.targetElement = targetElement;
        source = new EdgeSourceStruct(EdgeEndpointType.REF, sourceRef);
        target = new EdgeSourceStruct(EdgeEndpointType.ADDR, targetElement.getAddress());
    }

    public ScEdgeImpl(EdgeType edgeType, ScElement sourceElement, long targetRef) {
        this.edgeType = edgeType;
        this.sourceElement = sourceElement;
        this.targetElement = null;
        source = new EdgeSourceStruct(EdgeEndpointType.ADDR, sourceElement.getAddress());
        target = new EdgeSourceStruct(EdgeEndpointType.REF, targetRef);
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
    public int hashCode() {
        return Objects.hash(address);
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScEdgeImpl scEdge = (ScEdgeImpl) o;
        return address == scEdge.address;
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
