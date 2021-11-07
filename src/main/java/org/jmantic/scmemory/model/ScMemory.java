package org.jmantic.scmemory.model;

import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.exception.ScMemoryException;

import java.util.stream.Stream;

/**
 * This interface represents global contract between java-code and the sc-machine
 * <p>
 * ToDo:
 * <ul>
 *     <li>Elements generator by template</li>
 *     <li>More searching templates</li>
 *     <li>Sc-machine events</li>
 *     <li>Keynodes finder and resolver</li>
 *     <li>Method for creating sc-link with binary content (and get/set methods)</li>
 *
 * </ul>
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScMemory {

    /**
     * Method to create nodes with specified type in sc-machine.
     * You should pass a stream of node types that you want to create.
     *
     * @return stream of created nodes
     */
    Stream<? extends ScNode> createNodes(Stream<NodeType> elements) throws ScMemoryException;


    /**
     * Method to create edges with specified type in sc-machine.
     * You should pass a stream of edge types that you want to create.
     * Also, you should pass a stream of source sc-elements and a stream of target sc-elements.
     * Edges will be created between the corresponding source and target with corresponding type
     * All passed streams must have the same length.
     *
     * @return stream of created edges
     */
    Stream<? extends ScEdge> createEdges(Stream<EdgeType> elements,
                                         Stream<? extends ScElement> sources,
                                         Stream<? extends ScElement> targets) throws ScMemoryException;

    /**
     * Method to create sc-link with specified type and integer content in sc-machine.
     * You should pass a stream of sc-link types that you want to create.
     * Also, you should pass a stream of integer content.
     * Sc-link with corresponding type and content will be created.
     * All passed streams must have the same length.
     *
     * @return stream of created integer sc-links
     */
    Stream<? extends ScLinkInteger> createIntegerLink(Stream<LinkType> elements, Stream<Integer> content) throws ScMemoryException;

    /**
     * Method to create sc-link with specified type and float content in sc-machine.
     * You should pass a stream of sc-link types that you want to create.
     * Also, you should pass a stream of float content.
     * Sc-link with corresponding type and content will be created.
     * All passed streams must have the same length.
     *
     * @return stream of created float sc-links
     */
    Stream<? extends ScLinkFloat> createFloatLink(Stream<LinkType> elements, Stream<Float> content) throws ScMemoryException;

    /**
     * Method to create sc-link with specified type and string content in sc-machine.
     * You should pass a stream of sc-link types that you want to create.
     * Also, you should pass a stream of string content.
     * Sc-link with corresponding type and content will be created.
     * All passed streams must have the same length.
     *
     * @return stream of created string sc-links
     */
    Stream<? extends ScLinkString> createStringLink(Stream<LinkType> elements, Stream<String> content) throws ScMemoryException;

    /**
     * Method to remove any sc-element in sc-machine.
     * You should pass a stream of sc-elements that you want to remove.
     *
     * @return the status of the request. Always true ¯\_(ツ)_/¯
     */
    boolean deleteElements(Stream<ScElement> elements) throws ScMemoryException;

    /**
     * Method to search for sc-constructions from pattern Node-Edge-Node.
     * This pattern is a variation of F_A_A pattern
     * You should pass a stream of sc-elements that you want to remove.
     *
     * @return the status of the request. Always true ¯\_(ツ)_/¯
     */
    Stream<? extends ScEdge> findByTemplateNodeEdgeNode(ScNode node, EdgeType edgeType, NodeType nodeType) throws ScMemoryException;

    Stream<Boolean> setIntegerLinkContent(Stream<? extends ScLinkInteger> links, Stream<Integer> content) throws ScMemoryException;

    Stream<Boolean> setFloatLinkContent(Stream<? extends ScLinkFloat> links, Stream<Float> content) throws ScMemoryException;

    Stream<Boolean> setStringLinkContent(Stream<? extends ScLinkString> links, Stream<String> content) throws ScMemoryException;

    Stream<Integer> getIntegerLinkContent(Stream<? extends ScLinkInteger> elements) throws ScMemoryException;

    Stream<Float> getFloatLinkContent(Stream<? extends ScLinkFloat> elements) throws ScMemoryException;

    Stream<String> getStringLinkContent(Stream<? extends ScLinkString> elements) throws ScMemoryException;
}
