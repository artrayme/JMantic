package context.mock;

import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;

import java.util.Objects;

/**
 * @author artrayme
 * 10/24/21
 */
class MockScEdge implements ScEdge {
    private final Long address;
    private final EdgeType type;
    private final ScElement first;
    private final ScElement second;

    MockScEdge(Long address, EdgeType type, ScElement first, ScElement second) {
        this.address = address;
        this.type = type;
        this.first = first;
        this.second = second;
    }

    @Override
    public Long getAddress() {
        return address;
    }

    @Override
    public EdgeType getType() {
        return type;
    }

    @Override
    public ScElement getSource() {
        return first;
    }

    @Override
    public ScElement getTarget() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MockScEdge that = (MockScEdge) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
