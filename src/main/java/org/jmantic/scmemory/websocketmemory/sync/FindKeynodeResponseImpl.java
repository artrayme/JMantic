package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.response.FindKeynodeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FindKeynodeResponseImpl extends AbstractScResponse implements FindKeynodeResponse {
    @JsonProperty("payload")
    private final List<Long> addresses = new ArrayList<>();

    @Override
    public Stream<Long> getFindAddresses() {
        return addresses.stream();
    }
}
