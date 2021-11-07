package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.response.SetLinkContentResponse;

import java.util.List;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class SetLinkContentResponseImpl implements SetLinkContentResponse {
    @JsonProperty("id")
    private long responseId;
    private boolean status;
    private boolean event;
    @JsonProperty("payload")
    private List<Boolean> statusOfOperations;

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

    @Override
    public List<Boolean> getOperationStatus() {
        return statusOfOperations;
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "SetLinkContentResponseImpl{" +
                "responseId=" + responseId +
                ", status=" + status +
                ", event=" + event +
                ", statusOfOperations=" + statusOfOperations +
                '}';
    }
}
