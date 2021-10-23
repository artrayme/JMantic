package org.jmantic.scmemory.model.element;

/**
 * This is the main class of sc-structures.
 * Each sc-element in the sc-machine has a unique address (an integer value).
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScElement {
    Long getAddress();
}
