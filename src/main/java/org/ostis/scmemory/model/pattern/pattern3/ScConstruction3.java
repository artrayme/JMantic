package org.ostis.scmemory.model.pattern.pattern3;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.ScEdge;

/**
 * This interface describes constructions of three elements, which are the result of a 3-element pattern search.
 * <pre>
 *     {@code
 *                      getEdge()
 *         get1()-----------------------get3()
 *     }
 * </pre>
 * @author artrayme
 * @since 0.3.2
 */
public interface ScConstruction3<T1 extends ScElement, T3 extends ScElement> {
    T1 get1();

    ScEdge getEdge();

    T3 get3();

}
