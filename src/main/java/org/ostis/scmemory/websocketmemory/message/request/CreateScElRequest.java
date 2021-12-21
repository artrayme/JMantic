package org.ostis.scmemory.websocketmemory.message.request;

import org.ostis.scmemory.model.element.ScElement;

import java.util.List;

/**
 * Request for creating {@link ScElement} in the database
 *
 * @author Michael
 * @since 0.0.1
 */
public interface CreateScElRequest extends ScRequest {

    /**
     * Method for adding elements to be created
     *
     * @param elements to be created
     */
    boolean addToRequest(List<? extends ScElement> elements);

    /**
     * Method for adding elements to be created
     *
     * @param element to be created
     */
    boolean addElementToRequest(ScElement element);

    /**
     * Clears the content of the request
     */
    void resetRequest();
}
