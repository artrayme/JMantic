package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.websocketmemory.message.request.RequestType;
import org.jmantic.scmemory.websocketmemory.message.request.SearchByTemplateRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author artrayme
 * @since 0.0.1
 */
@Deprecated(since = "0.3.2", forRemoval = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class SearchByTemplateNodeEdgeNodeRequestImpl extends AbstractScRequest implements SearchByTemplateRequest {
    @JsonProperty("payload")
    private List<NodeEdgeNodeStruct> struct = new ArrayList<>();

    public SearchByTemplateNodeEdgeNodeRequestImpl(ScElement fixed, EdgeType edgeType, NodeType nodeType) {
        super(1, RequestType.SEARCH_TEMPLATE);
        struct.add(new NodeEdgeNodeStruct(fixed, edgeType, nodeType));
    }

    @JsonIgnore
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "SearchByTemplateNodeEdgeNodeRequestImpl{" +
                "id=" + getRequestId() +
                ", searchTemplate=" + getRequestType() +
                ", struct=" + struct +
                '}';
    }

    /**
     * @author artrayme
     * @since 0.0.1
     */
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static
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
}
