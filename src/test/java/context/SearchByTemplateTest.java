package context;

import org.jmantic.api.context.UncheckedScContext;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.websocketmemory.sync.OstisClientSync;
import org.jmantic.scmemory.websocketmemory.sync.SyncScMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author artrayme
 * 11/6/21
 */
public class SearchByTemplateTest {
    private UncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws URISyntaxException {
        scContext = new UncheckedScContext(new SyncScMemory(new OstisClientSync(new URI("ws://localhost:8090/ws_json"))));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTriple() {
        ScNode source = scContext.createNode(NodeType.NODE);
        ScNode target1 = scContext.createNode(NodeType.NODE);
        ScEdge edge1 = scContext.createEdge(EdgeType.ACCESS, source, target1);
        var result = scContext.findAllConstructionsNodeEdgeNode(source, EdgeType.ACCESS, NodeType.NODE).toList();
        assertEquals(source, result.get(0).getSource());
        assertEquals(target1, result.get(0).getTarget());
        assertEquals(edge1, result.get(0));

    }
}
