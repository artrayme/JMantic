package org.jmantic.scmemory.model.element.edge;

import org.jmantic.scmemory.model.element.ScElement;

/**
 * @author artrayme
 * @since 0.0.1
 */
public interface ScEdge extends ScElement {
    EdgeType getType();
    ScElement getFirst();
    ScElement getSecond();
}
