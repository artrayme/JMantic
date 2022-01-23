package context.unchecked;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ostis.api.context.UncheckedScContext;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.pattern.ScPattern;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern3Factory;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern5Factory;
import org.ostis.scmemory.model.pattern.pattern3.ScPattern3;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;
import org.ostis.scmemory.websocketmemory.memory.pattern.SearchingPatternTriple;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.FixedPatternElement;

import java.net.URI;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author artrayme
 * 12/7/21
 */
public class ExceptionsTest {
    private ScMemory memory;

    private UncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws Exception {
        memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scContext = new UncheckedScContext(memory);
    }

    @Test
    public void exceptionAtNodeCreating() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.createNodes(Stream.of(new NodeType[]{NodeType.ABSTRACT}));
                });
    }

    @Test
    public void exceptionAtNodesCreating() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.createNode(NodeType.ABSTRACT);
                });
    }


    @Test
    public void exceptionAtEdgeCreating() throws Exception {
        memory.open();
        ScNode source = scContext.createNode(NodeType.NODE);
        ScNode target = scContext.createNode(NodeType.NODE);
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.createEdge(
                            EdgeType.ACCESS,
                            source,
                            target);
                });
    }

    @Test
    public void exceptionAtEdgesCreating() throws Exception {
        memory.open();
        ScNode source = scContext.createNode(NodeType.NODE);
        ScNode target = scContext.createNode(NodeType.NODE);
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.createEdges(
                            Stream.of(EdgeType.ACCESS),
                            Stream.of(source),
                            Stream.of(target));
                });
    }

    @Test
    void exceptionAtIntegerLinkCreating() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.createIntegerLink(
                            LinkType.LINK,
                            5);
                });
    }

    @Test
    void exceptionAtFloatLinkCreating() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.createFloatLink(
                            LinkType.LINK,
                            5.0f);
                });
    }

    @Test
    void exceptionAtStringLinkCreating() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.createStringLink(
                            LinkType.LINK,
                            "qwe");
                });
    }

    @Test
    void exceptionAtIntegerLinkContentSetting() throws Exception {
        memory.open();
        var link = scContext.createIntegerLink(
                LinkType.LINK,
                5);
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.setIntegerLinkContent(
                            link,
                            10);
                });
    }

    @Test
    void exceptionAtFloatLinkSetting() throws Exception {
        memory.open();
        var link = scContext.createFloatLink(
                LinkType.LINK,
                5f);
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.setFloatLinkContent(
                            link,
                            10f);
                });
    }

    @Test
    void exceptionAtStringLinkSetting() throws Exception {
        memory.open();
        var link = scContext.createStringLink(
                LinkType.LINK,
                "qwe");
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.setStringLinkContent(
                            link,
                            "other");
                });
    }

    @Test
    void exceptionAtIntegerLinkContentGetting() throws Exception {
        memory.open();
        var link = scContext.createIntegerLink(
                LinkType.LINK,
                5);
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.getIntegerLinkContent(link);
                });
    }

    @Test
    void exceptionAtFloatLinkGetting() throws Exception {
        memory.open();
        var link = scContext.createFloatLink(
                LinkType.LINK,
                5f);
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.getFloatLinkContent(link);
                });
    }

    @Test
    void exceptionAtStringLinkGetting() throws Exception {
        memory.open();
        var link = scContext.createStringLink(
                LinkType.LINK,
                "qwe");
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.getStringLinkContent(link);
                });
    }

    @Test
    void exceptionAtKeynodeFinding() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.findKeynode("no_sense");
                });
    }

    @Test
    void exceptionAtKeynodeResolving() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.resolveKeynode(
                            "no_sense",
                            NodeType.NODE);
                });
    }

    @Test
    void exceptionAtElementDeleting() throws Exception {
        memory.open();
        ScNode node = scContext.createNode(NodeType.NODE);
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.deleteElement(node);
                });
    }

    @Test
    void exceptionAtMultipleElementsDeleting() throws Exception {
        memory.open();
        ScNode node1 = scContext.createNode(NodeType.NODE);
        ScNode node2 = scContext.createNode(NodeType.NODE);
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.deleteElements(Stream.of(
                            node1,
                            node2));
                });
    }

    @Test
    void exceptionAtPattern3Searching() throws Exception {

        memory.open();
        ScNode node1 = scContext.createNode(NodeType.NODE);
        ScNode node2 = scContext.createNode(NodeType.NODE);
        ScEdge edge = scContext.createEdge(
                EdgeType.ACCESS,
                node1,
                node2);
        ScPattern3<ScNode, NodeType, ScNode> pattern = DefaultScPattern3Factory.get(
                node1,
                EdgeType.ACCESS,
                NodeType.NODE);
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.find(pattern);
                });
    }

    @Test
    void exceptionAtPattern5Searching() throws Exception {

        memory.open();
        ScNode node1 = scContext.createNode(NodeType.NODE);
        ScNode node2 = scContext.createNode(NodeType.NODE);
        ScNode node3 = scContext.createNode(NodeType.NODE);
        ScEdge edge1 = scContext.createEdge(
                EdgeType.ACCESS,
                node1,
                node2);
        ScEdge edge2 = scContext.createEdge(
                EdgeType.ACCESS,
                node3,
                edge1);
        var pattern = DefaultScPattern5Factory.get(
                node1,
                EdgeType.ACCESS,
                NodeType.NODE,
                EdgeType.ACCESS,
                node3);
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.find(pattern);
                });
    }

    @Test
    void exceptionAtPatternSearching() throws Exception {
        memory.open();
        ScNode node1 = scContext.createNode(NodeType.NODE);
        ScNode node2 = scContext.createNode(NodeType.NODE);
        ScEdge edge = scContext.createEdge(
                EdgeType.ACCESS,
                node1,
                node2);
        ScPattern pattern = new DefaultWebsocketScPattern();
        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(node1),
                new FixedPatternElement(node2),
                new FixedPatternElement(edge)));
        memory.close();
        assertThrows(
                RuntimeException.class,
                () -> {
                    scContext.find(pattern);
                });
    }

}
