package org.ostis.scmemory.model.pattern.pattern3;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;

/**
 * This class is a search pattern for 3 elements.
 * <p></p>
 * It is not recommended creating this class manually. Yau should use one of the factories:
 * {@link org.ostis.scmemory.model.pattern.factory.DefaultScPattern3Factory},
 * {@link org.ostis.scmemory.model.pattern.factory.ScPattern3FactoryWithNames},
 * <p>
 * Generics magic:
 * <ul>
 *     <li>
 *      T1 - expected and actual type of the first sc-element (and it fixed in any case)
 *     </li>
 *     <li>
 *       T3 - actual element type. It can be NodeType or LinkType.
 *     </li>
 *     <li>
 *         expectedT3 - expected ScElement type for 3-rd element in pattern. Can be ScNode or ScLink.
 *     </li>
 * </ul>
 *
 * @author artrayme
 * @since 0.3.2
 */

public class ScPattern3Impl<T1 extends ScElement, T3, expectedT3 extends ScElement> implements ScPattern3<T1, T3, expectedT3> {
    private final T1 element1;
    private final EdgeType edgeType;
    private final T3 element3;

    public ScPattern3Impl(T1 element1, EdgeType edgeType, T3 element3) {
        this.element1 = element1;
        this.edgeType = edgeType;
        this.element3 = element3;
    }

    @Override
    public T1 get1() {
        return element1;
    }

    @Override
    public EdgeType get2() {
        return edgeType;
    }

    @Override
    public T3 get3() {
        return element3;
    }

}
