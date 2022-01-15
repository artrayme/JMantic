package org.ostis.scmemory.websocketmemory.memory.message.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.memory.structures.KeynodeStruct;
import org.ostis.scmemory.websocketmemory.message.request.KeynodeRequest;
import org.ostis.scmemory.websocketmemory.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author artrayme
 * @since 0.3.3
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class KeynodeRequestImpl extends AbstractScRequest implements KeynodeRequest {

    @JsonProperty("payload")
    private final List<KeynodeStruct> keynodeStructs = new ArrayList<>();

    public KeynodeRequestImpl() {
        super(RequestType.KEYNODES);
    }

    @Override
    public void addAllIdtf(List<?extends KeynodeStruct> idtf) {
        keynodeStructs.addAll(idtf);
    }

    @Override
    public boolean isEmpty() {
        return keynodeStructs.isEmpty();
    }
}
