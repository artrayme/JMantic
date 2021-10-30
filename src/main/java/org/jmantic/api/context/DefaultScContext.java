package org.jmantic.api.context;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.exception.ScMemoryException;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author artrayme
 * 10/23/21
 */
public class DefaultScContext {
    private final ScMemory memory;

    public DefaultScContext(ScMemory memory) {
        this.memory = memory;
    }

    public ScNode createNode(NodeType type) {
        Optional<? extends ScElement> result = null;
        try {
            result = memory.createNodes(Stream.of(type)).findFirst();
        } catch (ScMemoryException e) {
//            ToDo logger
            e.printStackTrace();
        }
        return (ScNode) result.get();
    }

    public Stream<ScNode> createNodes(Stream<NodeType> types) {
        Stream<? extends ScElement> result = null;
        try {
            result = memory.createNodes(types);
        } catch (ScMemoryException e) {
//            ToDO logger
            e.printStackTrace();
        }
        return result.map(e -> (ScNode) e);
    }

    public ScEdge createEdge(EdgeType type, ScElement first, ScElement second) {
        Optional<? extends ScElement> edge = null;
        try {
            edge = memory.createEdges(Stream.of(type), Stream.of(first), Stream.of(second)).findFirst();
        } catch (ScMemoryException e) {
//            ToDo logger
            e.printStackTrace();
        }
        return (ScEdge) edge.get();
    }


}
