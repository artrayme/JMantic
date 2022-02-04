package scmemory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLinkFloat;
import org.ostis.scmemory.model.element.link.ScLinkInteger;
import org.ostis.scmemory.model.element.link.ScLinkString;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.model.pattern.factory.ScPattern5FactoryWithNames;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScMemoryFindPattern5WithNamesTest {
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
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScNode relNode = scMemory.createNodes(Stream.of(NodeType.NODE))
                                 .findFirst()
                                 .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relNode),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFNodeAEdgeANodeAEdgeFNodePattern(
                                     source,
                                     edge.getType(),
                                     target.getType(),
                                     relEdge.getType(),
                                     relNode))
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

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFNodeEdgeFNodeEdgeLink() throws ScMemoryException {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        String linkContent2 = "cde";
        ScLinkString relLink = scMemory.createStringLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent2))
                                       .findFirst()
                                       .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relLink),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFNodeAEdgeFNodeAEdgeALinkPattern(
                                     source,
                                     edge.getType(),
                                     target,
                                     relEdge.getType(),
                                     relLink.getType()))
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
                relLink,
                result.get5());
        assertEquals(
                relLink.getContent(),
                ((ScLinkString) result.get5()).getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFNodeEdgeLinkEdgeFNode() throws ScMemoryException {
        int linkContent = 10;
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScLinkInteger target = scMemory.createIntegerLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent))
                                       .findFirst()
                                       .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScNode relNode = scMemory.createNodes(Stream.of(NodeType.NODE))
                                 .findFirst()
                                 .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relNode),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFNodeAEdgeALinkAEdgeFNodePattern(
                                     source,
                                     edge.getType(),
                                     target.getType(),
                                     relEdge.getType(),
                                     relNode))
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
                target.getContent(),
                ((ScLinkInteger) result.get3()).getContent());
        assertEquals(
                relEdge,
                result.get4());
        assertEquals(
                relNode,
                result.get5());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFLinkEdgeLinkEdgeFNode() throws ScMemoryException {
        int linkContent = 10;
        ScLinkInteger source = scMemory.createIntegerLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent))
                                       .findFirst()
                                       .get();
        ScLinkInteger target = scMemory.createIntegerLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent))
                                       .findFirst()
                                       .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScNode relNode = scMemory.createNodes(Stream.of(NodeType.NODE))
                                 .findFirst()
                                 .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relNode),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFLinkAEdgeALinkAEdgeFNodePattern(
                                     source,
                                     edge.getType(),
                                     target.getType(),
                                     relEdge.getType(),
                                     relNode))
                             .findFirst()
                             .get();

        assertEquals(
                source,
                result.get1());
        assertEquals(
                source.getContent(),
                ((ScLinkInteger) result.get1()).getContent());
        assertEquals(
                edge,
                result.get2());
        assertEquals(
                target,
                result.get3());
        assertEquals(
                target.getContent(),
                ((ScLinkInteger) result.get3()).getContent());
        assertEquals(
                relEdge,
                result.get4());
        assertEquals(
                relNode,
                result.get5());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFLinkEdgeLinkEdgeFLink() throws ScMemoryException {
        int linkContent = 10;
        ScLinkInteger source = scMemory.createIntegerLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent))
                                       .findFirst()
                                       .get();
        ScLinkInteger target = scMemory.createIntegerLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent))
                                       .findFirst()
                                       .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScLinkInteger relLink = scMemory.createIntegerLinks(
                                                Stream.of(LinkType.LINK),
                                                Stream.of(linkContent))
                                        .findFirst()
                                        .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relLink),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFLinkAEdgeALinkAEdgeFLinkPattern(
                                     source,
                                     edge.getType(),
                                     target.getType(),
                                     relEdge.getType(),
                                     relLink))
                             .findFirst()
                             .get();

        assertEquals(
                source,
                result.get1());
        assertEquals(
                source.getContent(),
                ((ScLinkInteger) result.get1()).getContent());
        assertEquals(
                edge,
                result.get2());
        assertEquals(
                target,
                result.get3());
        assertEquals(
                target.getContent(),
                ((ScLinkInteger) result.get3()).getContent());
        assertEquals(
                relEdge,
                result.get4());
        assertEquals(
                relLink,
                result.get5());
        assertEquals(
                relLink.getContent(),
                ((ScLinkInteger) result.get5()).getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFLinkEdgeFLinkEdgeLink() throws ScMemoryException {
        int linkContent1 = 10;
        String linkContent2 = "abc";
        float linkContent3 = 6f;
        ScLinkInteger source = scMemory.createIntegerLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent1))
                                       .findFirst()
                                       .get();
        ScLinkString target = scMemory.createStringLinks(
                                              Stream.of(LinkType.LINK),
                                              Stream.of(linkContent2))
                                      .findFirst()
                                      .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScLinkFloat relLink = scMemory.createFloatLinks(
                                              Stream.of(LinkType.LINK),
                                              Stream.of(linkContent3))
                                      .findFirst()
                                      .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relLink),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFLinkAEdgeFLinkAEdgeALinkPattern(
                                     source,
                                     edge.getType(),
                                     target,
                                     relEdge.getType(),
                                     relLink.getType()))
                             .findFirst()
                             .get();

        assertEquals(
                source,
                result.get1());
        assertEquals(
                source.getContent(),
                ((ScLinkInteger) result.get1()).getContent());
        assertEquals(
                edge,
                result.get2());
        assertEquals(
                target,
                result.get3());
        assertEquals(
                target.getContent(),
                ((ScLinkString) result.get3()).getContent());
        assertEquals(
                relEdge,
                result.get4());
        assertEquals(
                relLink,
                result.get5());
        assertEquals(
                relLink.getContent(),
                ((ScLinkFloat) result.get5()).getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFLinkEdgeFLinkEdgeNode() throws ScMemoryException {
        int linkContent = 10;
        ScLinkInteger source = scMemory.createIntegerLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent))
                                       .findFirst()
                                       .get();
        ScLinkInteger target = scMemory.createIntegerLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent))
                                       .findFirst()
                                       .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScNode relNode = scMemory.createNodes(Stream.of(NodeType.NODE))
                                 .findFirst()
                                 .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relNode),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFLinkAEdgeFLinkAEdgeANodePattern(
                                     source,
                                     edge.getType(),
                                     target,
                                     relEdge.getType(),
                                     relNode.getType()))
                             .findFirst()
                             .get();

        assertEquals(
                source,
                result.get1());
        assertEquals(
                source.getContent(),
                ((ScLinkInteger) result.get1()).getContent());
        assertEquals(
                edge,
                result.get2());
        assertEquals(
                target,
                result.get3());
        assertEquals(
                target.getContent(),
                ((ScLinkInteger) result.get3()).getContent());
        assertEquals(
                relEdge,
                result.get4());
        assertEquals(
                relNode,
                result.get5());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFNodeEdgeNodeEdgeFLink() throws ScMemoryException {
        Integer linkContent = 10;
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScLinkInteger relLink = scMemory.createIntegerLinks(
                                                Stream.of(LinkType.LINK),
                                                Stream.of(linkContent))
                                        .findFirst()
                                        .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relLink),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFNodeAEdgeANodeAEdgeFLinkPattern(
                                     source,
                                     edge.getType(),
                                     target.getType(),
                                     relEdge.getType(),
                                     relLink))
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
                relLink,
                result.get5());
        assertEquals(
                relLink.getContent(),
                ((ScLinkInteger) result.get5()).getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFNodeEdgeLinkEdgeFLink() throws ScMemoryException {
        Integer linkContent = 10;
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScLinkInteger target = scMemory.createIntegerLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent))
                                       .findFirst()
                                       .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScLinkInteger relLink = scMemory.createIntegerLinks(
                                                Stream.of(LinkType.LINK),
                                                Stream.of(linkContent))
                                        .findFirst()
                                        .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relLink),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFNodeAEdgeALinkAEdgeFLinkPattern(
                                     source,
                                     edge.getType(),
                                     target.getType(),
                                     relEdge.getType(),
                                     relLink))
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
                target.getContent(),
                ((ScLinkInteger) result.get3()).getContent());
        assertEquals(
                relEdge,
                result.get4());
        assertEquals(
                relLink,
                result.get5());
        assertEquals(
                relLink.getContent(),
                ((ScLinkInteger) result.get5()).getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFNodeEdgeFLinkEdgeNode() throws ScMemoryException {
        Float linkContent = 10f;
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScLinkFloat target = scMemory.createFloatLinks(
                                             Stream.of(LinkType.LINK),
                                             Stream.of(linkContent))
                                     .findFirst()
                                     .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScNode relNode = scMemory.createNodes(Stream.of(NodeType.NODE))
                                 .findFirst()
                                 .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relNode),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFNodeAEdgeFLinkAEdgeANodePattern(
                                     source,
                                     edge.getType(),
                                     target,
                                     relEdge.getType(),
                                     relNode.getType()))
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
                target.getContent(),
                ((ScLinkFloat) result.get3()).getContent());
        assertEquals(
                relEdge,
                result.get4());
        assertEquals(
                relNode,
                result.get5());
    }


    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFNodeEdgeFLinkEdgeLink() throws ScMemoryException {
        String linkContent = "hello";
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScLinkString target = scMemory.createStringLinks(
                                              Stream.of(LinkType.LINK),
                                              Stream.of(linkContent))
                                      .findFirst()
                                      .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScLinkString relLink = scMemory.createStringLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent))
                                       .findFirst()
                                       .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relLink),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFNodeAEdgeFLinkAEdgeALinkPattern(
                                     source,
                                     edge.getType(),
                                     target,
                                     relEdge.getType(),
                                     relLink.getType()))
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
                target.getContent(),
                ((ScLinkString) result.get3()).getContent());
        assertEquals(
                relEdge,
                result.get4());
        assertEquals(
                relLink,
                result.get5());
        assertEquals(
                relLink.getContent(),
                ((ScLinkString) result.get5()).getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFNodeEdgeFNodeEdgeNode() throws ScMemoryException {
        ScNode source = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScNode relNode = scMemory.createNodes(Stream.of(NodeType.NODE))
                                 .findFirst()
                                 .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relNode),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFNodeAEdgeFNodeAEdgeANodePattern(
                                     source,
                                     edge.getType(),
                                     target,
                                     relEdge.getType(),
                                     relNode.getType()))
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

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFLinkEdgeNodeEdgeFNode() throws ScMemoryException {
        String linkContent = "-_-";
        ScLinkString source = scMemory.createStringLinks(
                                              Stream.of(LinkType.LINK),
                                              Stream.of(linkContent))
                                      .findFirst()
                                      .get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScNode relNode = scMemory.createNodes(Stream.of(NodeType.NODE))
                                 .findFirst()
                                 .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relNode),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFLinkAEdgeANodeAEdgeFNodePattern(
                                     source,
                                     edge.getType(),
                                     target.getType(),
                                     relEdge.getType(),
                                     relNode))
                             .findFirst()
                             .get();

        assertEquals(
                source,
                result.get1());
        assertEquals(
                source.getContent(),
                ((ScLinkString) result.get1()).getContent());
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

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFLinkEdgeNodeEdgeFLink() throws ScMemoryException {
        String linkContent = "-_-";
        ScLinkString source = scMemory.createStringLinks(
                                              Stream.of(LinkType.LINK),
                                              Stream.of(linkContent))
                                      .findFirst()
                                      .get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScLinkString relLink = scMemory.createStringLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent))
                                       .findFirst()
                                       .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relLink),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFLinkAEdgeANodeAEdgeFLinkPattern(
                                     source,
                                     edge.getType(),
                                     target.getType(),
                                     relEdge.getType(),
                                     relLink))
                             .findFirst()
                             .get();

        assertEquals(
                source,
                result.get1());
        assertEquals(
                source.getContent(),
                ((ScLinkString) result.get1()).getContent());
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
                relLink,
                result.get5());
        assertEquals(
                relLink.getContent(),
                ((ScLinkString) result.get5()).getContent());
    }


    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFLinkEdgeFNodeEdgeLink() throws ScMemoryException {
        String linkContent = "-_-";
        ScLinkString source = scMemory.createStringLinks(
                                              Stream.of(LinkType.LINK),
                                              Stream.of(linkContent))
                                      .findFirst()
                                      .get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScLinkString relLink = scMemory.createStringLinks(
                                               Stream.of(LinkType.LINK),
                                               Stream.of(linkContent))
                                       .findFirst()
                                       .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relLink),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFLinkAEdgeFNodeAEdgeALinkPattern(
                                     source,
                                     edge.getType(),
                                     target,
                                     relEdge.getType(),
                                     relLink.getType()))
                             .findFirst()
                             .get();

        assertEquals(
                source,
                result.get1());
        assertEquals(
                source.getContent(),
                ((ScLinkString) result.get1()).getContent());
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
                relLink,
                result.get5());
        assertEquals(
                relLink.getContent(),
                ((ScLinkString) result.get5()).getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void findPatternFLinkEdgeFNodeEdgeNode() throws ScMemoryException {
        String linkContent = "-_-";
        ScLinkString source = scMemory.createStringLinks(
                                              Stream.of(LinkType.LINK),
                                              Stream.of(linkContent))
                                      .findFirst()
                                      .get();
        ScNode target = scMemory.createNodes(Stream.of(NodeType.NODE))
                                .findFirst()
                                .get();
        ScEdge edge = scMemory.createEdges(
                                      Stream.of(EdgeType.ACCESS),
                                      Stream.of(source),
                                      Stream.of(target))
                              .findFirst()
                              .get();

        ScNode relNode = scMemory.createNodes(Stream.of(NodeType.NODE))
                                 .findFirst()
                                 .get();
        ScEdge relEdge = scMemory.createEdges(
                                         Stream.of(EdgeType.ACCESS),
                                         Stream.of(relNode),
                                         Stream.of(edge))
                                 .findFirst()
                                 .get();

        var result = scMemory.findByPattern5(ScPattern5FactoryWithNames.getFLinkAEdgeFNodeAEdgeANodePattern(
                                     source,
                                     edge.getType(),
                                     target,
                                     relEdge.getType(),
                                     relNode.getType()))
                             .findFirst()
                             .get();

        assertEquals(
                source,
                result.get1());
        assertEquals(
                source.getContent(),
                ((ScLinkString) result.get1()).getContent());
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
}
