package org.ostis.scmemory.websocketmemory.memory.message.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.message.request.CheckScElTypeRequest;
import org.ostis.scmemory.websocketmemory.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CheckScElTypeRequestImpl extends AbstractScRequest implements CheckScElTypeRequest {
    @JsonProperty("payload")
    private final List<Long> addresses = new ArrayList<>();

    public CheckScElTypeRequestImpl() {
        super(RequestType.CHECK_ELEMENTS);
    }

    @JsonIgnore
    @Override
    public boolean add(Long addr) {
        return addresses.add(addr);
    }

    @JsonIgnore
    @Override
    public boolean remove(Long addr) {
        return addresses.remove(addr);
    }

    @JsonIgnore
    @Override
    public Stream<Long> getAddresses() {
        return addresses.stream();
    }

    @JsonIgnore
    @Override
    public boolean isEmpty() {
        return addresses.isEmpty();
    }
}
