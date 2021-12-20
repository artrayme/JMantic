package org.jmantic.scmemory.websocketmemory.sync.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.response.CreateScElResponse;

import java.util.List;
import java.util.stream.Stream;

/**
 * Implementation of the response to the request for
 * creating elements in the database. The payload part is a {@link List}
 * of addresses of the created {@link org.jmantic.scmemory.model.element.ScElement}. Or 0 if the item could not be created.
 * Jackson's annotations are used to further serialize the request.
 *
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CreateScElResponseImpl extends AbstractScResponse implements CreateScElResponse {
    @JsonProperty("payload")
    private List<Long> createdElementAddress;

    @Override
    public Stream<Long> getAddresses() {
        return createdElementAddress.stream();
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "CreateScElResponseImpl{" +
                "responseId=" + getResponseId() +
                ", status=" + getResponseStatus() +
                ", event=" + getEvent() +
                ", createdElementAddress=" + createdElementAddress +
                '}';
    }
}
