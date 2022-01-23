package org.ostis.scmemory.model.pattern;

import org.ostis.scmemory.model.pattern.element.ScPatternElement;

/**
 * This class represents the basic element of a universal searching pattern.
 * Each ScPattern consists of a number of scc-triplets with searching rules.
 * Searching rule is a variant of {@link ScPatternElement} object that can be:
 * <ul>
 *     <li>
 *         Fixed. If one of ScPatternElement is {@link org.ostis.scmemory.model.pattern.element.ScFixedElement},
 *         the element address will be used at searching. This address will be present and constant in every founded pattern.
 *     </li>
 *     <li>
 *         Typed. If one of ScPatternElement is {@link org.ostis.scmemory.model.pattern.element.ScTypedElement},
 *         the element type will be used at searching. Address of this element can be different in different founded patterns.
 *     </li>
 *     <li>
 *         Aliased. Alise is used when you want to use Typed parameter as fixed. Api - {@link org.ostis.scmemory.model.pattern.element.ScAliasedElement}
 *     </li>
 * </ul>
 *
 * @author artrayme
 * @since 0.6.0
 */
public interface ScPatternTriplet {
    ScPatternElement get1();

    ScPatternElement get2();

    ScPatternElement get3();
}
