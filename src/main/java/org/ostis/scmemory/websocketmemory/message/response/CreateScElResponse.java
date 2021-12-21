package org.ostis.scmemory.websocketmemory.message.response;

import java.util.stream.Stream;

/**
 * Response to {@link org.ostis.scmemory.websocketmemory.message.request.CreateScElRequest}
 * that contains information about the item created
 *
 * @author Michael
 * @since 0.0.1
 */
public interface CreateScElResponse extends ScResponse {

    /**
     * Method for getting the addresses of the created {@link org.ostis.scmemory.model.element.ScElement}
     *
     * @return address
     */
    Stream<Long> getAddresses();
}
