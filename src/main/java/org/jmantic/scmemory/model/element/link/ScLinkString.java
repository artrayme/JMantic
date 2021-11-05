package org.jmantic.scmemory.model.element.link;

/**
 * ScLink for storing a String value
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScLinkString extends ScLink {
    /**
     * @return content of this ScLink
     */
    String getContent();

    @Override
    default LinkContentType getContentType(){
        return LinkContentType.STRING;
    }
}
