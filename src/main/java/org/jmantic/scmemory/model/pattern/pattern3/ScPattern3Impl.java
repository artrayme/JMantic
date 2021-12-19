package org.jmantic.scmemory.model.pattern.pattern3;

import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.pattern.ScPattern3;

/**
 * This class is a search pattern for 3 elements.
 * <p></p>
 * It is not recommended creating this class manually. Yau should use one of the factories:
 * {@link org.jmantic.scmemory.model.pattern.factory.DefaultScPattern3Factory},
 * {@link org.jmantic.scmemory.model.pattern.factory.ScPattern3FactoryWithNames},
 * {@link org.jmantic.scmemory.model.pattern.factory.ScPattern3FactoryWithAbbreviation}.
 *
 * @author artrayme
 * @since 0.3.2
 */

public class ScPattern3Impl<T1 extends ScElement, T3, expectedT3 extends ScElement> implements ScPattern3<T1, T3, expectedT3> {
    private T1 element1;
    private EdgeType edgeType;
    private T3 element3;

    public ScPattern3Impl(T1 element1, EdgeType edgeType, T3 element3) {
        this.element1 = element1;
        this.edgeType = edgeType;
        this.element3 = element3;
    }

    public ScPattern3Impl() {

    }

    @Override
    public T1 get1() {
        return element1;
    }

    @Override
    public void set1(T1 element) {
        element1 = element;
    }

    @Override
    public EdgeType get2() {
        return edgeType;
    }

    @Override
    public void set2(EdgeType edgeType) {
        this.edgeType = edgeType;
    }

    @Override
    public T3 get3() {
        return element3;
    }

    @Override
    public void set3(T3 element) {
        element3 = element;
    }

}
