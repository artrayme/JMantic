package org.jmantic.scmemory.model;

import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.model.util.ScTriple;

import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.0.1
 */
public interface ScMemory {
    Stream<? extends ScElement> createNodes(Stream<NodeType> elements) throws ScMemoryException;

    Stream<? extends ScElement> createEdges(Stream<EdgeType> elements,
                                            Stream<? extends ScElement> firstComponents,
                                            Stream<? extends ScElement> secondComponents) throws ScMemoryException;

    Stream<? extends ScElement> createIntegerLink(Stream<LinkType> elements, Stream<Integer> content) throws ScMemoryException;

    Stream<? extends ScElement> createFloatLink(Stream<LinkType> elements, Stream<Float> content) throws ScMemoryException;

    Stream<? extends ScElement> createStringLink(Stream<LinkType> elements, Stream<String> content) throws ScMemoryException;

    Stream<? extends ScElement> createBinaryLink(Stream<LinkType> elements, Stream<Object> content) throws ScMemoryException;

    Stream<ScElement> checkElements(Stream<ScElement> elements) throws ScMemoryException;

    boolean deleteNode(Stream<ScElement> elements) throws ScMemoryException;

    boolean deleteEdge(Stream<ScEdge> elements) throws ScMemoryException;

    boolean deleteLink(Stream<ScLink> elements) throws ScMemoryException;

    Stream<ScTriple> findByTemplateF_A_A(ScElement element, EdgeType edgeType, NodeType nodeType) throws ScMemoryException;

    Stream<ScTriple> findByTemplateF_F_A(ScElement firstElement, ScElement secondElement, NodeType nodeType) throws ScMemoryException;

    //    ToDo generateTemplate
    //    ToDO events
    //    void addEventListener(ScEvent event, Function<ScElement, ScElement> predicate);
    //    void removeEventListener(ScEvent event);

    ScElement findKeynode(String identifier) throws ScMemoryException;

    ScElement resolvedKeynode(String identifier, NodeType type) throws ScMemoryException;

    Stream<ScLink> getContent(Stream<ScLink> elements) throws ScMemoryException;

    Stream<Boolean> setIntegerContent(Stream<ScLinkInteger> links, Stream<Integer> content) throws ScMemoryException;

    Stream<Boolean> setFloatContent(Stream<ScLinkFloat> links, Stream<Float> content) throws ScMemoryException;

    Stream<Boolean> setStringContent(Stream<ScLinkString> links, Stream<String> content) throws ScMemoryException;

    Stream<Boolean> setBinaryContent(Stream<ScLinkString> links, Stream<Object> content) throws ScMemoryException;
}
