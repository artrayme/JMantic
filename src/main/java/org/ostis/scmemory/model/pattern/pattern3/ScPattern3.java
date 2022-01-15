package org.ostis.scmemory.model.pattern.pattern3;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;

/**
 * This is a common interface for creating and interacting with sc search patterns of 3 elements.
 * <p>
 * For type-safety, the generic interface consists of five components:
 * <ul>
 *     <li>
 *         Type first element. The first element is always fixed, that is, it is some sc-element.
 *     </li>
 *     <li>
 *         Type of the third element. It can be either concrete, i.e., child of sc-element, or unknown (represented by an enum element of the corresponding type)
 *     </li>
 *     <li>
 *         Expected type of the third element. It is not used directly in the interface, but allows to create a check for using this search pattern. This type must be a child of the Sc-element.
 *     </li>
 * </ul>
 *
 * @author artrayme
 * @since 0.3.2
 */
public interface ScPattern3<T1 extends ScElement, T3, expectedT3 extends ScElement> {
    T1 get1();

    EdgeType get2();

    T3 get3();

}
