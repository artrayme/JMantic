package org.jmantic.scmemory.model.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.websocket.message.request.CreateScElRequest;
import org.jmantic.scmemory.model.websocket.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class CreateScElRequestImpl implements CreateScElRequest {
    @JsonProperty("id")
    private final long requestId;
    @JsonProperty("type")
    private final RequestType requestType;
    @JsonProperty("payload")
    private List<ScElement> elementsToCreate;

    {
        requestId = 1;
        requestType = RequestType.CREATE_ELEMENTS;
    }

    public CreateScElRequestImpl() {
        elementsToCreate = new ArrayList<>(10);
    }

    public CreateScElRequestImpl(List<ScElement> elementsToCreate) {
        this.elementsToCreate = elementsToCreate;
    }

    @Override
    public void replaceRequest(List<ScElement> elements) {
        elementsToCreate = elements;
    }

    @Override
    public boolean addElementToRequest(ScElement element) {
        return elementsToCreate.add(element);
    }

    @Override
    public List<ScElement> resetRequest() {
        List<ScElement> elements = elementsToCreate;
        elementsToCreate = new ArrayList<>(10);
        return elements;
    }

    @Override
    public long getRequestId() {
        return requestId;
    }

    @Override
    public RequestType getRequestType() {
        return requestType;
    }

    @JsonIgnore
    @Override
    public boolean isEmpty() {
        return elementsToCreate.isEmpty();
    }

    @JsonIgnore
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CreateScElRequestImpl{");
        sb.append("requestId=").append(requestId);
        sb.append(", requestType=").append(requestType);
        sb.append(", elementsToCreate=").append(elementsToCreate);
        sb.append('}');
        return sb.toString();
    }
}
