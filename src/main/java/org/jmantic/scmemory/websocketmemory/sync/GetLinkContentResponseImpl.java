package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.response.GetLinkContentResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class GetLinkContentResponseImpl implements GetLinkContentResponse {
    @JsonProperty("id")
    private long responseId;
    private boolean status;
    private boolean event;
    @JsonProperty("payload")
    private List<GetContentStruct> linkContent;

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    private static class GetContentStruct {
        @JsonProperty("value")
        Object value;
        @JsonProperty("type")
        String type;

        @JsonIgnore
        @Override
        public String toString() {
            return "GetContentStruct{" +
                    "type='" + type + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    @Override
    public List<Object> getContent() {
        return linkContent.stream().map(s -> s.value).collect(Collectors.toList());
    }

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

    @JsonIgnore
    @Override
    public String toString() {
        return "GetLinkContentResponseImpl{" +
                "responseId=" + responseId +
                ", status=" + status +
                ", event=" + event +
                ", statusOfOperations=" + linkContent +
                '}';
    }
}
