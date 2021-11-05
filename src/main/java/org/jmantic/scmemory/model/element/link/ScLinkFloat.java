package org.jmantic.scmemory.model.element.link;

/**
 * ScLink for storing a float value
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScLinkFloat extends ScLink {
    /**
     * @return content of this ScLink
     */
    float getContent();

    @Override
    default LinkContentType getContentType(){
        return LinkContentType.FLOAT;
    }
}
