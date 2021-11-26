package context.unchecked;

import org.jmantic.api.context.UncheckedScContext;
import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author artrayme
 * @since 0.0.1
 */
public class SearchByTemplateTest {
    ScMemory memory;

    private UncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws Exception {
        memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scContext = new UncheckedScContext(memory);
        memory.open();
    }

    @AfterEach
    public void closeScMemory() throws Exception {
        memory.close();
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
