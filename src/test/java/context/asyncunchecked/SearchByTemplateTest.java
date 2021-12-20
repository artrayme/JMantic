package context.asyncunchecked;

import org.jmantic.api.context.AsyncUncheckedScContext;
import org.jmantic.api.context.UncheckedScContext;
import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.pattern.factory.DefaultScPattern3Factory;
import org.jmantic.scmemory.model.pattern.factory.DefaultScPattern5Factory;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author artrayme
 * @since 0.2.0
 */

//ToDO multithreading tests
public class SearchByTemplateTest {
    ScMemory memory;

    private AsyncUncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws Exception {
        memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scContext = new AsyncUncheckedScContext(new UncheckedScContext(memory));
        memory.open();
    }

    @AfterEach
    public void closeScMemory() throws Exception {
        memory.close();
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

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findSingleTripleFNodeEdgeNode() throws Exception {
        ScNode source = scContext.createNode(NodeType.NODE).get();
        ScNode target = scContext.createNode(NodeType.NODE).get();
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target).get();
        var x = scContext.find(DefaultScPattern3Factory.get(source, EdgeType.ACCESS, NodeType.NODE)).get().findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFNodeEdgeNodeEdgeFNode() throws ExecutionException, InterruptedException {
        ScNode source = scContext.createNode(NodeType.NODE).get();
        ScNode target = scContext.createNode(NodeType.NODE).get();
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target).get();

        ScNode relNode = scContext.createNode(NodeType.NODE).get();
        ScEdge relEdge = scContext.createEdge(EdgeType.ACCESS, relNode, edge).get();

        var result = scContext.find(DefaultScPattern5Factory.get(
                source,
                edge.getType(),
                target.getType(),
                relEdge.getType(),
                relNode)
        ).get().findFirst().get();

        assertEquals(source, result.get1());
        assertEquals(edge, result.get2());
        assertEquals(target, result.get3());
        assertEquals(relEdge, result.get4());
        assertEquals(relNode, result.get5());
    }

}
