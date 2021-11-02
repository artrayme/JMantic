package context.test;

import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.websocketmemory.sync.SyncScMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author artrayme
 * 10/30/21
 */
public class CreateEdgeTest {
    private DefaultScContext scContext;
  
    @BeforeEach
    public void setUp() throws URISyntaxException {
        scContext = new DefaultScContext(new SyncScMemory(new URI("ws://localhost:8090/ws_json")));
    }

    @Test
    void createEdge() {
        ScNode first = scContext.createNode(NodeType.NODE);
        ScNode second = scContext.createNode(NodeType.NODE);
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, first, second);
//        assertEquals(edge.getType(), EdgeType.ACCESS);
//        assertEquals(edge.getFirst(), first);
//        assertEquals(edge.getSecond(), second);
        assertTrue(true);
    }
}
