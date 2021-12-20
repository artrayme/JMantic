package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.request.FindKeynodeRequest;
import org.jmantic.scmemory.websocketmemory.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;

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
