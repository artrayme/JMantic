package org.ostis.scmemory.model;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLinkBinary;
import org.ostis.scmemory.model.element.link.ScLinkFloat;
import org.ostis.scmemory.model.element.link.ScLinkInteger;
import org.ostis.scmemory.model.element.link.ScLinkString;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.event.EventType;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.model.pattern.ScPattern;
import org.ostis.scmemory.model.pattern.pattern3.ScConstruction3;
import org.ostis.scmemory.model.pattern.pattern3.ScPattern3;
import org.ostis.scmemory.model.pattern.pattern5.ScConstruction5;
import org.ostis.scmemory.model.pattern.pattern5.ScPattern5;

import java.io.ByteArrayOutputStream;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * This interface represents global contract between java-code and the sc-machine
 * <p>
 * ToDo:
 * <ul>
 *     <li>Sc-machine events</li>
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
     * @param elements - stream of node types.
     * @return stream of created nodes.
     */
    Stream<? extends ScNode> createNodes(Stream<NodeType> elements) throws ScMemoryException;


    /**
     * Method to create edges with specified type in sc-machine.
     * You should pass a stream of edge types that you want to create.
     * Also, you should pass a stream of source sc-elements and a stream of target sc-elements.
     * Edges will be created between the corresponding source and target with corresponding type
     * All passed streams must have the same length.
     *
     * @param elements - stream of edge types.
     * @param sources  - stream of source nodes.
     * @param targets  - stream of target nodes.
     * @return stream of created edges.
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
    Stream<? extends ScLinkInteger> createIntegerLinks(Stream<LinkType> elements,
                                                       Stream<Integer> content) throws ScMemoryException;

    /**
     * Method to create sc-link with specified type and float content in sc-machine.
     * You should pass a stream of sc-link types that you want to create.
     * Also, you should pass a stream of float content.
     * Sc-link with corresponding type and content will be created.
     * All passed streams must have the same length.
     *
     * @return stream of created float sc-links
     */
    Stream<? extends ScLinkFloat> createFloatLinks(Stream<LinkType> elements,
                                                   Stream<Float> content) throws ScMemoryException;

    /**
     * Method to create sc-link with specified type and string content in sc-machine.
     * You should pass a stream of sc-link types that you want to create.
     * Also, you should pass a stream of string content.
     * Sc-link with corresponding type and content will be created.
     * All passed streams must have the same length.
     *
     * @return stream of created string sc-links
     */
    Stream<? extends ScLinkString> createStringLinks(Stream<LinkType> elements,
                                                     Stream<String> content) throws ScMemoryException;

    /**
     * Method to create sc-link with specified type and binary content in sc-machine.
     * You should pass a stream of sc-link types that you want to create.
     * Also, you should pass a stream of ByteArrayOutputStream content.
     * Sc-link with corresponding type and content will be created.
     * All passed streams must have the same length.
     *
     * @return stream of created binary sc-links
     * @since 0.6.2
     */
    Stream<? extends ScLinkBinary> createBinaryLinks(Stream<LinkType> elements,
                                                     Stream<ByteArrayOutputStream> content) throws ScMemoryException;

    /**
     * Method to remove any sc-element in sc-machine.
     * You should pass a stream of sc-elements that you want to remove.
     *
     * @return the status of the request
     */
    boolean deleteElements(Stream<? extends ScElement> elements) throws ScMemoryException;

    /**
     * Method to search any sc-constructions by pattern3.
     * You must pass one object of {@link ScPattern3}.
     *
     * @return stream of {@link ScConstruction3} with found elements.
     * @since 0.3.2
     */
    <t1 extends ScElement, t3, T3 extends ScElement> Stream<? extends ScConstruction3<t1, T3>> findByPattern3(ScPattern3<t1, t3, T3> pattern) throws ScMemoryException;

    /**
     * Method to search any sc-constructions by pattern5.
     * You must pass one object of {@link ScPattern5}.
     *
     * @return stream of {@link ScConstruction5} with found elements.
     * @since 0.3.2
     */
    <t1 extends ScElement, t3, t5, T3 extends ScElement, T5 extends ScElement> Stream<? extends ScConstruction5<t1, T3, T5>> findByPattern5(
            ScPattern5<t1, t3, t5, T3, T5> pattern) throws ScMemoryException;

    /**
     * Method to search any sc-constructions by universal pattern.
     *
     * @param pattern {@link ScPattern} that store your pattern.
     * @return stream of streams with found elements. Each nested stream store found sc-elements in sequence of a passed pattern.
     * @since 0.6.0
     */
    Stream<Stream<? extends ScElement>> find(ScPattern pattern) throws ScMemoryException;

    /**
     * @since 0.7.0
     */
    Stream<? extends ScElement> generate(ScPattern pattern) throws ScMemoryException;

    /**
     * Methods for changing the content of {@link ScLinkInteger}
     * All passed streams must have the same length.
     *
     * @param links   links that need to change content
     * @param content new link content
     * @return a stream of values that reflect the result of an operation.
     * True, there was a successful operation on the link, or a lie, if something went wrong.
     */
    Stream<Boolean> setIntegerLinkContent(Stream<? extends ScLinkInteger> links,
                                          Stream<Integer> content) throws ScMemoryException;

    /**
     * Methods for changing the content of {@link ScLinkFloat}
     * All passed streams must have the same length.
     *
     * @param links   links that need to change content
     * @param content new link content
     * @return a stream of values that reflect the result of an operation.
     * True, there was a successful operation on the link, or a lie, if something went wrong.
     * @since 0.3.0
     */
    Stream<Boolean> setFloatLinkContent(Stream<? extends ScLinkFloat> links,
                                        Stream<Float> content) throws ScMemoryException;

    /**
     * Methods for changing the content of {@link ScLinkString}
     * All passed streams must have the same length.
     *
     * @param links   links that need to change content
     * @param content new link content
     * @return a stream of values that reflect the result of an operation.
     * True, there was a successful operation on the link, or a lie, if something went wrong.
     * @since 0.3.0
     */
    Stream<Boolean> setStringLinkContent(Stream<? extends ScLinkString> links,
                                         Stream<String> content) throws ScMemoryException;

    /**
     * Methods for changing the content of {@link ScLinkBinary}
     * All passed streams must have the same length.
     *
     * @param links   links that need to change content
     * @param content new link content
     * @return a stream of values that reflect the result of an operation.
     * True, there was a successful operation on the link, or a lie, if something went wrong.
     * @since 0.6.2
     */
    Stream<Boolean> setBinaryLinkContent(Stream<? extends ScLinkBinary> links,
                                         Stream<ByteArrayOutputStream> content) throws ScMemoryException;

    /**
     * Method for getting the content of {@link ScLinkInteger}
     *
     * @param links links whose content you need to get
     * @return stream of received sc-link values
     * @since 0.3.0
     */
    Stream<Integer> getIntegerLinkContent(Stream<? extends ScLinkInteger> links) throws ScMemoryException;

    /**
     * Method for getting the content of {@link ScLinkFloat}
     *
     * @param links links whose content you need to get
     * @return stream of received sc-link values
     * @since 0.3.0
     */
    Stream<Float> getFloatLinkContent(Stream<? extends ScLinkFloat> links) throws ScMemoryException;

    /**
     * Method for getting the content of {@link ScLinkString}
     *
     * @param links links whose content you need to get
     * @return stream of received sc-link values
     * @since 0.3.0
     */
    Stream<String> getStringLinkContent(Stream<? extends ScLinkString> links) throws ScMemoryException;

    /**
     * Method for getting the content of {@link ScLinkBinary}
     *
     * @param links links whose content you need to get
     * @return stream of received sc-link values
     * @since 0.6.2
     */
    Stream<ByteArrayOutputStream> getBinaryLinkContent(Stream<? extends ScLinkBinary> links) throws ScMemoryException;

    /**
     * Method for getting the sc-link by content
     *
     * @param idtf content for finding
     * @return stream of found sc-links
     * @since 0.3.3
     */
    Stream<Optional<? extends ScNode>> findKeynodes(Stream<String> idtf) throws ScMemoryException;

    /**
     * @since 0.6.0
     */
    Stream<? extends ScNode> resolveKeynodes(Stream<String> idtf, Stream<NodeType> type) throws ScMemoryException;

    /**
     * Implementation specific!
     * <p></p>
     * It is recommended to open ScMemory before use.
     *
     * @since 0.3.0
     */
    void open() throws Exception;

    /**
     * Implementation specific!
     * <p></p>
     * You must close ScMemory if you have opened it before using the method ({@link #open()})
     *
     * @since 0.3.0
     */
    void close() throws Exception;
}
