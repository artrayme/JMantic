package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.response.SearchByTemplateResponse;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author artrayme
 * 11/6/21
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class SearchByTemplateResponseImpl implements SearchByTemplateResponse {
    @JsonProperty("id")
    private long responseId;
    @JsonProperty("event")
    private boolean event;
    @JsonProperty("status")
    private boolean status;
    @JsonProperty("payload")
    private PayloadFoundByTemplateStruct payloadFoundByTemplateStruct;

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

    @Override
    public Stream<Stream<Long>> getFoundAddresses() {
        return payloadFoundByTemplateStruct.getFoundAddresses().stream().map(Collection::stream);
    }

    @Override
    public String toString() {
        return "SearchByTemplateResponseImpl{" +
                "responseId=" + responseId +
                ", event=" + event +
                ", status=" + status +
                ", payloadFoundByTemplateStruct=" + payloadFoundByTemplateStruct +
                '}';
    }
}
