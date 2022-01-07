package org.ostis.scmemory.websocketmemory.memory.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ostis.scmemory.websocketmemory.message.response.DeleteScElResponse;

/**
 * Implementation of the response to the request for
 * deleting elements in the database. If command processed, then response status is true,
 * otherwise status - false.
 * Jackson's annotations are used to further serialize the request.
 *
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DeleteScElResponseImpl extends AbstractScResponse implements DeleteScElResponse {

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
