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
class SetLinkContentResponseImpl extends AbstractScResponse implements SetLinkContentResponse {
    @JsonProperty("payload")
    private List<Boolean> statusOfOperations;

    @Override
    public List<Boolean> getOperationStatus() {
        return statusOfOperations;
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "SetLinkContentResponseImpl{" +
                "responseId=" + getResponseId() +
                ", status=" + getResponseStatus() +
                ", event=" + getEvent() +
                ", statusOfOperations=" + statusOfOperations +
                '}';
    }
}
