package org.jmantic.scmemory.model.websocket.sender;

import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.model.websocket.message.request.CreateScElRequest;
import org.jmantic.scmemory.model.websocket.message.response.CreateScElResponse;

/**
 * @author Michael
 */
public interface RequestSender {
    CreateScElResponse sendCreateElRequest(CreateScElRequest request) throws ScMemoryException;

    //todo more requests
}
