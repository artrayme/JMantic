package org.ostis.scmemory.websocketmemory.message.request;

import org.ostis.scmemory.model.element.link.ScLink;

import java.util.List;

/**
 * Request for getting {@link ScLink} content
 *
 * @author Michael
 * @since 0.0.1
 */
public interface GetLinkContentRequest extends ScRequest {

    /**
     * Method for getting {@link ScLink} content
     *
     * @param addresses of links whose content you want to get
     */
    boolean addToRequest(List<Long> addresses);

    /**
     * Method for getting {@link ScLink} content
     *
     * @param address of links whose content you want to get
     */
    boolean addAddressToRequest(long address);

    /**
     * Clears the content of the request
     */
    void resetRequest();
}
