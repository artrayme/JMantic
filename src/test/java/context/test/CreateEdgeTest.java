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
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        scContext = new DefaultScContext(SyncScMemory.getSyncScMemory(new URI("ws://localhost:8090/ws_json")));
    }

    @Test
    void createEdge() {
        ScNode source = scContext.createNode(NodeType.NODE);
        ScNode target = scContext.createNode(NodeType.NODE);
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target);
        assertEquals(edge.getType(), EdgeType.ACCESS);
        assertEquals(edge.getSource(), source);
        assertEquals(edge.getTarget(), target);
        assertTrue(true);
    }

    @Test
    void createEdgesComplex() {
        NodeType expectedNodeType = NodeType.NODE;
        EdgeType expectedEdgeType = EdgeType.ACCESS;
        int count = 10;

        var sources = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType).limit(count)).collect(Collectors.toList());
        var targets = scContext.createNodes(Stream.iterate(expectedNodeType, e -> expectedNodeType).limit(count)).collect(Collectors.toList());
        var types = Stream.iterate(expectedEdgeType, e -> expectedEdgeType).limit(count);
        var edges = scContext.createEdges(types, sources.stream(), targets.stream());

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
}
