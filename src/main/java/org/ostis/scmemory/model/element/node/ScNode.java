package org.ostis.scmemory.model.element.node;

import org.ostis.scmemory.model.element.ScElement;

/**
 * This class describes any sc-node in the sc-machine.
 * Each node has an address (because node is a sc-elements) and a type.
 * You can find available node types in the {@link NodeType};
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScNode extends ScElement {
    /**
     * @return type of this sc-node
     */
    NodeType getType();
}
