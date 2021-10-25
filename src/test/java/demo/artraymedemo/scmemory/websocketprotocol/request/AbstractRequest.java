package demo.artraymedemo.scmemory.websocketprotocol.request;

import demo.artraymedemo.scmemory.websocketprotocol.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author artrayme
 * 10/22/21
 */

@JsonPropertyOrder({"id", "type", "payload"})
public abstract class AbstractRequest implements Request {
    @JsonProperty("id")
    protected Long id;
    @JsonProperty("type")
    protected RequestType type;
    @JsonProperty("payload")
    protected List<CreateElementPayload> payload;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Stream<String> payload() {
        return payload.stream().map(Objects::toString);
    }

    @Override
    public RequestType getType() {
        return type;
    }
}
