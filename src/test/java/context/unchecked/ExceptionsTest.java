package context.unchecked;

import org.jmantic.api.context.UncheckedScContext;
import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author artrayme
 * 12/7/21
 */
public class ExceptionsTest {
    ScMemory memory;

    private UncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws Exception {
        memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scContext = new UncheckedScContext(memory);
    }

    @Test
    public void exceptionAtNodeCreating() {
        assertThrows(RuntimeException.class, () -> {
            scContext.createNodes(Stream.of(new NodeType[]{NodeType.ABSTRACT}));
        });
    }

    @Test
    public void exceptionAtNodesCreating() {
        assertThrows(RuntimeException.class, () -> {
            scContext.createNode(NodeType.ABSTRACT);
        });
    }


    @Test
    public void exceptionAtEdgeCreating() throws Exception {
        memory.open();
        ScNode source = scContext.createNode(NodeType.NODE);
        ScNode target = scContext.createNode(NodeType.NODE);
        memory.close();
        assertThrows(RuntimeException.class, () -> {
            scContext.createEdge(EdgeType.ACCESS, source, target);
        });
    }

    @Test
    public void exceptionAtEdgesCreating() throws Exception {
        memory.open();
        ScNode source = scContext.createNode(NodeType.NODE);
        ScNode target = scContext.createNode(NodeType.NODE);
        memory.close();
        assertThrows(RuntimeException.class, () -> {
            scContext.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(source), Stream.of(target));
        });
    }

    @Test
    void exceptionAtIntegerLink() {
        assertThrows(RuntimeException.class, () -> {
            scContext.createIntegerLink(LinkType.LINK, 5);
        });
    }

    @Test
    void exceptionAtFloatLink() {
        assertThrows(RuntimeException.class, () -> {
            scContext.createFloatLink(LinkType.LINK, 5.0f);
        });
    }

    @Test
    void exceptionAtStringLink() {
        assertThrows(RuntimeException.class, () -> {
            scContext.createStringLink(LinkType.LINK, "qwe");
        });
    }

}
