package org.jmantic.scmemory.model.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.websocket.message.response.CreateScElResponse;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Michael
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class CreateScElResponseImpl implements CreateScElResponse {
    @JsonProperty("id")
    private long responseId;
    private boolean status;
    private boolean event;
    @JsonProperty("payload")
    private List<Integer> createdElementAddress;

    @Override
    public Stream<Integer> getAddresses() {
        return createdElementAddress.stream();
    }

    @Override
    public long getResponseId() {
        return responseId;
    }

    @Override
    public boolean getResponseStatus() {
        return status;
    }

    @Override
    public boolean getEvent() {
        return event;
    }
}
