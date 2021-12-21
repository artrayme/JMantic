package org.ostis.scmemory.websocketmemory.message.request;

import org.ostis.scmemory.model.element.ScElement;

import java.util.List;

/**
 * Request for deleting {@link ScElement} from the database
 *
 * @author Michael
 * @since 0.0.1
 */
public interface DeleteScElRequest extends ScRequest {

    /**
     * Method for removing elements from the database
     *
     * @param addresses element to be removed
     */
    boolean addToRequest(List<Long> addresses);

    /**
     * Method for removing elements from the database
     *
     * @param address element to be removed
     */
    boolean addAddressToRequest(Long address);

    /**
     * Clears the content of the request
     */
    void resetRequest();
}
