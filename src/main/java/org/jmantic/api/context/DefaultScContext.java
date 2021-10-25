package org.jmantic.api.context;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;

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
        var result = memory.createNodes(Stream.of(type)).findFirst();
        if (result.isPresent()) {
            return (ScNode) result.get();
        }
        //        ToDo normal Exceptions
        throw new RuntimeException("Internal ScError");
    }

    public Stream<ScNode> createNodes(Stream<NodeType> types) {
        var result = memory.createNodes(types);
        return result.map(e -> (ScNode) e);
    }

    public ScEdge createEdge(EdgeType type, ScElement first, ScElement second) {
        var edge = memory.createEdges(Stream.of(type), Stream.of(first), Stream.of(second)).findFirst();
        if (edge.isPresent()) {
            return (ScEdge) edge.get();
        }
        //        ToDo normal Exceptions
        throw new RuntimeException("Internal ScError");
    }

}
