package scmemory;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.model.pattern.factory.ScPattern5FactoryWithNames;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScMemoryFindPattern5Test {
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
    void findPatternFNodeEdgeNodeEdgeFNode() throws ScMemoryException {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();

        ScNode relNode = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge relEdge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(relNode), Stream.of(edge)).findFirst().get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFNodeAEdgeANodeAEdgeFNodePattern(
                source,
                edge.getType(),
                target.getType(),
                relEdge.getType(),
                relNode)
        ).findFirst().get();

        assertEquals(source, result.get1());
        assertEquals(edge, result.get2());
        assertEquals(target, result.get3());
        assertEquals(relEdge, result.get4());
        assertEquals(relNode, result.get5());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFNodeEdgeLinkEdgeFNode() throws ScMemoryException {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScLink target = scMemory.createIntegerLinks(Stream.of(LinkType.LINK), Stream.of(10)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();

        ScNode relNode = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge relEdge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(relNode), Stream.of(edge)).findFirst().get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFNodeAEdgeALinkAEdgeFNodePattern(
                source,
                edge.getType(),
                target.getType(),
                relEdge.getType(),
                relNode)
        ).findFirst().get();

        assertEquals(source, result.get1());
        assertEquals(edge, result.get2());
        assertEquals(target, result.get3());
        assertEquals(relEdge, result.get4());
        assertEquals(relNode, result.get5());
    }
}
