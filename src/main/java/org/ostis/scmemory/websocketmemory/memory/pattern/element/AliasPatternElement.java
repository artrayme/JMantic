package org.ostis.scmemory.websocketmemory.memory.pattern.element;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.model.pattern.element.ScAliasedElement;

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

}
