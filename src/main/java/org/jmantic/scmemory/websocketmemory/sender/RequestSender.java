package org.jmantic.scmemory.websocketmemory.sender;

import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.message.request.CreateScElRequest;
import org.jmantic.scmemory.websocketmemory.message.response.CreateScElResponse;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface RequestSender {
    CreateScElResponse sendCreateElRequest(CreateScElRequest request) throws ScMemoryException;

    //todo more requests
}
