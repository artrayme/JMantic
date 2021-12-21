package org.ostis.scmemory.websocketmemory.message.response;

import java.util.List;

/**
 * A response that contains data obtained from links
 * using a {@link org.ostis.scmemory.websocketmemory.message.request.SetLinkContentRequest}
 *
 * @author Michael
 * @since 0.0.1
 */
public interface SetLinkContentResponse extends ScResponse {

    /**
     * Method that returns the status of operations for grabbing
     * {@link org.ostis.scmemory.model.element.link.ScLink} content
     *
     * @return status of operation
     */
    List<Boolean> getOperationStatus();
}
