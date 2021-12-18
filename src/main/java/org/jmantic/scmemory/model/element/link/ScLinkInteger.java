package org.jmantic.scmemory.model.element.link;

/**
 * ScLink for storing an integer value
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScLinkInteger extends ScLink {
    /**
     * @return content of this ScLink
     */
    int getContent();

    @Override
    default LinkContentType getContentType() {
        return LinkContentType.INT;
    }
}
