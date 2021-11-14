package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * @author artrayme
 * @since 0.2.0
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class EdgeSourceStruct {
    @JsonProperty("type")
    private final EdgeEndpointType type;
    @JsonProperty("value")
    private final Long value;

    public EdgeSourceStruct(EdgeEndpointType type, Long value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EdgeSourceStruct that = (EdgeSourceStruct) o;
        return type == that.type && Objects.equals(value, that.value);
    }

    @Override
    public String toString() {
        return "EdgeSourceStruct{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }

}
