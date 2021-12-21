package org.ostis.scmemory.model.pattern;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.ScEdge;

/**
 * This interface describes constructions of five elements, which are the result of a 5-element pattern search.
 * <pre>
 *     {@code
 *                      get2()
 *         get1()-----------------------------get3()
 *                                  ^
 *                                  |
 *                                  | get4()
 *                                  |
 *                                  |
 *                                get5()
 *     }
 * </pre>
 *
 * @author artrayme
 * @since 0.3.2
 */
public interface ScConstruction5<T1 extends ScElement, T3 extends ScElement, T5 extends ScElement> {
    T1 get1();

    ScEdge get2();

    T3 get3();

    ScEdge get4();

    T5 get5();
}
