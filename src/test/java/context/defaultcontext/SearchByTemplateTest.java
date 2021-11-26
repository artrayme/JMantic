package context.defaultcontext;

import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkContentType;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author artrayme
 * @since 0.2.0
 */

//ToDO exceptions test
public class SearchByTemplateTest {
    ScMemory memory;
    private DefaultScContext scContext;

    @BeforeEach
    public void setUp() throws Exception {
        memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scContext = new DefaultScContext(memory);
        memory.open();
    }

    @AfterEach
    public void closeScMemory() throws Exception {
        memory.close();
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTripleNodeEdgeNode() throws ScMemoryException {
        ScNode source = scContext.createNode(NodeType.NODE);
        ScNode target1 = scContext.createNode(NodeType.NODE);
        ScEdge edge1 = scContext.createEdge(EdgeType.ACCESS, source, target1);
        var result = scContext.findAllConstructionsNodeEdgeNode(source, EdgeType.ACCESS, NodeType.NODE).toList();
        assertEquals(source, result.get(0).getSource());
        assertEquals(target1, result.get(0).getTarget());
        assertEquals(edge1, result.get(0));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTripleNodeEdgeLink() throws ScMemoryException {
        ScNode source = scContext.createNode(NodeType.NODE);
        ScLinkString link = scContext.createStringLink(LinkType.LINK, "Hello");
        ScEdge edge1 = scContext.createEdge(EdgeType.ACCESS, source, link);
        var result = scContext.findAllConstructionsNodeEdgeLink(source, EdgeType.ACCESS, LinkType.LINK_VAR, LinkContentType.STRING).toList();
        assertEquals(source, result.get(0).getSource());
        assertEquals(link, result.get(0).getTarget());
        assertEquals(link.getContent(), ((ScLinkString) result.get(0).getTarget()).getContent());
        assertEquals(edge1, result.get(0));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTripleNodeEdgeLinkWithRelation() throws ScMemoryException {
        ScNode source = scContext.createNode(NodeType.NODE);
        ScLinkString link = scContext.createStringLink(LinkType.LINK, "Hello");
        ScEdge edge1 = scContext.createEdge(EdgeType.ACCESS, source, link);

        ScNode rel = scContext.createNode(NodeType.NODE);
        ScEdge edge2 = scContext.createEdge(EdgeType.ACCESS, rel, edge1);

        var result = scContext.findAllConstructionsNodeEdgeLinkWithRelation(source, EdgeType.ACCESS, LinkType.LINK_VAR, LinkContentType.STRING, rel, EdgeType.ACCESS).toList();
        assertEquals(source, result.get(0).getSource());
        assertEquals(link, result.get(0).getTarget());
        assertEquals(link.getContent(), ((ScLinkString) result.get(0).getTarget()).getContent());
        assertEquals(edge1, result.get(0));
    }

}
