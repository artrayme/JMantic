package org.jmantic.api.context;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.model.util.ScTriple;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.0.1
 */
public class DefaultScContext {
    private final ScMemory memory;

    public DefaultScContext(ScMemory memory) {
        this.memory = memory;
    }

    public ScNode createNode(NodeType type) {
        Optional<? extends ScElement> result = Optional.empty();
        try {
            result = memory.createNodes(Stream.of(type)).findFirst();
        } catch (ScMemoryException e) {
            //            ToDo logger
            e.printStackTrace();
        }
        return (ScNode) result.get();
    }

    public Stream<ScNode> createNodes(Stream<NodeType> types) {
        Stream<? extends ScElement> result = Stream.empty();
        try {
            result = memory.createNodes(types);
        } catch (ScMemoryException e) {
            //            ToDO logger
            e.printStackTrace();
        }
        return result.map(e -> (ScNode) e);
    }

    public ScEdge createEdge(EdgeType type, ScElement source, ScElement target) {
        Optional<? extends ScElement> edge = Optional.empty();
        try {
            edge = memory.createEdges(Stream.of(type), Stream.of(source), Stream.of(target)).findFirst();
        } catch (ScMemoryException e) {
            //            ToDo logger
            e.printStackTrace();
        }
        return (ScEdge) edge.get();
    }

    public Stream<ScEdge> createEdges(Stream<EdgeType> types, Stream<? extends ScElement> source, Stream<? extends ScElement> target) {
        Stream<ScEdge> scEdgeStream = Stream.empty();
        try {
            scEdgeStream = memory.createEdges(types, source, target).map(e -> (ScEdge) e);
        } catch (ScMemoryException e) {
            //            ToDo logger
            e.printStackTrace();
        }
        return scEdgeStream;
    }

    public ScLinkInteger createIntegerLink(LinkType type, Integer content){
        Optional<? extends ScLinkInteger> result = Optional.empty();
        try {
            result = memory.createIntegerLink(Stream.of(type), Stream.of(content)).findFirst();
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.get();
    }

    public ScLinkInteger createFloatLink(LinkType type, Float content){
        Optional<? extends ScLinkInteger> result = Optional.empty();
        try {
            result = memory.createFloatLink(Stream.of(type), Stream.of(content)).findFirst();
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.get();
    }

    public ScLinkInteger createStringLink(LinkType type, String content){
        Optional<? extends ScLinkInteger> result = Optional.empty();
        try {
            result = memory.createStringLink(Stream.of(type), Stream.of(content)).findFirst();
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.get();
    }

    public ScLinkInteger createBinaryLink(LinkType type, String content){
        Optional<? extends ScLinkInteger> result = Optional.empty();
        try {
            result = memory.createStringLink(Stream.of(type), Stream.of(content)).findFirst();
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.get();
    }

    public boolean deleteElement(ScElement element){
        boolean result = false;
        try {
            result = memory.deleteElements(Stream.of(element));
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Stream<ScEdge> findAll(ScElement fixedNode, EdgeType edge, NodeType node) {
        Stream<ScEdge> result = Stream.empty();
        try {
            result = memory.findByTemplateF_A_A(fixedNode, edge, node);
        } catch (ScMemoryException e) {
            //            ToDo logger
            e.printStackTrace();
        }
        return result;
    }

}
