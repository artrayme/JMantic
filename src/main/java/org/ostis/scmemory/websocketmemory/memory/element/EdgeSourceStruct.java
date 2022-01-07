package org.ostis.scmemory.websocketmemory.memory.element;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author artrayme
 * @since 0.2.0
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EdgeSourceStruct {
    @JsonProperty("type")
    private final EdgeEndpointType type;
    @JsonProperty("value")
    private final Long value;

    public EdgeSourceStruct(EdgeEndpointType type, Long value) {
        this.type = type;
        this.value = value;
    }

}
