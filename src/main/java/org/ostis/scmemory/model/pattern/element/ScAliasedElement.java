package org.ostis.scmemory.model.pattern.element;

/**
 * This class represents one of the {@link ScPatternElement} classes.
 * Use cases for this pattern element you can find at {@link ScTypedElement}.
 *
 * @author artrayme
 * @since 0.6.0
 */
public interface ScAliasedElement extends ScPatternElement {
    /**
     * @return string representation of alias.
     */
    String getAlias();

    @Override
    default PatternElement getType() {
        return PatternElement.ALIAS;
    }

}
