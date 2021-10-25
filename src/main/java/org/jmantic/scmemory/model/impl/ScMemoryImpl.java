package org.jmantic.scmemory.model.impl;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.*;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.event.ScEvent;

import java.util.function.Function;
import java.util.stream.Stream;


/**
 * @author Michael
 */
public enum ScMemoryImpl implements ScMemory {
    INSTANCE;

    @Override
    public Stream<? extends ScElement> createNodes(Stream<NodeType> elements) {
        return null;
    }

    @Override
    public Stream<? extends ScElement> createEdges(Stream<EdgeType> elements, Stream<ScElement> firstComponents, Stream<ScElement> secondComponents) {
        return null;
    }

    @Override
    public Stream<? extends ScElement> createIntegerLink(Stream<LinkType> elements, Stream<Integer> content) {
        return null;
    }

    @Override
    public Stream<? extends ScElement> createFloatLink(Stream<LinkType> elements, Stream<Float> content) {
        return null;
    }

    @Override
    public Stream<? extends ScElement> createStringLink(Stream<LinkType> elements, Stream<String> content) {
        return null;
    }

    @Override
    public Stream<? extends ScElement> createBinaryLink(Stream<LinkType> elements, Stream<Object> content) {
        return null;
    }

    @Override
    public Stream<ScElement> checkElements(Stream<ScElement> elements) {
        return null;
    }

    @Override
    public boolean deleteNode(Stream<ScElement> elements) {
        return false;
    }

    @Override
    public boolean deleteEdge(Stream<ScEdge> elements) {
        return false;
    }

    @Override
    public boolean deleteLink(Stream<ScLink> elements) {
        return false;
    }

    @Override
    public void addEventListener(ScEvent event, Function<ScElement, ScElement> predicate) {

    }

    @Override
    public void removeEventListener(ScEvent event) {

    }

    @Override
    public ScElement findKeynode(String identifier) {
        return null;
    }

    @Override
    public ScElement resolvedKeynode(String identifier, NodeType type) {
        return null;
    }

    @Override
    public Stream<ScLink> getContent(Stream<ScLink> elements) {
        return null;
    }

    @Override
    public Stream<Boolean> setIntegerContent(Stream<ScLinkInteger> links, Stream<Integer> content) {
        return null;
    }

    @Override
    public Stream<Boolean> setFloatContent(Stream<ScLinkFloat> links, Stream<Float> content) {
        return null;
    }

    @Override
    public Stream<Boolean> setStringContent(Stream<ScLinkString> links, Stream<String> content) {
        return null;
    }
}
