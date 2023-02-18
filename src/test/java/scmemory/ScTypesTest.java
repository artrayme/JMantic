package scmemory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ScTypesTest {
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
    void nodeEqualityBasicRule() throws Exception {
        var node = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        assertEquals(node, node);
        assertNotEquals(node, null);
    }
    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void nodeEqualityBasicRuleFail() throws Exception {
        var node1 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        var node2 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        assertNotEquals(node1, node2);
        assertNotEquals(node2, node1);
    }
    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void nodeEqualityWithLinkRule() throws Exception {
        var node = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        var link = scMemory.createStringLinks(Stream.of(LinkType.LINK), Stream.of("content")).findFirst().get();
        assertNotEquals(node, link);
        assertNotEquals(link, node);
    }
    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void nodeEqualityWithEdgeRule() throws Exception {
        var node = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        var node1 = scMemory.createNodes(Stream.of(NodeType.NODE));
        var node2 = scMemory.createNodes(Stream.of(NodeType.NODE));
        var edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), node1, node2).findFirst().get();
        assertNotEquals(node, edge);
        assertNotEquals(edge, node);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void edgeEqualityBasicRule() throws Exception {
        var node1 = scMemory.createNodes(Stream.of(NodeType.NODE));
        var node2 = scMemory.createNodes(Stream.of(NodeType.NODE));
        var edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), node1, node2).findFirst().get();
        assertEquals(edge, edge);
        assertNotEquals(edge, null);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void edgeEqualityBasicRuleFail() throws Exception {
        var node1 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        var node2 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        var edge1 = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(node1), Stream.of(node2)).findFirst().get();
        var edge2 = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(node1), Stream.of(node2)).findFirst().get();
        assertNotEquals(edge1, edge2);
        assertNotEquals(edge2, edge1);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void edgeEqualityWithLinkRule() throws Exception {
        var node1 = scMemory.createNodes(Stream.of(NodeType.NODE));
        var node2 = scMemory.createNodes(Stream.of(NodeType.NODE));
        var edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), node1, node2).findFirst().get();
        var link = scMemory.createStringLinks(Stream.of(LinkType.LINK), Stream.of("content")).findFirst().get();
        assertNotEquals(link, edge);
        assertNotEquals(edge, link);
    }
}
