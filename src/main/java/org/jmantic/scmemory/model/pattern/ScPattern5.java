package org.jmantic.scmemory.model.pattern;

import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;

/**
 * This is a common interface for creating and interacting with sc search patterns of 5 elements.
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
 *         The type of the fifth element. Subject to the same rules as the type of the third element.
 *     </li>
 *     <li>
 *         Expected type of the third element. It is not used directly in the interface, but allows to create a check for using this search pattern. This type must be a child of the Sc-element.
 *     </li>
 *     <li>
 *         Expected type of the fifth element. It also allows you to specify what type the result should be casted to after the search.
 *     </li>
 * </ul>
 *
 * @author artrayme
 * @since 0.3.2
 */
public interface ScPattern5<T1 extends ScElement, T3, T5, expectedT3 extends ScElement, expectedT5 extends ScElement> {
    T1 get1();

    void set1(T1 element);

    EdgeType get2();

    void set2(EdgeType edgeType);

    T3 get3();

    void set3(T3 element);

    EdgeType get4();

    void set4(EdgeType edgeType);

    T5 get5();

    void set5(T5 element);

}
