package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * @author artrayme
 * @since 0.2.0
 */

enum EdgeEndpointType {
    ADDR("addr"),
    REF("ref");

    @JsonValue
    private final String type;

    EdgeEndpointType(String type) {
        this.type = type;
    }
}

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
abstract class ScEntity implements ScElement {
    @JsonProperty("el")
    private final String element;

    @JsonIgnore
    private long address;

    public ScEntity(String element) {
        this.element = element;
    }

    public ScEntity(String element, long address) {
        this.element = element;
        this.address = address;
    }

    @JsonIgnore
    public String getElement() {
        return element;
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
}

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScNodeImpl extends ScEntity implements ScNode {
    @JsonProperty("type")
    private final NodeType nodeType;

    public ScNodeImpl(NodeType nodeType) {
        super("node");
        this.nodeType = nodeType;
    }

    public ScNodeImpl(NodeType nodeType, Long address) {
        super("node", address);
        this.nodeType = nodeType;
    }

    @JsonIgnore
    @Override
    public NodeType getType() {
        return nodeType;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(getAddress());
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScNodeImpl scNode = (ScNodeImpl) o;
        return Objects.equals(getAddress(), scNode.getAddress());
    }
}

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScLinkFloatImpl extends ScEntity implements ScLinkFloat {
    @JsonProperty("type")
    private final LinkType linkType;
    @JsonProperty("content_type")
    private final String contentType = "float";
    @JsonProperty("content")
    private float content;

    public ScLinkFloatImpl(LinkType linkType) {
        super("link");
        this.linkType = linkType;
    }

    public ScLinkFloatImpl(LinkType linkType, Long address) {
        super("link", address);
        this.linkType = linkType;
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
    public void setContent(float content) {
        this.content = content;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(getAddress());
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScLinkFloatImpl that = (ScLinkFloatImpl) o;
        return Objects.equals(getAddress(), that.getAddress());
    }

}

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScLinkIntegerImpl extends ScEntity implements ScLinkInteger {
    @JsonProperty("type")
    private final LinkType linkType;
    @JsonProperty("content_type")
    private final String contentType = "int";
    @JsonProperty("content")
    private int content;

    public ScLinkIntegerImpl(LinkType linkType) {
        super("link");
        this.linkType = linkType;
    }

    public ScLinkIntegerImpl(LinkType linkType, Long address) {
        super("link", address);
        this.linkType = linkType;
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
    public void setContent(int content) {
        this.content = content;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(getAddress());
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScLinkIntegerImpl that = (ScLinkIntegerImpl) o;
        return Objects.equals(getAddress(), that.getAddress());
    }

}

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScLinkStringImpl extends ScEntity implements ScLinkString {
    @JsonProperty("type")
    private final LinkType linkType;
    @JsonProperty("content_type")
    private final String contentType = "string";
    @JsonProperty("content")
    private String content;

    public ScLinkStringImpl(LinkType linkType) {
        super("link");
        this.linkType = linkType;
    }

    public ScLinkStringImpl(LinkType linkType, Long address) {
        super("link", address);
        this.linkType = linkType;
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
    public void setContent(String content) {
        this.content = content;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(getAddress());
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScLinkStringImpl that = (ScLinkStringImpl) o;
        return Objects.equals(getAddress(), that.getAddress());
    }

}

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScEdgeImpl extends ScEntity implements ScEdge {
    @JsonProperty("src")
    private EdgeSourceStruct source;
    @JsonIgnore
    private ScElement sourceElement;
    @JsonProperty("trg")
    private EdgeSourceStruct target;
    @JsonIgnore
    private ScElement targetElement;
    @JsonProperty("type")
    private EdgeType edgeType;

    public ScEdgeImpl(EdgeType edgeType, ScElement sourceElement, ScElement targetElement) {
        super("edge");
        this.edgeType = edgeType;
        this.sourceElement = sourceElement;
        this.targetElement = targetElement;
        source = new EdgeSourceStruct(EdgeEndpointType.ADDR, sourceElement.getAddress());
        target = new EdgeSourceStruct(EdgeEndpointType.ADDR, targetElement.getAddress());
    }

    public ScEdgeImpl(EdgeType edgeType, ScElement sourceElement, ScElement targetElement, Long address) {
        this(edgeType, sourceElement, targetElement);
        setAddress(address);
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

    public void setSourceElement(ScElement sourceElement) {
        this.sourceElement = sourceElement;
        source = new EdgeSourceStruct(EdgeEndpointType.ADDR, sourceElement.getAddress());
    }

    public void setTargetElement(ScElement targetElement) {
        this.targetElement = targetElement;
        target = new EdgeSourceStruct(EdgeEndpointType.ADDR, targetElement.getAddress());
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(getAddress());
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScEdgeImpl scEdge = (ScEdgeImpl) o;
        return Objects.equals(getAddress(), scEdge.getAddress());
    }

}

/**
 * @author artrayme
 * @since 0.2.0
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class EdgeSourceStruct {
    @JsonProperty("type")
    private final EdgeEndpointType type;
    @JsonProperty("value")
    private final Long value;

    public EdgeSourceStruct(EdgeEndpointType type, Long value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EdgeSourceStruct that = (EdgeSourceStruct) o;
        return type == that.type && Objects.equals(value, that.value);
    }

}