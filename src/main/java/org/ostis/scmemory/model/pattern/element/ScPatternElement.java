package org.ostis.scmemory.model.pattern.element;

/**
 * This class represents an abstract sc-element for searching by universal pattern.
 * You can find more information about the concrete elements:
 * <li>
 *     <ul>Fixed - {@link ScFixedElement}</ul>
 *     <ul>Typed - {@link ScTypedElement}</ul>
 *     <ul>Alias - {@link ScAliasedElement}</ul>
 * </li>
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


