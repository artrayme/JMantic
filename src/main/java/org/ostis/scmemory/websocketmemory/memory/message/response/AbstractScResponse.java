package org.ostis.scmemory.websocketmemory.memory.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.message.response.ScResponse;

/**
 * An abstract response class that contains information that is inherent in all system responses.
 * Jackson's annotations are used to further serialize the request.
 *
 * @author Michael
 * @since 0.2.0
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class AbstractScResponse implements ScResponse {
    @JsonProperty("id")
    private long responseId;
    @JsonProperty("status")
    private short status;
    @JsonProperty("event")
    private short event;

    @Override
    public long getResponseId() {
        return responseId;
    }

    @Override
    public boolean getResponseStatus() {
        return status==1;
    }

    @Override
    public boolean getEvent() {
        return event==1;
    }
}
