package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.response.DeleteScElResponse;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class DeleteScElResponseImpl implements DeleteScElResponse {
    @JsonProperty("id")
    private long responseId;
    private boolean status;
    private boolean event;

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
                '}';
    }
}
