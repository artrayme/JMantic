package org.ostis.scmemory.websocketmemory.memory.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.message.response.EventResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EventResponseImpl extends AbstractScResponse implements EventResponse {
    @JsonProperty("payload")
    private List<Long> ids = new ArrayList<>();

    @Override
    public Stream<Long> getEventIds() {
        return ids.stream();
    }
}
