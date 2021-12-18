package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.link.LinkContentType;
import org.jmantic.scmemory.websocketmemory.message.response.GetLinkContentResponse;

import java.util.List;
import java.util.Locale;

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

    @Override
    public List<Object> getContent() {
        return linkContent.stream().map(s -> s.value).toList();
    }

    @Override
    public List<LinkContentType> getType() {
        return linkContent.stream().map(s -> LinkContentType.valueOf(s.type.toUpperCase(Locale.ROOT))).toList();
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

    /**
     * Class describing the structure of the {@link GetLinkContentResponseImpl} to retrieve
     * {@link org.jmantic.scmemory.model.element.link.ScLink} content
     * <p>
     * {
     * "value": 56.7,  // value will be a null, if content doesn't exist
     * "type": content_type
     * }
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
}
