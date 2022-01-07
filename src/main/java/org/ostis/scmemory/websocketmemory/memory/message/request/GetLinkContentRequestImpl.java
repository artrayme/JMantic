package org.ostis.scmemory.websocketmemory.memory.message.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.message.request.GetLinkContentRequest;
import org.ostis.scmemory.websocketmemory.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link GetLinkContentRequest}. The payload part consists
 * of a {@link List} of {@link GetContentStruct} elements.
 *
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GetLinkContentRequestImpl extends AbstractScRequest implements GetLinkContentRequest {
    @JsonProperty("payload")
    private List<GetContentStruct> contentStructs;

    /**
     * Class describing the structure of a request to retrieve the content of the {@link org.ostis.scmemory.model.element.link.ScLink}
     *
     *     {
     *       "command": "get",
     *       "addr": integer_value
     *     }
     */
    private static class GetContentStruct {
        @JsonProperty("command")
        String command = "get";
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
        super(1, RequestType.CONTENT);
        contentStructs = new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public boolean addToRequest(List<Long> addresses) {
        List<GetContentStruct> structs = addresses.stream().map(GetContentStruct::new).collect(Collectors.toList());
        return contentStructs.addAll(structs);
    }

    @JsonIgnore
    @Override
    public boolean addAddressToRequest(long address) {
        GetContentStruct struct = new GetContentStruct(address);
        return contentStructs.add(struct);
    }

    @JsonIgnore
    @Override
    public void resetRequest() {
        contentStructs.clear();
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
                "requestId=" + getRequestId() +
                ", requestType=" + getRequestType() +
                ", contentStructs=" + contentStructs +
                '}';
    }
}
