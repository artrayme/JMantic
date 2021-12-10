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
    void exceptionAtIntegerLinkCreating() {
        assertThrows(RuntimeException.class, () -> {
            scContext.createIntegerLink(LinkType.LINK, 5);
        });
    }

    @Test
    void exceptionAtFloatLinkCreating() {
        assertThrows(RuntimeException.class, () -> {
            scContext.createFloatLink(LinkType.LINK, 5.0f);
        });
    }

    @Test
    void exceptionAtStringLinkCreating() {
        assertThrows(RuntimeException.class, () -> {
            scContext.createStringLink(LinkType.LINK, "qwe");
        });
    }

    @Test
    void exceptionAtIntegerLinkContentSetting() throws Exception {
        memory.open();
        var link = scContext.createIntegerLink(LinkType.LINK, 5);
        memory.close();
        assertThrows(RuntimeException.class, () -> {
            scContext.setIntegerLinkContent(link, 10);
        });
    }

    @Test
    void exceptionAtFloatLinkSetting() throws Exception {
        memory.open();
        var link = scContext.createFloatLink(LinkType.LINK, 5f);
        memory.close();
        assertThrows(RuntimeException.class, () -> {
            scContext.setFloatLinkContent(link, 10f);
        });
    }

    @Test
    void exceptionAtStringLinkSetting() throws Exception {
        memory.open();
        var link = scContext.createStringLink(LinkType.LINK, "qwe");
        memory.close();
        assertThrows(RuntimeException.class, () -> {
            scContext.setStringLinkContent(link, "other");
        });
    }

    @Test
    void exceptionAtIntegerLinkContentGetting() throws Exception {
        memory.open();
        var link = scContext.createIntegerLink(LinkType.LINK, 5);
        memory.close();
        assertThrows(RuntimeException.class, () -> {
            scContext.getIntegerLinkContent(link);
        });
    }

    @Test
    void exceptionAtFloatLinkGetting() throws Exception {
        memory.open();
        var link = scContext.createFloatLink(LinkType.LINK, 5f);
        memory.close();
        assertThrows(RuntimeException.class, () -> {
            scContext.getFloatLinkContent(link);
        });
    }

    @Test
    void exceptionAtStringLinkGetting() throws Exception {
        memory.open();
        var link = scContext.createStringLink(LinkType.LINK, "qwe");
        memory.close();
        assertThrows(RuntimeException.class, () -> {
            scContext.getStringLinkContent(link);
        });
    }

}
