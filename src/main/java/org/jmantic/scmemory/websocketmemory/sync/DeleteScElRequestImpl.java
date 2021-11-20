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
class DeleteScElRequestImpl extends AbstractScRequest implements DeleteScElRequest {
    @JsonProperty("payload")
    private List<Long> addressesToDelete;

    public DeleteScElRequestImpl() {
        super(1, RequestType.DELETE_ELEMENTS);
        addressesToDelete = new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public boolean addToRequest(List<Long> addresses) {
        return addressesToDelete.addAll(addresses);
    }

    @JsonIgnore
    @Override
    public boolean addAddressToRequest(Long address) {
        return addressesToDelete.add(address);
    }

    @JsonIgnore
    @Override
    public void resetRequest() {
        addressesToDelete.clear();
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
                "requestId=" + getRequestId() +
                ", requestType=" + getRequestType() +
                ", addressesToDelete=" + addressesToDelete +
                '}';
    }
}
