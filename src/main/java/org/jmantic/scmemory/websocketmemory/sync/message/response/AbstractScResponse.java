package org.jmantic.scmemory.websocketmemory.sync.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.response.ScResponse;

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
}
