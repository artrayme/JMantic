package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.websocketmemory.message.request.CreateScElRequest;
import org.jmantic.scmemory.websocketmemory.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class CreateScElRequestImpl extends AbstractScRequest implements CreateScElRequest {
    @JsonProperty("payload")
    private List<ScElement> elementsToCreate;

    public CreateScElRequestImpl() {
        super(1, RequestType.CREATE_ELEMENTS);
        elementsToCreate = new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public void addToRequest(List<? extends ScElement> elements) {
        elementsToCreate.addAll(elements);
    }

    @JsonIgnore
    @Override
    public boolean addElementToRequest(ScElement element) {
        return elementsToCreate.add(element);
    }

    @JsonIgnore
    @Override
    public List<ScElement> resetRequest() {
        List<ScElement> elements = elementsToCreate;
        elementsToCreate = new ArrayList<>(10);
        return elements;
    }

    @JsonIgnore
    @Override
    public boolean isEmpty() {
        return elementsToCreate.isEmpty();
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "CreateScElRequestImpl{" +
                "requestId=" + getRequestId() +
                ", requestType=" + getRequestType() +
                ", elementsToCreate=" + elementsToCreate +
                '}';
    }
}
