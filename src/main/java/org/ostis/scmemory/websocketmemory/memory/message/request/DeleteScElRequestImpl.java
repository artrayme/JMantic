package org.ostis.scmemory.websocketmemory.memory.message.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.message.request.DeleteScElRequest;
import org.ostis.scmemory.websocketmemory.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a request to remove items from the database.
 * Payload part is implemented as a {@link List} of addresses of {@link org.ostis.scmemory.model.element.ScElement}
 * that need to be removed
 *
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DeleteScElRequestImpl extends AbstractScRequest implements DeleteScElRequest {
    @JsonProperty("payload")
    private List<Long> addressesToDelete;

    public DeleteScElRequestImpl() {
        super(RequestType.DELETE_ELEMENTS);
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

}
