package org.ostis.scmemory.websocketmemory.memory.pattern.element;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ostis.scmemory.model.pattern.element.ScAliasedElement;
import org.ostis.scmemory.model.pattern.element.ScTypedElement;

/**
 * @author artrayme
 * @since 0.3.2
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonPropertyOrder({"type", "value", "alias"})
public final class TypePatternElement<T> implements ScTypedElement<T> {
    @JsonProperty("type")
    private final String type = "type";
    @JsonProperty("value")
    private final T scElementType;
    @JsonProperty("alias")
    private final String alias;
    @JsonIgnore
    private final ScAliasedElement aliasedElement;

    public TypePatternElement(T type, ScAliasedElement element) {
        this.scElementType = type;
        this.alias = element.getAlias();
        aliasedElement = element;
    }

    @JsonIgnore
    @Override
    public T getValue() {
        return scElementType;
    }

    @JsonIgnore
    @Override
    public ScAliasedElement getAlias() {
        return aliasedElement;
    }
}
