package scmemory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;

import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author artrayme
 * @since 0.6.0
 */

public class EdgeOperationsTest {
    private ScMemory scMemory;

    @BeforeEach
    public void init() throws Exception {
        scMemory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scMemory.open();
    }

    @AfterEach
    public void shutdown() throws Exception {
        scMemory.close();
    }


    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleEdge() throws ScMemoryException {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();
        assertEquals(
                edge.getType(),
                EdgeType.ACCESS);
        assertEquals(
                edge.getSource(),
                source);
        assertEquals(
                edge.getTarget(),
                target);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleEdgesWithOneType() throws ScMemoryException {
        NodeType expectedNodeType = NodeType.NODE;
        EdgeType expectedEdgeType = EdgeType.ACCESS;
        int count = 10;

        var sources = scMemory.createNodes(Stream.iterate(
                                                         expectedNodeType,
                                                         e -> expectedNodeType)
                                                 .limit(count))
                              .toList();
        var targets = scMemory.createNodes(Stream.iterate(
                                                         expectedNodeType,
                                                         e -> expectedNodeType)
                                                 .limit(count))
                              .toList();
        var types = Stream.iterate(
                                  expectedEdgeType,
                                  e -> expectedEdgeType)
                          .limit(count);
        var edges = scMemory.createEdges(
                types,
                sources.stream(),
                targets.stream());

        Iterator<? extends ScEdge> edgeIterator = edges.iterator();
        Iterator<? extends ScNode> sourceNodeIterator = sources.iterator();
        Iterator<? extends ScNode> targetNodeIterator = targets.iterator();
        while (edgeIterator.hasNext() && sourceNodeIterator.hasNext() && targetNodeIterator.hasNext()) {
            var currentEdge = edgeIterator.next();
            assertEquals(
                    currentEdge.getType(),
                    expectedEdgeType);
            assertEquals(
                    currentEdge.getSource(),
                    sourceNodeIterator.next());
            assertEquals(
                    currentEdge.getTarget(),
                    targetNodeIterator.next());
        }

        assertEquals(
                edgeIterator.hasNext(),
                sourceNodeIterator.hasNext());
        assertEquals(
                sourceNodeIterator.hasNext(),
                targetNodeIterator.hasNext());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createNodesWithAllAvailableTypes() throws ScMemoryException {
        NodeType expectedNodeType = NodeType.NODE;

        var types = Arrays.stream(EdgeType.values())
                          .toList();
        var sources = scMemory.createNodes(Stream.iterate(
                                                         expectedNodeType,
                                                         e -> expectedNodeType)
                                                 .limit(types.size()))
                              .toList();
        var targets = scMemory.createNodes(Stream.iterate(
                                                         expectedNodeType,
                                                         e -> expectedNodeType)
                                                 .limit(types.size()))
                              .toList();
        var edges = scMemory.createEdges(
                types.stream(),
                sources.stream(),
                targets.stream());

        Iterator<? extends ScEdge> edgeIterator = edges.iterator();
        Iterator<EdgeType> edgeTypeIterator = types.iterator();
        Iterator<? extends ScNode> sourceNodeIterator = sources.iterator();
        Iterator<? extends ScNode> targetNodeIterator = targets.iterator();
        while (edgeIterator.hasNext() && sourceNodeIterator.hasNext() && targetNodeIterator.hasNext()) {
            var currentEdge = edgeIterator.next();
            assertEquals(
                    currentEdge.getType(),
                    edgeTypeIterator.next());
            assertEquals(
                    currentEdge.getSource(),
                    sourceNodeIterator.next());
            assertEquals(
                    currentEdge.getTarget(),
                    targetNodeIterator.next());
        }

        assertEquals(
                edgeIterator.hasNext(),
                sourceNodeIterator.hasNext());
        assertEquals(
                sourceNodeIterator.hasNext(),
                targetNodeIterator.hasNext());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createTwoEdgesOneByOne() throws ScMemoryException {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();
        ScEdge edge2 = scMemory.createEdges(
                                       Stream.of(EdgeType.ACCESS),
                                       Stream.of(source),
                                       Stream.of(target))
                               .findFirst()
                               .get();
        assertEquals(
                edge.getType(),
                EdgeType.ACCESS);
        assertEquals(
                edge.getSource(),
                source);
        assertEquals(
                edge.getTarget(),
                target);
        assertEquals(
                edge2.getType(),
                EdgeType.ACCESS);
        assertEquals(
                edge2.getSource(),
                source);
        assertEquals(
                edge2.getTarget(),
                target);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteMultipleEdgesWithOneType() throws ScMemoryException {
        NodeType expectedNodeType = NodeType.NODE;
        EdgeType expectedEdgeType = EdgeType.ACCESS;
        int count = 10;

        var sources = scMemory.createNodes(Stream.iterate(
                                                         expectedNodeType,
                                                         e -> expectedNodeType)
                                                 .limit(count))
                              .collect(Collectors.toList());
        var targets = scMemory.createNodes(Stream.iterate(
                                                         expectedNodeType,
                                                         e -> expectedNodeType)
                                                 .limit(count))
                              .collect(Collectors.toList());
        var types = Stream.iterate(
                                  expectedEdgeType,
                                  e -> expectedEdgeType)
                          .limit(count);
        var edges = scMemory.createEdges(
                types,
                sources.stream(),
                targets.stream());

        scMemory.deleteElements(edges);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteEdge() throws ScMemoryException {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();
        boolean result = scMemory.deleteElements(Stream.of(edge));
        assertTrue(result);
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingWithPausesEdges() throws InterruptedException, ScMemoryException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                    .findFirst()
                                    .get();
            ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                    .findFirst()
                                    .get();
            ScEdge edge = scMemory.createEdges(
                                          Stream.of(EdgeType.ACCESS),
                                          Stream.of(source),
                                          Stream.of(target))
                                  .findFirst()
                                  .get();
            Thread.sleep(ThreadLocalRandom.current()
                                          .nextInt(
                                                  0,
                                                  10));
            assertEquals(
                    edge.getType(),
                    EdgeType.ACCESS);
            assertEquals(
                    edge.getSource(),
                    source);
            assertEquals(
                    edge.getTarget(),
                    target);
        }
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingEdges() throws ScMemoryException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                    .findFirst()
                                    .get();
            ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                    .findFirst()
                                    .get();
            ScEdge edge = scMemory.createEdges(
                                          Stream.of(EdgeType.ACCESS),
                                          Stream.of(source),
                                          Stream.of(target))
                                  .findFirst()
                                  .get();
            assertEquals(
                    edge.getType(),
                    EdgeType.ACCESS);
            assertEquals(
                    edge.getSource(),
                    source);
            assertEquals(
                    edge.getTarget(),
                    target);
        }
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void exceptionAtEdgeCreating() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                            .findFirst()
                                            .get();
                    ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                            .findFirst()
                                            .get();
                    ScEdge edge = scMemory.createEdges(
                                                  Stream.of(),
                                                  Stream.of(source),
                                                  Stream.of(target))
                                          .findFirst()
                                          .get();
                });
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                            .findFirst()
                                            .get();
                    ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                            .findFirst()
                                            .get();
                    ScEdge edge = scMemory.createEdges(
                                                  Stream.of(EdgeType.ACCESS),
                                                  Stream.of(),
                                                  Stream.of(target))
                                          .findFirst()
                                          .get();
                });
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                            .findFirst()
                                            .get();
                    ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                            .findFirst()
                                            .get();
                    ScEdge edge = scMemory.createEdges(
                                                  Stream.of(EdgeType.ACCESS),
                                                  Stream.of(source),
                                                  Stream.of())
                                          .findFirst()
                                          .get();
                });
    }
}
