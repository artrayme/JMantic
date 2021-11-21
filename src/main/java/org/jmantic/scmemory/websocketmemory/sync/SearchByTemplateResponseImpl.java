package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.response.SearchByTemplateResponse;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class SearchByTemplateResponseImpl extends AbstractScResponse implements SearchByTemplateResponse {
    @JsonProperty("payload")
    private PayloadFoundByTemplateStruct payloadFoundByTemplateStruct;

    @Override
    public Stream<Stream<Long>> getFoundAddresses() {
        return payloadFoundByTemplateStruct.getFoundAddresses().stream().map(Collection::stream);
    }

    @Override
    public String toString() {
        return "SearchByTemplateResponseImpl{" +
                "responseId=" + getResponseId() +
                ", event=" + getEvent() +
                ", status=" + getResponseStatus() +
                ", payloadFoundByTemplateStruct=" + payloadFoundByTemplateStruct +
                '}';
    }
}
