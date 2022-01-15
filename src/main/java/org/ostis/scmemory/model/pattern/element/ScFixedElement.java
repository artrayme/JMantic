package org.ostis.scmemory.model.pattern.element;

import org.ostis.scmemory.model.element.ScElement;

/**
 * This class represents one of the {@link ScPatternElement} classes.
 * You can use this pattern element if you have a known {@link ScElement}.
 *
 * @author artrayme
 * @since 0.6.0
 */
public interface ScFixedElement extends ScPatternElement {
    /**
     * @return sc-element of this pattern.
     */
    ScElement getElement();

    @Override
    default PatternElement getType() {
        return PatternElement.ADDR;
    }
}
