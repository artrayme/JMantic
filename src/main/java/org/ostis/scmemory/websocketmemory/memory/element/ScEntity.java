package org.ostis.scmemory.websocketmemory.memory.element;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.model.element.ScElement;

import java.util.Objects;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class ScEntity implements ScElement {
    @JsonProperty("el")
    private final String element;

    @JsonIgnore
    protected long address;

    public ScEntity(String element) {
        this.element = element;
    }

    public ScEntity(String element, long address) {
        this.element = element;
        this.address = address;
    }

    @JsonIgnore
    @Override
    public Long getAddress() {
        return address;
    }

    @JsonIgnore
    public void setAddress(long address) {
        this.address = address;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScEntity scEntity = (ScEntity) o;
        return address == scEntity.address;
    }
}
