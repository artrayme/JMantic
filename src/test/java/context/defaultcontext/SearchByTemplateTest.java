package context.defaultcontext;

import org.ostis.api.context.DefaultScContext;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.link.LinkContentType;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLinkFloat;
import org.ostis.scmemory.model.element.link.ScLinkInteger;
import org.ostis.scmemory.model.element.link.ScLinkString;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern3Factory;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern5Factory;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;
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
    void createSingleTripleNodeEdgeStringLink() throws ScMemoryException {
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
    void createSingleTripleNodeIntegerLink() throws ScMemoryException {
        ScNode source = scContext.createNode(NodeType.NODE);
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, 5);
        ScEdge edge1 = scContext.createEdge(EdgeType.ACCESS, source, link);
        var result = scContext.findAllConstructionsNodeEdgeLink(source, EdgeType.ACCESS, LinkType.LINK_VAR, LinkContentType.INT).toList();
        assertEquals(source, result.get(0).getSource());
        assertEquals(link, result.get(0).getTarget());
        assertEquals(link.getContent(), ((ScLinkInteger) result.get(0).getTarget()).getContent());
        assertEquals(edge1, result.get(0));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleTripleNodeFloatLink() throws ScMemoryException {
        ScNode source = scContext.createNode(NodeType.NODE);
        ScLinkFloat link = scContext.createFloatLink(LinkType.LINK, 5f);
        ScEdge edge1 = scContext.createEdge(EdgeType.ACCESS, source, link);
        var result = scContext.findAllConstructionsNodeEdgeLink(source, EdgeType.ACCESS, LinkType.LINK_VAR, LinkContentType.FLOAT).toList();
        assertEquals(source, result.get(0).getSource());
        assertEquals(link, result.get(0).getTarget());
        assertEquals(link.getContent(), ((ScLinkFloat) result.get(0).getTarget()).getContent());
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

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findSingleTripleFNodeEdgeNode() throws ScMemoryException {
        ScNode source = scContext.createNode(NodeType.NODE);
        ScNode target = scContext.createNode(NodeType.NODE);
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target);
        var x = scContext.find(DefaultScPattern3Factory.get(source, EdgeType.ACCESS, NodeType.NODE)).findFirst().get();
        assertEquals(source, x.get1());
        assertEquals(target, x.get3());
        assertEquals(target, edge.getTarget());
        assertEquals(source, edge.getSource());
        assertEquals(edge, x.getEdge());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFNodeEdgeNodeEdgeFNode() throws ScMemoryException {
        ScNode source = scContext.createNode(NodeType.NODE);
        ScNode target = scContext.createNode(NodeType.NODE);
        ScEdge edge = scContext.createEdge(EdgeType.ACCESS, source, target);

        ScNode relNode = scContext.createNode(NodeType.NODE);
        ScEdge relEdge = scContext.createEdge(EdgeType.ACCESS, relNode, edge);

        var result = scContext.find(DefaultScPattern5Factory.get(
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
