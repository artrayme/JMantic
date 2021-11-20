package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;

import java.util.Objects;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScNodeImpl implements ScNode {
    @JsonProperty("el")
    private final String element = "node";

    @JsonProperty("type")
    private final NodeType nodeType;

    @JsonIgnore
    private long address;

    public ScNodeImpl(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public ScNodeImpl(NodeType nodeType, Long address) {
        this.nodeType = nodeType;
        this.address = address;
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

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScLinkFloatImpl implements ScLinkFloat {
    @JsonProperty("el")
    private final String element = "link";

    @JsonProperty("type")
    private final LinkType linkType;

    @JsonProperty("content")
    private float content;

    @JsonProperty("content_type")
    private final String contentType = "float";

    @JsonIgnore
    private long address;

    public ScLinkFloatImpl(LinkType linkType) {
        this.linkType = linkType;
    }

    @JsonIgnore
    public void setAddress(long address) {
        this.address = address;
    }

    @JsonIgnore
    public void setContent(float content) {
        this.content = content;
    }

    @JsonIgnore
    @Override
    public Long getAddress() {
        return address;
    }

    @JsonIgnore
    @Override
    public LinkType getType() {
        return linkType;
    }

    @JsonIgnore
    @Override
    public float getContent() {
        return content;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScLinkFloatImpl that = (ScLinkFloatImpl) o;
        return address == that.address;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "ScLinkFloatImpl{" +
                "element='" + element + '\'' +
                ", linkType=" + linkType +
                ", content=" + content +
                ", contentType='" + contentType + '\'' +
                ", address=" + address +
                '}';
    }
}


/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScLinkIntegerImpl implements ScLinkInteger {
    @JsonProperty("el")
    private final String element = "link";

    @JsonProperty("type")
    private final LinkType linkType;

    @JsonProperty("content")
    private int content;

    @JsonProperty("content_type")
    private final String contentType = "int";

    @JsonIgnore
    private long address;

    public ScLinkIntegerImpl(LinkType linkType) {
        this.linkType = linkType;
    }

    @JsonIgnore
    public void setAddress(long address) {
        this.address = address;
    }

    @JsonIgnore
    public void setContent(int content) {
        this.content = content;
    }

    @JsonIgnore
    @Override
    public Long getAddress() {
        return address;
    }

    @JsonIgnore
    @Override
    public LinkType getType() {
        return linkType;
    }

    @JsonIgnore
    @Override
    public int getContent() {
        return content;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScLinkIntegerImpl that = (ScLinkIntegerImpl) o;
        return address == that.address;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "ScLinkIntegerImpl{" +
                "element='" + element + '\'' +
                ", linkType=" + linkType +
                ", content=" + content +
                ", contentType='" + contentType + '\'' +
                ", address=" + address +
                '}';
    }
}

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScLinkStringImpl implements ScLinkString {
    @JsonProperty("el")
    private final String element = "link";

    @JsonProperty("type")
    private final LinkType linkType;

    @JsonProperty("content")
    private String content;

    @JsonProperty("content_type")
    private final String contentType = "string";

    @JsonIgnore
    private long address;

    public ScLinkStringImpl(LinkType linkType) {
        this.linkType = linkType;
    }

    @JsonIgnore
    public void setAddress(long address) {
        this.address = address;
    }

    @JsonIgnore
    public void setContent(String content) {
        this.content = content;
    }

    @JsonIgnore
    @Override
    public Long getAddress() {
        return address;
    }

    @JsonIgnore
    @Override
    public LinkType getType() {
        return linkType;
    }

    @JsonIgnore
    @Override
    public String getContent() {
        return content;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScLinkStringImpl that = (ScLinkStringImpl) o;
        return address == that.address;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "ScLinkStringImpl{" +
                "el='" + element + '\'' +
                ", linkType=" + linkType +
                ", content='" + content + '\'' +
                ", contentType='" + contentType + '\'' +
                ", address=" + address +
                '}';
    }
}

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