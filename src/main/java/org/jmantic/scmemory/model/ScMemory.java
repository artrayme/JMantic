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
import org.jmantic.scmemory.model.event.ScEvent;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.0.1
 */
public interface ScMemory {
    Stream<? extends ScElement> createNodes(Stream<NodeType> elements);

    Stream<? extends ScElement> createEdges(Stream<EdgeType> elements,
                                            Stream<ScElement> firstComponents,
                                            Stream<ScElement> secondComponents);

    Stream<? extends ScElement> createIntegerLink(Stream<LinkType> elements, Stream<Integer> content);

    Stream<? extends ScElement> createFloatLink(Stream<LinkType> elements, Stream<Float> content);

    Stream<? extends ScElement> createStringLink(Stream<LinkType> elements, Stream<String> content);

    Stream<? extends ScElement> createBinaryLink(Stream<LinkType> elements, Stream<Object> content);

    Stream<ScElement> checkElements(Stream<ScElement> elements);

    boolean deleteNode(Stream<ScElement> elements);

    boolean deleteEdge(Stream<ScEdge> elements);

    boolean deleteLink(Stream<ScLink> elements);

    //    ToDO searchTemplate
    //    ToDo generateTemplate

    void addEventListener(ScEvent event, Function<ScElement, ScElement> predicate);

    void removeEventListener(ScEvent event);

    ScElement findKeynode(String identifier);

    ScElement resolvedKeynode(String identifier, NodeType type);

    Stream<ScLink> getContent(Stream<ScLink> elements);

    Stream<Boolean> setIntegerContent(Stream<ScLinkInteger> links, Stream<Integer> content);

    Stream<Boolean> setFloatContent(Stream<ScLinkFloat> links, Stream<Float> content);

    Stream<Boolean> setStringContent(Stream<ScLinkString> links, Stream<String> content);

    //    ToDO set binary content
}