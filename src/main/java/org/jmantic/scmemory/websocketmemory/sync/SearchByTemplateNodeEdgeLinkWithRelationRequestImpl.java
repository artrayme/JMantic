package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.websocketmemory.message.request.RequestType;
import org.jmantic.scmemory.websocketmemory.message.request.SearchByTemplateRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.3.0
 */
@Deprecated(since = "0.3.2", forRemoval = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class SearchByTemplateNodeEdgeLinkWithRelationRequestImpl extends AbstractScRequest implements SearchByTemplateRequest {
    @JsonProperty("payload")
    private List<Object> struct = new ArrayList<>();

    public SearchByTemplateNodeEdgeLinkWithRelationRequestImpl(ScElement fixed,
                                                               EdgeType edgeType,
                                                               LinkType linkType,
                                                               ScElement relation,
                                                               EdgeType relationNodeType) {
        super(1, RequestType.SEARCH_TEMPLATE);
        String aliasName = "edge_alias";
        struct.add(new NodeEdgeLinkStruct(fixed, edgeType, linkType, aliasName));
        struct.add(new RelationStruct(relation, relationNodeType, aliasName));
    }

    @JsonIgnore
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "SearchByTemplateNodeEdgeLinkWithRelationRequestImpl{" +
                "struct=" + struct +
                '}';
    }

    /**
     * @author artrayme
     * @since 0.3.0
     */
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static
    class NodeEdgeLinkStruct {

        @JsonValue
        private final Object[] components = new Object[3];

        public NodeEdgeLinkStruct(ScElement fixedNode, EdgeType edgeType, LinkType linkType, String aliasForEdge) {
            components[0] = new FixedComponent(fixedNode.getAddress());
            components[1] = new EdgeComponent(edgeType, aliasForEdge);
            components[2] = new LinkComponent(linkType);
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
            @JsonProperty("alias")
            private final String alias;

            private EdgeComponent(EdgeType edgeType, String alias) {
                this.edgeType = edgeType;
                this.alias = alias;
            }

            @Override
            public String toString() {
                return "EdgeComponent{" +
                        "type='" + type + '\'' +
                        ", edgeType=" + edgeType +
                        ", alias='" + alias + '\'' +
                        '}';
            }
        }

        private static class LinkComponent {
            @JsonProperty("type")
            private final String type = "type";
            @JsonProperty("value")
            private final LinkType linkType;

            private LinkComponent(LinkType linkType) {
                this.linkType = linkType;
            }

            @Override
            public String toString() {
                return "LinkComponent{" +
                        "type='" + type + '\'' +
                        ", linkType=" + linkType +
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

    /**
     * @author artrayme
     * @since 0.3.0
     */
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static
    class RelationStruct {

        @JsonValue
        private final Object[] components = new Object[3];

        public RelationStruct(ScElement fixedNode, EdgeType edgeType, String alias) {
            components[0] = new FixedComponent(fixedNode.getAddress());
            components[1] = new EdgeComponent(edgeType);
            components[2] = new AliasComponent(alias);
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

        private static class AliasComponent {
            @JsonProperty("type")
            private final String type = "alias";
            @JsonProperty("value")
            private final String alias;

            private AliasComponent(String alias) {
                this.alias = alias;
            }

            @Override
            public String toString() {
                return "AliasesComponent{" +
                        "type='" + type + '\'' +
                        ", alias='" + alias + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "RelationStruct{" +
                    "components=" + Arrays.toString(components) +
                    '}';
        }
    }

}
