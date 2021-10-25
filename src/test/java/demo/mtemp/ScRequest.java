package demo.mtemp;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.LinkedList;
import java.util.List;


@JsonPropertyOrder({"id", "type", "payload"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class ScRequest {
    @JsonProperty("id")
    private final long requestId;
    @JsonProperty("type")
    private final String requestType;
    private final List<ScElement> payload;

    public ScRequest(String type) {
        this.requestId = IdGenerator.generateId();
        this.requestType = type;
        this.payload = new LinkedList<>();
    }

    public long getRequestId() {
        return requestId;
    }

    public String getType() {
        return requestType;
    }

    public boolean addScElementToRequest(ScElement scElement) {
        return payload.add(scElement);
    }

    public abstract String parsToJsonRequest() throws JsonProcessingException;
}