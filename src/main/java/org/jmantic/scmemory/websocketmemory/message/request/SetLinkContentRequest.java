package org.jmantic.scmemory.websocketmemory.message.request;

import org.jmantic.scmemory.model.element.link.ScLink;

/**
 * Request for setting {@link ScLink} content
 *
 * @author Michael
 * @since 0.0.1
 */
public interface SetLinkContentRequest extends ScRequest {

    /**
     * Method for setting {@link ScLink} content
     *
     * @param link link whose content needs to be changed
     * @param data new content
     */
    void addToRequest(ScLink link, Object data);

    /**
     * Clears the content of the request
     */
    void resetRequest();
}
