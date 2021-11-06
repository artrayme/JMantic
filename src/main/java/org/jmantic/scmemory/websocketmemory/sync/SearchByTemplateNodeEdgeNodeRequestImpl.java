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
 * 11/6/21
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SearchByTemplateNodeEdgeNodeRequestImpl implements SearchByTemplateRequest {
    @JsonProperty("id")
    private Long id = 1L;
    @JsonProperty("type")
    RequestType searchTemplate = RequestType.SEARCH_TEMPLATE;
    @JsonProperty("payload")
    private List<NodeEdgeNodeStruct> struct = new ArrayList<>();

    public SearchByTemplateNodeEdgeNodeRequestImpl(ScElement fixed, EdgeType edgeType, NodeType nodeType) {
        struct.add(new NodeEdgeNodeStruct(fixed, edgeType, nodeType));
    }

    @JsonIgnore
    @Override
    public long getRequestId() {
        return id;
    }

    @JsonIgnore
    @Override
    public RequestType getRequestType() {
        return searchTemplate;
    }

    @JsonIgnore
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "SearchByTemplateNodeEdgeNodeRequestImpl{" +
                "id=" + id +
                ", searchTemplate=" + searchTemplate +
                ", struct=" + struct +
                '}';
    }
}
