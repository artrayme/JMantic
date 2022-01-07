package org.ostis.scmemory.websocketmemory.memory.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.message.response.FindKeynodeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.3.3
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FindKeynodeResponseImpl extends AbstractScResponse implements FindKeynodeResponse {
    @JsonProperty("payload")
    private final List<Long> addresses = new ArrayList<>();

    @Override
    public Stream<Long> getFindAddresses() {
        return addresses.stream();
    }
}
