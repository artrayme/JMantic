package org.ostis.scmemory.websocketmemory.memory.message.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.message.request.RequestType;
import org.ostis.scmemory.websocketmemory.message.request.ScRequest;
import org.ostis.scmemory.websocketmemory.util.internal.RequestIdGenerator;

/**
 * An abstract request class that contains information that is inherent in all system requests.
 * Jackson's annotations are used to further serialize the request.
 *
 * @author Michael
 * @since 0.2.0
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class AbstractScRequest implements ScRequest {
    @JsonProperty("id")
    private final long requestId;
    @JsonProperty("type")
    private final RequestType requestType;

    public AbstractScRequest(RequestType requestType) {
        this.requestId = RequestIdGenerator.getId();
        this.requestType = requestType;
    }

}
