package org.ostis.scmemory.websocketmemory.sync.message.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.message.request.FindKeynodeRequest;
import org.ostis.scmemory.websocketmemory.message.request.RequestType;
import org.ostis.scmemory.websocketmemory.sync.structures.FindKeynodeStruct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author artrayme
 * @since 0.3.3
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FindKeynodeRequestImpl extends AbstractScRequest implements FindKeynodeRequest {

    @JsonProperty("payload")
    private final List<FindKeynodeStruct> keynodeStructs = new ArrayList<>();

    public FindKeynodeRequestImpl() {
        super(1, RequestType.KEYNODES);
    }


    @Override
    public void addAllIdtf(List<String> idtf) {
        keynodeStructs.addAll(idtf.stream().map(FindKeynodeStruct::new).toList());
    }

    @Override
    public boolean isEmpty() {
        return keynodeStructs.isEmpty();
    }
}
