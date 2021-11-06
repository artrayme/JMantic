package org.jmantic.scmemory.websocketmemory.sender;

import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.message.request.CreateScElRequest;
import org.jmantic.scmemory.websocketmemory.message.request.DeleteScElRequest;
import org.jmantic.scmemory.websocketmemory.message.response.CreateScElResponse;
import org.jmantic.scmemory.websocketmemory.message.response.DeleteScElResponse;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface RequestSender {
    CreateScElResponse sendCreateElRequest(CreateScElRequest request) throws ScMemoryException;

    DeleteScElResponse sendDeleteElRequest(DeleteScElRequest request) throws ScMemoryException;

    // TODO: 6.11.21 more request
}
