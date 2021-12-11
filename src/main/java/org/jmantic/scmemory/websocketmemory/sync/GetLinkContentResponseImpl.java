package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.response.GetLinkContentResponse;

import java.util.List;

/**
 * Implementation of the {@link GetLinkContentResponse}. The payload part consists of a {@link List}
 * of {@link GetContentStruct} elements.
 *
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class GetLinkContentResponseImpl extends AbstractScResponse implements GetLinkContentResponse {
    @JsonProperty("payload")
    private List<GetContentStruct> linkContent;

    /**
     * Class describing the structure of the {@link GetLinkContentResponseImpl} to retrieve
     * {@link org.jmantic.scmemory.model.element.link.ScLink} content
     *
     *     {
     *       "value": 56.7,  // value will be a null, if content doesn't exist
     *       "type": content_type
     *     }
     */
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
        return linkContent.stream().map(s -> s.value).toList();
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "GetLinkContentResponseImpl{" +
                "responseId=" + getResponseId() +
                ", status=" + getResponseStatus() +
                ", event=" + getEvent() +
                ", statusOfOperations=" + linkContent +
                '}';
    }
}
