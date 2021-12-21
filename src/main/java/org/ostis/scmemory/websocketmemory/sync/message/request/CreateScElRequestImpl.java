package org.ostis.scmemory.websocketmemory.sync.message.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.websocketmemory.message.request.CreateScElRequest;
import org.ostis.scmemory.websocketmemory.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a request to create elements in the database.
 * A {@link List} of {@link ScElement} is used as a payload part of the request.
 * Jackson's annotations are used to further serialize the request.
 *
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CreateScElRequestImpl extends AbstractScRequest implements CreateScElRequest {
    @JsonProperty("payload")
    private List<ScElement> elementsToCreate;

    public CreateScElRequestImpl() {
        super(1, RequestType.CREATE_ELEMENTS);
        elementsToCreate = new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public boolean addToRequest(List<? extends ScElement> elements) {
        return elementsToCreate.addAll(elements);
    }

    @JsonIgnore
    @Override
    public boolean addElementToRequest(ScElement element) {
        return elementsToCreate.add(element);
    }

    @JsonIgnore
    @Override
    public void resetRequest() {
        elementsToCreate.clear();
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
