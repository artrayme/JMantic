package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.response.CreateScElResponse;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class CreateScElResponseImpl implements CreateScElResponse {
    @JsonProperty("id")
    private long responseId;
    private boolean status;
    private boolean event;
    @JsonProperty("payload")
    private List<Long> createdElementAddress;

    @Override
    public Stream<Long> getAddresses() {
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

    @JsonIgnore
    @Override
    public String toString() {
        return "CreateScElResponseImpl{" +
                "responseId=" + responseId +
                ", status=" + status +
                ", event=" + event +
                ", createdElementAddress=" + createdElementAddress +
                '}';
    }
}
