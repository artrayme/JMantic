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
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;
import org.ostis.scmemory.websocketmemory.memory.pattern.SearchingPatternTriple;
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

    /**
     * <pre>
     *     {@code
     *                         ?edge(Edge.Access)
     *         source(Node)----------------------->?target(Node)
     *     }
     * </pre>
     */
    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findSingleTripleFNodeEdgeNode() throws Exception {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(
                Stream.of(EdgeType.ACCESS),
                Stream.of(source),
                Stream.of(target)
        ).findFirst().get();

        ScPattern pattern = new DefaultWebsocketScPattern();
        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(source),
                new TypePatternElement<>(
                        EdgeType.ACCESS,
                        new AliasPatternElement("edge1")
                ),
                new TypePatternElement<>(
                        NodeType.NODE,
                        new AliasPatternElement("node2")
                )
        ));

        var result = scMemory.find(pattern).findFirst().get().toList();

        assertEquals(source, result.get(0));
        assertEquals(target, result.get(2));
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, result.get(1));
    }

    /**
     * <pre>
     *     {@code
     *                         ?edge(Edge.Access)
     *        ?source(Node)----------------------->?target(Node)
     *                                 ^
     *                                 |
     *                                 | ?relEdge(Edge.Access)
     *                                 |
     *                                 |
     *                            relNode(Node)
     *
     *     }
     * </pre>
     *
     * Source and targets node are not included in pattern, but should be found.
     */
    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternWithDeepness3() throws ScMemoryException {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge = scMemory.createEdges(
                Stream.of(EdgeType.ACCESS),
                Stream.of(source),
                Stream.of(target)
        ).findFirst().get();

        ScNode relNode = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge relEdge = scMemory.createEdges(
                Stream.of(EdgeType.ACCESS),
                Stream.of(relNode),
                Stream.of(edge)
        ).findFirst().get();

        ScPattern pattern = new DefaultWebsocketScPattern();
        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(relNode),
                new TypePatternElement<>(
                        EdgeType.ACCESS,
                        new AliasPatternElement("edge1")
                ),
                new TypePatternElement<>(
                        EdgeType.ACCESS,
                        new AliasPatternElement("edge2")
                )
        ));

        var result = scMemory.find(pattern).findFirst().get().toList();

        assertEquals(relNode, result.get(0));
        assertEquals(relEdge, result.get(1));
        assertEquals(edge, result.get(2));
        assertEquals(edge.getSource(), ((ScEdge) result.get(2)).getSource());
        assertEquals(edge.getTarget(), ((ScEdge) result.get(2)).getTarget());
    }

    /**
     * <pre>
     *     {@code
     *
     *
     *     ?target1                      ?target2
     *         ^                             ^
     *         |                             |
     *         |             edge3           |
     *  ?edge1 |---------------------------->| ?edge2
     *         |                             |
     *         |                             |
     *         |                             |
     *         |                             |
     *     ?source1                       ?source2
     *
     *
     *     }
     * </pre>
     *
     * Only edge3 is fixed, but all the elements will be found.
     * It because in JMantic each edge MUST have known source and target elements.
     */

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternOf3Edges() throws Exception {
        ScNode source1 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScNode target1 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge1 = scMemory.createEdges(
                Stream.of(EdgeType.ACCESS),
                Stream.of(source1),
                Stream.of(target1)
        ).findFirst().get();

        ScNode source2 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScNode target2 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
        ScEdge edge2 = scMemory.createEdges(
                Stream.of(EdgeType.D_COMMON),
                Stream.of(source2),
                Stream.of(target2)
        ).findFirst().get();

        ScEdge edge3 = scMemory.createEdges(
                Stream.of(EdgeType.ACCESS),
                Stream.of(edge1),
                Stream.of(edge2)
        ).findFirst().get();


        ScPattern pattern = new DefaultWebsocketScPattern();
        pattern.addElement(new SearchingPatternTriple(
                new TypePatternElement<>(
                        EdgeType.ACCESS,
                        new AliasPatternElement("edge1")
                ),
                new FixedPatternElement(edge3),
                new TypePatternElement<>(
                        EdgeType.D_COMMON,
                        new AliasPatternElement("edge2")
                )
        ));

        var result = scMemory.find(pattern).findFirst().get().toList();

        var resultEdge1 = ((ScEdge) result.get(0));
        var resultEdge2 = ((ScEdge) result.get(2));
        assertEquals(edge1, resultEdge1);
        assertEquals(edge2, resultEdge2);
        assertEquals(source1, resultEdge1.getSource());
        assertEquals(target1, resultEdge1.getTarget());
        assertEquals(source2, resultEdge2.getSource());
        assertEquals(target2, resultEdge2.getTarget());
    }
}
