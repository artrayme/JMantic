package context.test;

import context.mock.ScMemoryMock;
import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author artrayme
 * 10/24/21
 */

public class CreateNodeTest {
    DefaultScContext scContext = new DefaultScContext(new ScMemoryMock());

    @Test
    void createNode() {
        ScNode node = scContext.createNode(NodeType.NODE);
        assertEquals(NodeType.NODE, node.getType());
    }

    @Test
    void createNodes() {
        long size = 10;
        var types = Stream.iterate(NodeType.NODE, e->NodeType.NODE).limit(size);
        Stream<ScNode> nodes = scContext.createNodes(types);
        nodes.forEach(e->{
            assertEquals(e.getType(), NodeType.NODE);
        });
    }

    @Test
    void createEdge() {
        ScNode first = scContext.createNode(NodeType.NODE);
        ScNode second = scContext.createNode(NodeType.NODE);
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, first, second);
        assertEquals(edge.getType(), EdgeType.ACCESS);
        assertEquals(edge.getFirst(), first);
        assertEquals(edge.getSecond(), second);
    }
}
