package org.jmantic.scmemory.model.element.edge;

import org.jmantic.scmemory.model.element.ScElement;

/**
 * This class describes any sc-edge (and arc) in the sc-machine.
 * Each edge has an address (because node is a sc-elements), a type and two other sc-elements:
 * <p>
 * (first sc-element) -------(sc-edge)------> (second sc-element)
 * <p>
 * You can find available edge types in the {@link EdgeType};
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScEdge extends ScElement {
    /**
     * @return type of this edge
     */
    EdgeType getType();

    /**
     * @return first connected to this edge sc-element
     */
    ScElement getFirst();

    /**
     * @return second connected to this edge sc-element
     */
    ScElement getSecond();
}
