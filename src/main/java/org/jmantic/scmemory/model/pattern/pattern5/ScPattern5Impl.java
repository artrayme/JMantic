package org.jmantic.scmemory.model.pattern.pattern5;

import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.pattern.ScPattern5;

/**
 * This class is a search pattern for 5 elements.
 * <p></p>
 * It is not recommended creating this class manually. Yau should use one of the factories:
 * {@link org.jmantic.scmemory.model.pattern.factory.DefaultScPattern5Factory},
 * {@link org.jmantic.scmemory.model.pattern.factory.ScPattern5FactoryWithNames},
 * {@link org.jmantic.scmemory.model.pattern.factory.ScPattern5FactoryWithAbbreviation}.
 *
 * @author artrayme
 * @since 0.3.2
 */
public class ScPattern5Impl<T1 extends ScElement, T3, T5, expectedT3 extends ScElement, expectedT5 extends ScElement> implements ScPattern5<T1, T3, T5, expectedT3, expectedT5> {
    private T1 element1;
    private EdgeType edgeType2;
    private T3 element3;
    private EdgeType edgeType4;
    private T5 element5;

    public ScPattern5Impl(T1 element1, EdgeType edgeType2, T3 element3, EdgeType edgeType4, T5 element5) {
        this.element1 = element1;
        this.edgeType2 = edgeType2;
        this.element3 = element3;
        this.edgeType4 = edgeType4;
        this.element5 = element5;
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
        return edgeType2;
    }

    @Override
    public void set2(EdgeType edgeType) {
        edgeType2 = edgeType;
    }

    @Override
    public T3 get3() {
        return element3;
    }

    @Override
    public void set3(T3 element) {
        element3 = element;
    }

    @Override
    public EdgeType get4() {
        return edgeType4;
    }

    @Override
    public void set4(EdgeType edgeType) {
        edgeType4 = edgeType;
    }

    @Override
    public T5 get5() {
        return element5;
    }

    @Override
    public void set5(T5 element) {
        element5 = element;
    }
}
