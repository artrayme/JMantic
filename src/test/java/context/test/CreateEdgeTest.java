package context.test;

import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.websocketmemory.sync.SyncScMemory;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author artrayme
 * 10/30/21
 */
public class CreateEdgeTest {
    private final DefaultScContext scContext = new DefaultScContext(new SyncScMemory(new URI("ws://localhost:8090/ws_json")));

    public CreateEdgeTest() throws URISyntaxException {

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
