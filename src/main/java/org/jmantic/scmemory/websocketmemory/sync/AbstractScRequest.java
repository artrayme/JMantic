package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.request.RequestType;
import org.jmantic.scmemory.websocketmemory.message.request.ScRequest;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
abstract class AbstractScRequest implements ScRequest {
    @JsonProperty("id")
    private final long requestId;
    @JsonProperty("type")
    private final RequestType requestType;

    public AbstractScRequest(long requestId, RequestType requestType){
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
