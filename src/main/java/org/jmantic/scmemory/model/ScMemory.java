package org.jmantic.scmemory.model;

import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.model.element.link.ScLinkBinary;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.exception.ScMemoryException;

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

    Stream<? extends ScLinkInteger> createIntegerLink(Stream<LinkType> elements, Stream<Integer> content) throws ScMemoryException;

    Stream<? extends ScLinkFloat> createFloatLink(Stream<LinkType> elements, Stream<Float> content) throws ScMemoryException;

    Stream<? extends ScLinkString> createStringLink(Stream<LinkType> elements, Stream<String> content) throws ScMemoryException;

    Stream<? extends ScLinkBinary> createBinaryLink(Stream<LinkType> elements, Stream<Object> content) throws ScMemoryException;

    //    Stream<ScElement> checkElements(Stream<ScElement> elements) throws ScMemoryException;

    boolean deleteElements(Stream<ScElement> elements) throws ScMemoryException;

    Stream<? extends ScEdge> findByTemplateNodeEdgeNode(ScNode node, EdgeType edgeType, NodeType nodeType) throws ScMemoryException;

    //    ToDo generateTemplate
    //    ToDO events
    //    void addEventListener(ScEvent event, Function<ScElement, ScElement> predicate);
    //    void removeEventListener(ScEvent event);

    //    ScElement findKeynode(String identifier) throws ScMemoryException;

    //    ScElement resolvedKeynode(String identifier, NodeType type) throws ScMemoryException;

    Stream<? extends ScLink> getLinkContent(Stream<? extends ScLink> elements) throws ScMemoryException;

    Stream<Boolean> setLinkContent(Stream<? extends ScLink> links, Stream<Object> content) throws ScMemoryException;
}
