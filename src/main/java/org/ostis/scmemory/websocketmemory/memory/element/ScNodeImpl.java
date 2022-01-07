package org.ostis.scmemory.websocketmemory.memory.element;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;

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

