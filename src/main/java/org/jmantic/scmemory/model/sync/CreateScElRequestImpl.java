package org.jmantic.scmemory.model.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.websocket.message.request.CreateScElRequest;
import org.jmantic.scmemory.model.websocket.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    public CreateScElRequestImpl() {
        requestId = 1;
        requestType = RequestType.CREATE_ELEMENTS;
        this.elementsToCreate = new ArrayList<>(5);
    }

    @Override
    public boolean addElementToRequest(ScElement element) {
        return elementsToCreate.add(element);
    }

    @Override
    public Stream<ScElement> resetRequest() {
        Stream<ScElement> elements = elementsToCreate.stream();
        elementsToCreate = new ArrayList<>(5);
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
}
