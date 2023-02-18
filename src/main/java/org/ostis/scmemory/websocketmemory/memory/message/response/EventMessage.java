package org.ostis.scmemory.websocketmemory.memory.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonPropertyOrder({"errors", "event", "id", "payload", "status"})
public class EventMessage extends AbstractScResponse {
    @JsonProperty("payload")
    private List<Long> scAddrs = new ArrayList<>();

    public List<Long> getScAddrs() {
        return scAddrs;
    }
}
