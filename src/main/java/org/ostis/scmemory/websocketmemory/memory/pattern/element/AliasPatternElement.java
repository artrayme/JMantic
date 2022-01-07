package org.ostis.scmemory.websocketmemory.memory.pattern.element;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.model.pattern.ScAliasedElement;

import java.util.Objects;

/**
 * @author artrayme
 * @since 0.3.2
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class AliasPatternElement implements ScAliasedElement {
    @JsonProperty("type")
    private final String type = "alias";
    @JsonProperty("value")
    private final String alias;

    public AliasPatternElement(String alias) {
        this.alias = alias;
    }

    @JsonIgnore
    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AliasPatternElement that = (AliasPatternElement) o;
        return Objects.equals(alias, that.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias);
    }
}
