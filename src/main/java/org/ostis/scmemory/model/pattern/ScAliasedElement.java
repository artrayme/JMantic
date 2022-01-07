package org.ostis.scmemory.model.pattern;

public interface ScAliasedElement extends ScPatternElement {
    String getAlias();

    @Override
    default PatternElement getType() {
        return PatternElement.ALIAS;
    }

}
