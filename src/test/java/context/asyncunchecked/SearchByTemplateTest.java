package context.asyncunchecked;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.api.context.AsyncUncheckedScContext;
import org.ostis.api.context.UncheckedScContext;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.pattern.ScPattern;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern3Factory;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern5Factory;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;
import org.ostis.scmemory.websocketmemory.memory.pattern.SearchingPatternTriple;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.AliasPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.FixedPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.TypePatternElement;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author artrayme
 * @since 0.2.0
 */

//ToDO multithreading tests
public class SearchByTemplateTest {
    ScMemory memory;

    private AsyncUncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws Exception {
        memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scContext = new AsyncUncheckedScContext(new UncheckedScContext(memory));
        memory.open();
    }

    @AfterEach
    public void closeScMemory() throws Exception {
        memory.close();
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findSingleTripleFNodeEdgeNode() throws Exception {
        ScNode source = scContext.createNode(NodeType.NODE)
                                 .get();
        ScNode target = scContext.createNode(NodeType.NODE)
                                 .get();
        ScEdge edge = scContext.createEdge(
                                       EdgeType.ACCESS,
                                       source,
                                       target)
                               .get();
        var x = scContext.find(DefaultScPattern3Factory.get(
                                 source,
                                 EdgeType.ACCESS,
                                 NodeType.NODE))
                         .get()
                         .findFirst()
                         .get();
        assertEquals(
                source,
                x.get1());
        assertEquals(
                target,
                x.get3());
        assertEquals(
                target,
                edge.getTarget());
        assertEquals(
                source,
                edge.getSource());
        assertEquals(
                edge,
                x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFNodeEdgeNodeEdgeFNode() throws ExecutionException, InterruptedException {
        ScNode source = scContext.createNode(NodeType.NODE)
                                 .get();
        ScNode target = scContext.createNode(NodeType.NODE)
                                 .get();
        ScEdge edge = scContext.createEdge(
                                       EdgeType.ACCESS,
                                       source,
                                       target)
                               .get();

        ScNode relNode = scContext.createNode(NodeType.NODE)
                                  .get();
        ScEdge relEdge = scContext.createEdge(
                                          EdgeType.ACCESS,
                                          relNode,
                                          edge)
                                  .get();

        var result = scContext.find(DefaultScPattern5Factory.get(
                                      source,
                                      edge.getType(),
                                      target.getType(),
                                      relEdge.getType(),
                                      relNode))
                              .get()
                              .findFirst()
                              .get();

        assertEquals(
                source,
                result.get1());
        assertEquals(
                edge,
                result.get2());
        assertEquals(
                target,
                result.get3());
        assertEquals(
                relEdge,
                result.get4());
        assertEquals(
                relNode,
                result.get5());
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
     * <p>
     * Only edge3 is fixed, but all the elements will be found.
     * It because in JMantic each edge MUST have known source and target elements.
     */

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternOf3Edges() throws ExecutionException, InterruptedException {
        ScNode source1 = scContext.createNode(NodeType.NODE)
                                  .get();
        ScNode target1 = scContext.createNode(NodeType.NODE)
                                  .get();
        ScEdge edge1 = scContext.createEdge(
                                        (EdgeType.ACCESS),
                                        (source1),
                                        (target1))
                                .get();

        ScNode source2 = scContext.createNode(NodeType.NODE)
                                  .get();
        ScNode target2 = scContext.createNode(NodeType.NODE)
                                  .get();
        ScEdge edge2 = scContext.createEdge(
                                        (EdgeType.D_COMMON),
                                        (source2),
                                        (target2))
                                .get();

        ScEdge edge3 = scContext.createEdge(
                                        (EdgeType.ACCESS),
                                        (edge1),
                                        (edge2))
                                .get();


        ScPattern pattern = new DefaultWebsocketScPattern();
        pattern.addElement(new SearchingPatternTriple(
                new TypePatternElement<>(
                        EdgeType.ACCESS,
                        new AliasPatternElement("edge1")),
                new FixedPatternElement(edge3),
                new TypePatternElement<>(
                        EdgeType.D_COMMON,
                        new AliasPatternElement("edge2"))));

        var result = scContext.find(pattern)
                              .get()
                              .findFirst()
                              .get()
                              .toList();

        var resultEdge1 = ((ScEdge) result.get(0));
        var resultEdge2 = ((ScEdge) result.get(2));
        assertEquals(
                edge1,
                resultEdge1);
        assertEquals(
                edge2,
                resultEdge2);
        assertEquals(
                source1,
                resultEdge1.getSource());
        assertEquals(
                target1,
                resultEdge1.getTarget());
        assertEquals(
                source2,
                resultEdge2.getSource());
        assertEquals(
                target2,
                resultEdge2.getTarget());
    }

}
