package org.jmantic.api.context;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;

import java.util.Optional;

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
        return (ScNode) memory.createNodes(Optional.of(type).stream()).findFirst().get();
    }




}
