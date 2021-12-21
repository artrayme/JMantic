package org.ostis.api.context;

import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.ScElement;
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
import org.ostis.scmemory.model.pattern.ScConstruction3;
import org.ostis.scmemory.model.pattern.ScConstruction5;
import org.ostis.scmemory.model.pattern.ScPattern3;
import org.ostis.scmemory.model.pattern.ScPattern5;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern3Factory;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern5Factory;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Common sc-context implementation
 * This implementation provides most of the useful methods for working with sc-memory.
 * But if you use this implementation, you should catch all sc-exceptions.
 * If you don't like exceptions, try to use {@link UncheckedScContext}.
 *
 * @author artrayme
 * @since 0.2.0
 */

public class DefaultScContext {
    private final ScMemory memory;

    public DefaultScContext(ScMemory memory) {
        this.memory = memory;
    }

    /**
     * Node creating.
     * This method create a node in sc-memory with the specified type.
     *
     * @param type - type of the node.
     * @return Some implementation of ScNode, that is linked with the corresponding sc-memory.
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public ScNode createNode(NodeType type) throws ScMemoryException {
        return memory.createNodes(Stream.of(type)).findFirst().get();
    }

    /**
     * Nodes creating.
     * This method create multiple nodes in sc-memory with the specified types.
     * If you want to create multiple nodes, this method will be more efficient than {@link #createNode)}.
     *
     * @param types - stream of node types.
     * @return Stream of some implementation of ScNode, that is linked with the corresponding sc-memory.
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public Stream<? extends ScNode> createNodes(Stream<NodeType> types) throws ScMemoryException {
        return memory.createNodes(types);
    }

    /**
     * Edge creating.
     * This method create an edge in sc-memory with the specified type and between two non-null nodes
     *
     * @param type   - type of the edge.
     * @param source - edge source.
     * @param target - edge target.
     * @return Some implementation of ScEdge, that is linked with the corresponding sc-memory.
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public ScEdge createEdge(EdgeType type, ScElement source, ScElement target) throws ScMemoryException {
        return memory.createEdges(Stream.of(type), Stream.of(source), Stream.of(target)).findFirst().get();
    }

    /**
     * Edges creating.
     * This method create multiple edges in sc-memory with the specified types, sources and targets.
     * If you want to create multiple edges, this method will be more efficient than {@link #createEdge)}.
     * <p>
     * Edge[n] will be created between sources[n] and targets[n] with types[n].
     *
     * @param types   - type of the edge.
     * @param sources - edge sources.
     * @param targets - edge targets.
     * @return Stream of some implementation of ScEdge, that is linked with the corresponding sc-memory.
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public Stream<ScEdge> createEdges(Stream<EdgeType> types, Stream<? extends ScElement> sources, Stream<? extends ScElement> targets) throws ScMemoryException {
        return memory.createEdges(types, sources, targets).map(e -> (ScEdge) e);
    }

    /**
     * Link with integer content creating.
     * This method create a link in sc-memory with the specified type and integer content.
     *
     * @param type    - type of the link.
     * @param content - integer content of the link.
     * @return Some implementation of ScLinkInteger, that is linked with the corresponding sc-memory.
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public ScLinkInteger createIntegerLink(LinkType type, Integer content) throws ScMemoryException {
        return memory.createIntegerLinks(Stream.of(type), Stream.of(content)).findFirst().get();
    }

    /**
     * Link with float content creating.
     * This method create a link in sc-memory with the specified type and float content.
     *
     * @param type    - type of the link.
     * @param content - float content of the link.
     * @return Some implementation of ScLinkFloat, that is linked with the corresponding sc-memory.
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public ScLinkFloat createFloatLink(LinkType type, Float content) throws ScMemoryException {
        return memory.createFloatLinks(Stream.of(type), Stream.of(content)).findFirst().get();
    }

    /**
     * Link with string content creating.
     * This method create a link in sc-memory with the specified type and string content.
     *
     * @param type    - type of the link.
     * @param content - string content of the link.
     * @return Some implementation of ScLinkString, that is linked with the corresponding sc-memory.
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public ScLinkString createStringLink(LinkType type, String content) throws ScMemoryException {
        return memory.createStringLinks(Stream.of(type), Stream.of(content)).findFirst().get();
    }

    /**
     * Element deleting
     * This method removes the sc-element from the sc-memory.
     *
     * @param element - is the item you want to delete.
     * @return true when executed successfully
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public Boolean deleteElement(ScElement element) throws ScMemoryException {
        return memory.deleteElements(Stream.of(element));
    }

    /**
     * Elements deleting
     * This method removes the sc-elements from the sc-memory.
     *
     * @param elements - stream of items to be deleted.
     * @return true when executed successfully.
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public Boolean deleteElements(Stream<? extends ScElement> elements) throws ScMemoryException {
        return memory.deleteElements(elements);
    }

    /**
     * Construction search.
     * This method searches for all node-edge-node constructions with specified types relative to a fixed node
     *
     * @param fixedNode - the fixed node with respect to which the searching will take place.
     * @param edge      - type of edge you are looking for.
     * @param node      - type of node you are looking for.
     * @return stream of found constructions.
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public Stream<? extends ScEdge> findAllConstructionsNodeEdgeNode(ScNode fixedNode, EdgeType edge, NodeType node) throws ScMemoryException {
        return memory.findByPattern3(DefaultScPattern3Factory.get(fixedNode, edge, node)).map(ScConstruction3::getEdge);
    }

    @Deprecated
    public Stream<? extends ScEdge> findAllConstructionsNodeEdgeLink(ScNode fixedNode, EdgeType edge, LinkType link, LinkContentType linkContent) throws ScMemoryException {
        return memory.findByPattern3(DefaultScPattern3Factory.get(fixedNode, edge, link)).map(ScConstruction3::getEdge);
    }

    @Deprecated
    public Stream<? extends ScEdge> findAllConstructionsNodeEdgeLinkWithRelation(ScNode fixedNode, EdgeType edge, LinkType link, LinkContentType linkContent, ScNode relation, EdgeType relationEdgeType) throws ScMemoryException {
        return memory.findByPattern5(DefaultScPattern5Factory.get(fixedNode, edge, link, relationEdgeType, relation)).map(ScConstruction5::get2);
    }

    public <t1 extends ScElement, t2, T3 extends ScElement> Stream<? extends ScConstruction3<t1, T3>> find(ScPattern3<t1, t2, T3> pattern) throws ScMemoryException {
        return memory.findByPattern3(pattern);
    }

    public <t1 extends ScElement, t2, t3, T2 extends ScElement, T3 extends ScElement> Stream<? extends ScConstruction5<t1, T2, T3>> find(ScPattern5<t1, t2, t3, T2, T3> pattern) throws ScMemoryException {
        return memory.findByPattern5(pattern);
    }

    /**
     * Link integer content setting.
     * This method sets the content to sc-link.
     *
     * @param link    - target link.
     * @param content - integer content.
     * @return true when executed successfully.
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public Boolean setIntegerLinkContent(ScLinkInteger link, Integer content) throws ScMemoryException {
        return memory.setIntegerLinkContent(Stream.of(link), Stream.of(content)).findFirst().get();
    }

    /**
     * Link float content setting.
     * This method sets the content to sc-link.
     *
     * @param link    - target link.
     * @param content - float content.
     * @return true when executed successfully.
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public Boolean setFloatLinkContent(ScLinkFloat link, Float content) throws ScMemoryException {
        return memory.setFloatLinkContent(Stream.of(link), Stream.of(content)).findFirst().get();
    }

    /**
     * Link string content setting.
     * This method sets the content to sc-link.
     *
     * @param link    - target link.
     * @param content - string content.
     * @return true when executed successfully.
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public Boolean setStringLinkContent(ScLinkString link, String content) throws ScMemoryException {
        return memory.setStringLinkContent(Stream.of(link), Stream.of(content)).findFirst().get();
    }

    /**
     * Integer link content getter.
     * This method gets the link content from sc-memory.
     *
     * @param link - target link.
     * @return link content
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public Integer getIntegerLinkContent(ScLinkInteger link) throws ScMemoryException {
        return memory.getIntegerLinkContent(Stream.of(link)).findFirst().get();
    }

    /**
     * Float link content getter.
     * This method gets the link content from sc-memory.
     *
     * @param link - target link.
     * @return link content
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public Float getFloatLinkContent(ScLinkFloat link) throws ScMemoryException {
        return memory.getFloatLinkContent(Stream.of(link)).findFirst().get();
    }

    /**
     * String link content getter.
     * This method gets the link content from sc-memory.
     *
     * @param link - target link.
     * @return link content
     * @throws ScMemoryException if an internal sc-memory error has occurred. You can find more information in cause exception
     */
    public String getStringLinkContent(ScLinkString link) throws ScMemoryException {
        return memory.getStringLinkContent(Stream.of(link)).findFirst().get();
    }

    public Optional<? extends ScLinkString> findKeynode(String idtf) throws ScMemoryException {
        return memory.findKeynodes(Stream.of(idtf)).findFirst().get();
    }

}
