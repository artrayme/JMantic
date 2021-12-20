package org.jmantic.scmemory.websocketmemory.sync.element;

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
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ScNodeImpl extends ScEntity implements ScNode {
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

}

