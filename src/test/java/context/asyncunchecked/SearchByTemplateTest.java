package context.asyncunchecked;

import org.jmantic.api.context.AsyncUncheckedScContext;
import org.jmantic.api.context.UncheckedScContext;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author artrayme
 * 11/14/21
 */

//ToDO multithreading tests
public class SearchByTemplateTest {
    private AsyncUncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws URISyntaxException {
        scContext = new AsyncUncheckedScContext(new UncheckedScContext(new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"))));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTriple() throws ExecutionException, InterruptedException {
        ScNode source = scContext.createNode(NodeType.NODE).get();
        ScNode target1 = scContext.createNode(NodeType.NODE).get();
        ScEdge edge1 = scContext.createEdge(EdgeType.ACCESS, source, target1).get();
        var result = scContext.findAllConstructionsNodeEdgeNode(source, EdgeType.ACCESS, NodeType.NODE).get().toList();
        assertEquals(source, result.get(0).getSource());
        assertEquals(target1, result.get(0).getTarget());
        assertEquals(edge1, result.get(0));

    }
}
