package org.ostis.scmemory.model.pattern;

public interface ScTypedElement<T> extends ScPatternElement{
    T getValue();
    ScAliasedElement getAlias();

    @Override
    default PatternElement getType(){
        return PatternElement.TYPE;
    }
}
