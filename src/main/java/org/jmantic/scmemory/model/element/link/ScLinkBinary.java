package org.jmantic.scmemory.model.element.link;

/**
 * ScLink for storing serialized java-class
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScLinkBinary<T> extends ScLink {
    /**
     * @return content of this ScLink
     */
    T getContent();
}
