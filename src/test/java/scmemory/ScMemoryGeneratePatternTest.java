package scmemory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLink;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.pattern.ScPattern;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;
import org.ostis.scmemory.websocketmemory.memory.pattern.GeneratingPatternTriple;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.AliasPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.FixedPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.TypePatternElement;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScMemoryGeneratePatternTest {
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

        ScPattern pattern = new DefaultWebsocketScPattern();
        pattern.addElement(new GeneratingPatternTriple(
                new FixedPatternElement(source),
                new TypePatternElement<>(
                        EdgeType.ACCESS_VAR_POS_PERM,
                        new AliasPatternElement("edge1")
                ),
                new TypePatternElement<>(
                        LinkType.LINK,
                        new AliasPatternElement("_link2")
                )
        ));

        var generated = scMemory.generate(pattern).toList();

        assertEquals(source, generated.get(0));

        var found = scMemory.find(pattern)
                             .findFirst()
                             .get()
                             .toList();

        assertEquals(EdgeType.ACCESS_VAR_POS_PERM, ((ScEdge) found.get(1)).getType());
        assertEquals(generated.get(1), found.get(1));
        assertEquals(LinkType.LINK, ((ScLink) found.get(2)).getType());
        assertEquals(generated.get(2), found.get(2));
    }
}
