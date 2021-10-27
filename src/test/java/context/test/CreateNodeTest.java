package context.test;

import context.mock.ScMemoryMock;
import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.sync.SyncScMemory;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author artrayme
 * 10/24/21
 */

public class CreateNodeTest {
    DefaultScContext scContext = new DefaultScContext(new ScMemoryMock());

    public CreateNodeTest() throws URISyntaxException {

    }

    @Test
    void createNode() {
        long start = System.currentTimeMillis();
        ScNode node = scContext.createNode(NodeType.NODE);
        System.err.println("Time = " + (System.currentTimeMillis()-start));
//        System.err.println(node.getAddress());
        assertEquals(NodeType.NODE, node.getType());
    }

    @Test
    void createNodes() {
        long size = 10000;
        var types = Stream.iterate(NodeType.NODE, e -> NodeType.NODE).limit(size);
        long start = System.currentTimeMillis();
        Stream<ScNode> nodes = scContext.createNodes(types);
        System.err.println("Time = " + (System.currentTimeMillis()-start));
        nodes.forEach(e -> {
//            System.err.println(e.getAddress());
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
