package org.jmantic.scmemory.model.websocket.sender;

import org.jmantic.scmemory.model.websocket.request.CreateScElRequest;
import org.jmantic.scmemory.model.websocket.response.CreateScElResponse;

/**
 * @author Michael
 */
public interface RequestSender {
    CreateScElResponse sendRequest(CreateScElRequest request);

    //todo mode requests
}
