package org.ostis.scmemory.model.pattern;

import java.util.stream.Stream;

/**
 * Main class of sc-pattern searching concept.
 * You can create any search pattern you want.
 * <p></p>
 * Pattern consists of triplets {@link ScPatternTriplet}.
 * Each triplet describes elementary part of a searching pattern.
 * A single triplet is the simplest searching pattern.
 * More complex search patterns can be decomposed into triplets.
 * For example, pattern5 is two connected triplets
 * (you can find example at {@link org.ostis.scmemory.model.pattern.element.ScTypedElement})
 *
 * @author artrayme
 * @since 0.6.0
 */

public interface ScPattern {
    /**
     * @return all pattern triplets
     */
    Stream<ScPatternTriplet> getElements();

    /**
     * @param element - add triplet as part of a searching pattern.
     * @return true if the element added successfully.
     */
    boolean addElement(ScPatternTriplet element);

    /**
     * @param element - remove triplet from a searching pattern.
     * @return true, if the element is removed successfully.
     */
    boolean removeElement(ScPatternTriplet element);
}
