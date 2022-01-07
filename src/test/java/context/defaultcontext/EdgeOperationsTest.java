package context.defaultcontext;

import org.ostis.api.context.DefaultScContext;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author artrayme
 * @since 0.2.0
 */

//ToDO exceptions test
public class EdgeOperationsTest {
    ScMemory memory;
    private DefaultScContext scContext;

    @BeforeEach
    public void setUp() throws Exception {
        memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scContext = new DefaultScContext(memory);
        memory.open();
    }

    @AfterEach
    public void closeScMemory() throws Exception {
        memory.close();
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleEdge() throws ScMemoryException {
        ScNode source = scContext.createNode(NodeType.NODE);
        ScNode target = scContext.createNode(NodeType.NODE);
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target);
        assertEquals(edge.getType(), EdgeType.ACCESS);
        assertEquals(edge.getSource(), source);
        assertEquals(edge.getTarget(), target);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleEdgesWithOneType() throws ScMemoryException {
        NodeType expectedNodeType = NodeType.NODE;
        EdgeType expectedEdgeType = EdgeType.ACCESS;
        int count = 10;

        var sources = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType).limit(count)).collect(Collectors.toList());
        var targets = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType).limit(count)).collect(Collectors.toList());
        var types = Stream.iterate(expectedEdgeType, e -> expectedEdgeType).limit(count);
        var edges = scContext.createEdges(types, sources.stream(), targets.stream());

        Iterator<ScEdge> edgeIterator = edges.iterator();
        Iterator<? extends ScNode> sourceNodeIterator = sources.iterator();
        Iterator<? extends ScNode> targetNodeIterator = targets.iterator();
        while (edgeIterator.hasNext() && sourceNodeIterator.hasNext() && targetNodeIterator.hasNext()) {
            var currentEdge = edgeIterator.next();
            assertEquals(currentEdge.getType(), expectedEdgeType);
            assertEquals(currentEdge.getSource(), sourceNodeIterator.next());
            assertEquals(currentEdge.getTarget(), targetNodeIterator.next());
        }

        assertEquals(edgeIterator.hasNext(), sourceNodeIterator.hasNext());
        assertEquals(sourceNodeIterator.hasNext(), targetNodeIterator.hasNext());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createNodesWithAllAvailableTypes() throws ScMemoryException {
        NodeType expectedNodeType = NodeType.NODE;

        var types = Arrays.stream(EdgeType.values()).collect(Collectors.toList());
        var sources = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType)
                        .limit(types.size()))
                .collect(Collectors.toList());
        var targets = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType)
                        .limit(types.size()))
                .collect(Collectors.toList());
        var edges = scContext.createEdges(types.stream(), sources.stream(), targets.stream());

        Iterator<ScEdge> edgeIterator = edges.iterator();
        Iterator<EdgeType> edgeTypeIterator = types.iterator();
        Iterator<? extends ScNode> sourceNodeIterator = sources.iterator();
        Iterator<? extends ScNode> targetNodeIterator = targets.iterator();
        while (edgeIterator.hasNext() && sourceNodeIterator.hasNext() && targetNodeIterator.hasNext()) {
            var currentEdge = edgeIterator.next();
            assertEquals(currentEdge.getType(), edgeTypeIterator.next());
            assertEquals(currentEdge.getSource(), sourceNodeIterator.next());
            assertEquals(currentEdge.getTarget(), targetNodeIterator.next());
        }

        assertEquals(edgeIterator.hasNext(), sourceNodeIterator.hasNext());
        assertEquals(sourceNodeIterator.hasNext(), targetNodeIterator.hasNext());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createTwoEdgesOneByOne() throws ScMemoryException {
        ScNode source = scContext.createNode(NodeType.NODE);
        ScNode target = scContext.createNode(NodeType.NODE);
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target);
        ScEdge edge2 = scContext.createEdge(EdgeType.ACCESS, source, target);
        assertEquals(edge.getType(), EdgeType.ACCESS);
        assertEquals(edge.getSource(), source);
        assertEquals(edge.getTarget(), target);
        assertEquals(edge2.getType(), EdgeType.ACCESS);
        assertEquals(edge2.getSource(), source);
        assertEquals(edge2.getTarget(), target);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteMultipleEdgesWithOneType() throws ScMemoryException {
        NodeType expectedNodeType = NodeType.NODE;
        EdgeType expectedEdgeType = EdgeType.ACCESS;
        int count = 10;

        var sources = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType).limit(count)).collect(Collectors.toList());
        var targets = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType).limit(count)).collect(Collectors.toList());
        var types = Stream.iterate(expectedEdgeType, e -> expectedEdgeType).limit(count);
        var edges = scContext.createEdges(types, sources.stream(), targets.stream());

        scContext.deleteElements(edges);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteEdge() throws ScMemoryException {
        ScNode source = scContext.createNode(NodeType.NODE);
        ScNode target = scContext.createNode(NodeType.NODE);
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target);
        boolean result = scContext.deleteElement(edge);
        assertTrue(result);
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingWithPausesEdges() throws InterruptedException, ScMemoryException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode source = scContext.createNode(NodeType.NODE);
            ScNode target = scContext.createNode(NodeType.NODE);
            ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target);
            Thread.sleep(ThreadLocalRandom.current().nextInt(0, 10));
            assertEquals(edge.getType(), EdgeType.ACCESS);
            assertEquals(edge.getSource(), source);
            assertEquals(edge.getTarget(), target);
        }
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingEdges() throws ScMemoryException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode source = scContext.createNode(NodeType.NODE);
            ScNode target = scContext.createNode(NodeType.NODE);
            ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target);
            assertEquals(edge.getType(), EdgeType.ACCESS);
            assertEquals(edge.getSource(), source);
            assertEquals(edge.getTarget(), target);
        }
    }

//    @Test
//    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
//    void scMemoryExceptionAtEdgeCreating(){
//        assertThrows(ScMemoryException.class, ()->{
//            ScNode source = scContext.createNode(NodeType.NODE);
//            ScNode target = scContext.createNode(NodeType.NODE);
//            ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target);
//            assertEquals(edge.getType(), EdgeType.ACCESS);
//            assertEquals(edge.getSource(), source);
//            assertEquals(edge.getTarget(), target);
//        });
//    }
}
