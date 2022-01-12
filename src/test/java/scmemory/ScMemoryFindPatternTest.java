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
import org.ostis.scmemory.model.pattern.ScPattern;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;
import org.ostis.scmemory.websocketmemory.memory.pattern.BasicPatternTriple;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.AliasPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.FixedPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.TypePatternElement;

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
    void findSingleTripleFNodeEdgeNode() throws Exception {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();

        ScPattern pattern = new DefaultWebsocketScPattern();
        pattern.addElement(new BasicPatternTriple(
                new FixedPatternElement(source),
                new TypePatternElement<>(EdgeType.ACCESS, new AliasPatternElement("edge1")),
                new TypePatternElement<>(NodeType.NODE, new AliasPatternElement("node2"))
        ));

        var result = scMemory.find(pattern).findFirst().get().toList();

        assertEquals(source, result.get(0));
        assertEquals(target, result.get(2));
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, result.get(1));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFNodeEdgeNodeEdgeFNode() throws ScMemoryException {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target)).findFirst().get();

        ScNode relNode = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge relEdge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(relNode), Stream.of(edge)).findFirst().get();

        ScPattern pattern = new DefaultWebsocketScPattern();
        pattern.addElement(new BasicPatternTriple(
                new FixedPatternElement(relNode),
                new TypePatternElement<>(EdgeType.ACCESS, new AliasPatternElement("edge1")),
                new TypePatternElement<>(EdgeType.ACCESS, new AliasPatternElement("edge2"))
        ));

        var result = scMemory.find(pattern).findFirst().get().toList();

        assertEquals(relNode, result.get(0));
        assertEquals(relEdge, result.get(1));
        assertEquals(edge, result.get(2));
        assertEquals(edge.getSource(), ((ScEdge) result.get(2)).getSource());
        assertEquals(edge.getTarget(), ((ScEdge) result.get(2)).getTarget());
    }
}
