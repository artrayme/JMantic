package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.websocketmemory.message.request.RequestType;
import org.jmantic.scmemory.websocketmemory.message.request.SearchByTemplateRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author artrayme
 * @since 0.0.1
 */
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
}
