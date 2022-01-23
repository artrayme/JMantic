package org.ostis.scmemory.model.pattern.element;

/**
 * This class represents an abstract sc-element for searching by universal pattern.
 * You can find more information about the concrete elements:
 * <ul>
 *     <li>Fixed - {@link ScFixedElement}</li>
 *     <li>Typed - {@link ScTypedElement}</li>
 *     <li>Alias - {@link ScAliasedElement}</li>
 * </ul>
 *
 * @author artrayme
 * @since 0.6.0
 */
public interface ScPatternElement {
    /**
     * @return type of this element
     * @see PatternElement
     */
    PatternElement getType();
}


