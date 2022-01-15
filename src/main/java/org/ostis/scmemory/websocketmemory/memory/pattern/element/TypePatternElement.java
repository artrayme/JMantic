package org.ostis.scmemory.websocketmemory.memory.pattern.element;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.model.pattern.element.ScAliasedElement;
import org.ostis.scmemory.model.pattern.element.ScTypedElement;

/**
 * @author artrayme
 * @since 0.3.2
 */
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
