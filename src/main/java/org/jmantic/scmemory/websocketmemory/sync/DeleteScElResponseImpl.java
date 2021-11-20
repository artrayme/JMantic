package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jmantic.scmemory.websocketmemory.message.response.DeleteScElResponse;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class DeleteScElResponseImpl extends AbstractScResponse implements DeleteScElResponse {

    @JsonIgnore
    @Override
    public String toString() {
        return "CreateScElResponseImpl{" +
                "responseId=" + getResponseId() +
                ", status=" + getResponseStatus() +
                ", event=" + getEvent() +
                '}';
    }
}
