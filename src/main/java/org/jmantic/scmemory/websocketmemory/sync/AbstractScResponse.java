package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.response.ScResponse;

/**
 * @author Michael
 * @since 0.2.0
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
abstract class AbstractScResponse implements ScResponse {
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
