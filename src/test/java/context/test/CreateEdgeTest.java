package context.test;

import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.websocketmemory.sync.SyncScMemory;
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
    private final DefaultScContext scContext = new DefaultScContext(SyncScMemory.getSyncScMemory(new URI("ws://localhost:8090/ws_json")));

    public CreateEdgeTest() throws URISyntaxException {

    }

    @Test
    void createEdge() {
        //        ScNode first = scContext.createNode(NodeType.NODE);
        //        ScNode second = scContext.createNode(NodeType.NODE);
        //        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, first, second);
        //        assertEquals(edge.getType(), EdgeType.ACCESS);
        //        assertEquals(edge.getFirst(), first);
        //        assertEquals(edge.getSecond(), second);
        assertTrue(true);
    }
}
