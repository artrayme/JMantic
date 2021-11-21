package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.node.NodeType;

import java.util.Arrays;

/**
 * @author artrayme
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class NodeEdgeNodeStruct {

    @JsonValue
    private final Object[] components = new Object[3];

    public NodeEdgeNodeStruct(ScElement fixedNode, EdgeType edgeType, NodeType nodeType) {
        components[0] = new FixedComponent(fixedNode.getAddress());
        components[1] = new EdgeComponent(edgeType);
        components[2] = new NodeComponent(nodeType);
    }

    private static class FixedComponent {
        @JsonProperty("type")
        private final String type = "addr";
        @JsonProperty("value")
        private final Long fixedNode;

        private FixedComponent(Long fixedNode) {
            this.fixedNode = fixedNode;
        }

        @Override
        public String toString() {
            return "FixedComponent{" +
                    "type='" + type + '\'' +
                    ", fixedNode=" + fixedNode +
                    '}';
        }
    }

    private static class EdgeComponent {
        @JsonProperty("type")
        private final String type = "type";
        @JsonProperty("value")
        private final EdgeType edgeType;

        private EdgeComponent(EdgeType edgeType) {
            this.edgeType = edgeType;
        }

        @Override
        public String toString() {
            return "EdgeComponent{" +
                    "type='" + type + '\'' +
                    ", edgeType=" + edgeType +
                    '}';
        }
    }

    private static class NodeComponent {
        @JsonProperty("type")
        private final String type = "type";
        @JsonProperty("value")
        private final NodeType nodeType;

        private NodeComponent(NodeType nodeType) {
            this.nodeType = nodeType;
        }

        @Override
        public String toString() {
            return "NodeComponent{" +
                    "type='" + type + '\'' +
                    ", nodeType=" + nodeType +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NodeEdgeNodeStruct{" +
                "components=" + Arrays.toString(components) +
                '}';
    }
}
