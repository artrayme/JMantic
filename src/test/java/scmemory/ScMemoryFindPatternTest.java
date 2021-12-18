package scmemory;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.pattern.pattern3.ScPattern3Factory;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScMemoryFindPatternTest {
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
    void createSingleTripleFNodeEdgeNode() throws Exception {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(ScPattern3Factory.getFNodeEdgeNodePattern(source, EdgeType.ACCESS, NodeType.NODE)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTripleFNodeEdgeLink() throws Exception {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScLink target = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(5)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(ScPattern3Factory.getFNodeEdgeLinkPattern(source, EdgeType.ACCESS, LinkType.LINK)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTripleFNodeEdgeFNode() throws Exception {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(ScPattern3Factory.getFNodeEdgeFNodePattern(source, EdgeType.ACCESS, target)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTripleFNodeEdgeFLink() throws Exception {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScLink target = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(5)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(ScPattern3Factory.getFNodeEdgeFLinkPattern(source, EdgeType.ACCESS, target)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTripleFLinkEdgeLink() throws Exception {
        ScLink source = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(10)).findFirst().get();
        ScLink target = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(5)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(ScPattern3Factory.getFLinkEdgeFLinkPattern(source, EdgeType.ACCESS, target)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTripleFLinkEdgeFLink() throws Exception {
        ScLink source = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(10)).findFirst().get();
        ScLink target = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(5)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(ScPattern3Factory.getFLinkEdgeLinkPattern(source, EdgeType.ACCESS, LinkType.LINK)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTripleFLinkEdgeNode() throws Exception {
        ScLink source = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(10)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(ScPattern3Factory.getFLinkEdgeNodePattern(source, EdgeType.ACCESS, NodeType.NODE)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTripleFLinkEdgeFNode() throws Exception {
        ScLink source = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(10)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();
        var x = scMemory.findByPattern3(ScPattern3Factory.getFLinkEdgeFNodePattern(source, EdgeType.ACCESS, target)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }


}
