package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.request.GetLinkContentRequest;
import org.jmantic.scmemory.websocketmemory.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class GetLinkContentRequestImpl implements GetLinkContentRequest {
    @JsonProperty("id")
    private final long requestId;
    @JsonProperty("type")
    private final RequestType requestType;
    @JsonProperty("payload")
    private List<GetContentStruct> contentStructs;

    {
        requestId = 1;
        requestType = RequestType.CONTENT;
    }

    private static class GetContentStruct {
        @JsonProperty("command")
        String command = "set";
        @JsonProperty("addr")
        long address;

        public GetContentStruct(long address) {
            this.address = address;
        }

        @JsonIgnore
        @Override
        public String toString() {
            return "GetContentStruct{" +
                    "command='" + command + '\'' +
                    ", address=" + address +
                    '}';
        }
    }

    public GetLinkContentRequestImpl() {
        contentStructs = new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public void addToRequest(long address) {
        GetContentStruct struct = new GetContentStruct(address);
        contentStructs.add(struct);
    }

    @JsonIgnore
    @Override
    public void resetRequest() {
        contentStructs.clear();
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

    @JsonIgnore
    @Override
    public boolean isEmpty() {
        return contentStructs.isEmpty();
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "GetLinkContentRequestImpl{" +
                "requestId=" + requestId +
                ", requestType=" + requestType +
                ", contentStructs=" + contentStructs +
                '}';
    }
}
