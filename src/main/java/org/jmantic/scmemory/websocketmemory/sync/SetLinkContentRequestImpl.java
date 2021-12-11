package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.link.LinkContentType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.websocketmemory.message.request.GetLinkContentRequest;
import org.jmantic.scmemory.websocketmemory.message.request.RequestType;
import org.jmantic.scmemory.websocketmemory.message.request.SetLinkContentRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link SetLinkContentRequest}. The payload part consists
 * of a {@link List} of {@link SetContentStruct} elements.
 *
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class SetLinkContentRequestImpl extends AbstractScRequest implements SetLinkContentRequest {
    @JsonProperty("payload")
    private List<SetContentStruct> contentStructs;

    /**
     * Class describing the structure of a request to set content to the {@link org.jmantic.scmemory.model.element.link.ScLink}
     *
     *     {
     *       // command to set ScLink content
     *       "command": "set",
     *       "type": "int",  // content type
     *       "data": 67,     // content could be a string, number
     *       "addr": 3123    // ScAddr of ScLink to change content
     *     }
     */
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

        @JsonIgnore
        @Override
        public String toString() {
            return "SetContentStruct{" +
                    "command='" + command + '\'' +
                    ", contentType=" + contentType +
                    ", content=" + content +
                    ", address=" + address +
                    '}';
        }
    }

    public SetLinkContentRequestImpl() {
        super(1, RequestType.CONTENT);
        contentStructs = new ArrayList<>();
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

    @JsonIgnore
    @Override
    public String toString() {
        return "SetLinkContentRequestImpl{" +
                "requestId=" + getRequestId() +
                ", requestType=" + getRequestType() +
                ", contentStructs=" + contentStructs +
                '}';
    }
}
