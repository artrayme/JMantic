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
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
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

//ToDO multithreading tests
public class EdgeOperationsTest {
    private AsyncUncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws URISyntaxException {
        scContext = new AsyncUncheckedScContext(new UncheckedScContext(new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"))));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleEdge() throws ExecutionException, InterruptedException {
        ScNode source = scContext.createNode(NodeType.NODE).get();
        ScNode target = scContext.createNode(NodeType.NODE).get();
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target).get();
        assertEquals(edge.getType(), EdgeType.ACCESS);
        assertEquals(edge.getSource(), source);
        assertEquals(edge.getTarget(), target);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleEdgesWithOneType() throws ExecutionException, InterruptedException {
        NodeType expectedNodeType = NodeType.NODE;
        EdgeType expectedEdgeType = EdgeType.ACCESS;
        int count = 10;

        var sources = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType).limit(count))
                .get()
                .collect(Collectors.toList());
        var targets = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType).limit(count))
                .get()
                .collect(Collectors.toList());
        var types = Stream.iterate(expectedEdgeType, e -> expectedEdgeType).limit(count);
        var edges = scContext.createEdges(types, sources.stream(), targets.stream()).get();

        Iterator<ScEdge> edgeIterator = edges.iterator();
        Iterator<ScNode> sourceNodeIterator = sources.iterator();
        Iterator<ScNode> targetNodeIterator = targets.iterator();
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
    void createNodesWithAllAvailableTypes() throws ExecutionException, InterruptedException {
        NodeType expectedNodeType = NodeType.NODE;

        var types = Arrays.stream(EdgeType.values()).collect(Collectors.toList());
        var sources = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType)
                        .limit(types.size()))
                .get()
                .collect(Collectors.toList());
        var targets = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType)
                        .limit(types.size()))
                .get()
                .collect(Collectors.toList());
        var edges = scContext.createEdges(types.stream(), sources.stream(), targets.stream()).get();

        Iterator<ScEdge> edgeIterator = edges.iterator();
        Iterator<EdgeType> edgeTypeIterator = types.iterator();
        Iterator<ScNode> sourceNodeIterator = sources.iterator();
        Iterator<ScNode> targetNodeIterator = targets.iterator();
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
    void createTwoEdgesOneByOne() throws ExecutionException, InterruptedException {
        ScNode source = scContext.createNode(NodeType.NODE).get();
        ScNode target = scContext.createNode(NodeType.NODE).get();
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target).get();
        ScEdge edge2 = scContext.createEdge(EdgeType.ACCESS, source, target).get();
        assertEquals(edge.getType(), EdgeType.ACCESS);
        assertEquals(edge.getSource(), source);
        assertEquals(edge.getTarget(), target);
        assertEquals(edge2.getType(), EdgeType.ACCESS);
        assertEquals(edge2.getSource(), source);
        assertEquals(edge2.getTarget(), target);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteMultipleEdgesWithOneType() throws ExecutionException, InterruptedException {
        NodeType expectedNodeType = NodeType.NODE;
        EdgeType expectedEdgeType = EdgeType.ACCESS;
        int count = 10;

        var sources = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType).limit(count))
                .get()
                .collect(Collectors.toList());
        var targets = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType).limit(count))
                .get()
                .collect(Collectors.toList());
        var types = Stream.iterate(expectedEdgeType, e -> expectedEdgeType).limit(count);
        var edges = scContext.createEdges(types, sources.stream(), targets.stream()).get();

        scContext.deleteElements(edges);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteEdge() throws ExecutionException, InterruptedException {
        ScNode source = scContext.createNode(NodeType.NODE).get();
        ScNode target = scContext.createNode(NodeType.NODE).get();
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target).get();
        boolean result = scContext.deleteElement(edge).get();
        assertTrue(result);
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingWithPausesEdges() throws InterruptedException, ExecutionException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode source = scContext.createNode(NodeType.NODE).get();
            ScNode target = scContext.createNode(NodeType.NODE).get();
            ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target).get();
            Thread.sleep(ThreadLocalRandom.current().nextInt(0, 10));
            assertEquals(edge.getType(), EdgeType.ACCESS);
            assertEquals(edge.getSource(), source);
            assertEquals(edge.getTarget(), target);
        }
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingEdges() throws ExecutionException, InterruptedException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode source = scContext.createNode(NodeType.NODE).get();
            ScNode target = scContext.createNode(NodeType.NODE).get();
            ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target).get();
            assertEquals(edge.getType(), EdgeType.ACCESS);
            assertEquals(edge.getSource(), source);
            assertEquals(edge.getTarget(), target);
        }
    }
}
