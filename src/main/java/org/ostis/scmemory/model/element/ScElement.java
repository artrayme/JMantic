package org.ostis.scmemory.model.element;

/**
 * This is the main class of sc-structures. It describes any sc-element.
 * Each sc-element in the sc-machine has a unique address (an integer value). <br>
 * <p>
 * There are currently three types of scElement in the sc machine:
 * <ul>
 *     <li>{@link org.ostis.scmemory.model.element.node.ScNode}</li>
 *     <li>{@link org.ostis.scmemory.model.element.edge.ScEdge}</li>
 *     <li>{@link org.ostis.scmemory.model.element.link.ScLink}</li>
 * </ul>
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScElement {
    Long getAddress();
}
