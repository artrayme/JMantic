package scmemory;

import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLink;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern3Factory;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScMemoryFindPattern3Test {
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
    void findSingleTripleFNodeEdgeNode() throws Exception {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(DefaultScPattern3Factory.get(source, EdgeType.ACCESS, NodeType.NODE)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findSingleTripleFNodeEdgeLink() throws Exception {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScLink target = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(5)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(DefaultScPattern3Factory.get(source, EdgeType.ACCESS, LinkType.LINK)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findSingleTripleFNodeEdgeFNode() throws Exception {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(DefaultScPattern3Factory.get(source, EdgeType.ACCESS, target)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findSingleTripleFNodeEdgeFLink() throws Exception {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScLink target = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(5)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(DefaultScPattern3Factory.get(source, EdgeType.ACCESS, target)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findSingleTripleFLinkEdgeLink() throws Exception {
        ScLink source = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(10)).findFirst().get();
        ScLink target = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(5)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(DefaultScPattern3Factory.get(source, EdgeType.ACCESS, target)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findSingleTripleFLinkEdgeFLink() throws Exception {
        ScLink source = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(10)).findFirst().get();
        ScLink target = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(5)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(DefaultScPattern3Factory.get(source, EdgeType.ACCESS, LinkType.LINK)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findSingleTripleFLinkEdgeNode() throws Exception {
        ScLink source = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(10)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(DefaultScPattern3Factory.get(source, EdgeType.ACCESS, NodeType.NODE)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findSingleTripleFLinkEdgeFNode() throws Exception {
        ScLink source = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(10)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(DefaultScPattern3Factory.get(source, EdgeType.ACCESS, target)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

}
