package context.mock;

import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;

import java.util.Objects;

/**
 * @author artrayme
 * 10/24/21
 */
class MockScNode implements ScNode {
    private final Long address;
    private final NodeType type;

    MockScNode(Long address, NodeType type) {
        this.address = address;
        this.type = type;
    }

    @Override
    public Long getAddress() {
        return address;
    }

    @Override
    public NodeType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MockScNode that = (MockScNode) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
