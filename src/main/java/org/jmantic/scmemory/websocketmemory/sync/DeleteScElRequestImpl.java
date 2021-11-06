package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.request.DeleteScElRequest;
import org.jmantic.scmemory.websocketmemory.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DeleteScElRequestImpl implements DeleteScElRequest {
    @JsonProperty("id")
    private final long requestId;
    @JsonProperty("type")
    private final RequestType requestType;
    @JsonProperty("payload")
    private List<Long> addressesToDelete;

    {
        requestId = 1;
        requestType = RequestType.DELETE_ELEMENTS;
    }

    public DeleteScElRequestImpl() {
        addressesToDelete = new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public void addToRequest(List<Long> addresses) {
        addressesToDelete.addAll(addresses);
    }

    @JsonIgnore
    @Override
    public boolean addAddressToRequest(Long address) {
        return addressesToDelete.add(address);
    }

    @JsonIgnore
    @Override
    public List<Long> resetRequest() {
        List<Long> addresses = addressesToDelete;
        addressesToDelete = new ArrayList<>();
        return addresses;
    }

    @JsonIgnore
    @Override
    public long getRequestId() {
        return requestId;
    }

    @JsonIgnore
    @Override
    public RequestType getRequestType() {
        return requestType;
    }

    @JsonIgnore
    @Override
    public boolean isEmpty() {
        return addressesToDelete.isEmpty();
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "DeleteScElRequestImpl{" +
                "requestId=" + requestId +
                ", requestType=" + requestType +
                ", addressesToDelete=" + addressesToDelete +
                '}';
    }
}
