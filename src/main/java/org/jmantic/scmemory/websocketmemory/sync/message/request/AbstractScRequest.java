package org.jmantic.scmemory.websocketmemory.sync.message.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.request.RequestType;
import org.jmantic.scmemory.websocketmemory.message.request.ScRequest;

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

    public AbstractScRequest(long requestId, RequestType requestType) {
        this.requestId = requestId;
        this.requestType = requestType;
    }

    @JsonIgnore
    @Override
    public long getRequestId() {
        return requestId;
    }

    @JsonIgnore
    @Override
    public RequestType getRequestType() {
        return requestType;
    }
}
