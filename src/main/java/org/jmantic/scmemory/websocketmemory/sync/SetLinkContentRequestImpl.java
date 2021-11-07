package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.link.LinkContentType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.websocketmemory.message.request.RequestType;
import org.jmantic.scmemory.websocketmemory.message.request.SetLinkContentRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SetLinkContentRequestImpl implements SetLinkContentRequest {
    @JsonProperty("id")
    private final long requestId;
    @JsonProperty("type")
    private final RequestType requestType;
    @JsonProperty("payload")
    private List<SetContentStruct> contentStructs;

    {
        requestId = 1;
        requestType = RequestType.CONTENT;
    }

    private static class SetContentStruct {
        @JsonProperty("command")
        String command = "set";
        @JsonProperty("type")
        LinkContentType contentType;
        @JsonProperty("data")
        Object content;
        @JsonProperty("addr")
        long address;

        public SetContentStruct(LinkContentType contentType, Object content, long address) {
            this.contentType = contentType;
            this.content = content;
            this.address = address;
        }
    }

    public SetLinkContentRequestImpl() {
        contentStructs = new ArrayList<>();
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
    public void addToRequest(ScLink link, Object data) {
        SetContentStruct struct = new SetContentStruct(link.getContentType(), data, link.getAddress());
        contentStructs.add(struct);
    }

    @JsonIgnore
    @Override
    public void resetRequest() {
        contentStructs.clear();
    }
}
