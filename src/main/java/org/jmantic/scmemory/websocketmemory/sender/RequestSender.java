package org.jmantic.scmemory.websocketmemory.sender;

import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.message.request.*;
import org.jmantic.scmemory.websocketmemory.message.response.*;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface RequestSender {
    CreateScElResponse sendCreateElRequest(CreateScElRequest request) throws ScMemoryException;

    DeleteScElResponse sendDeleteElRequest(DeleteScElRequest request) throws ScMemoryException;

    SearchByTemplateResponse sendSearchByTemplateRequest(SearchByTemplateRequest request) throws ScMemoryException;

    SetLinkContentResponse sendSetLinkContentRequest(SetLinkContentRequest request) throws ScMemoryException;

    GetLinkContentResponse sendGetLinkContentRequest(GetLinkContentRequest request) throws ScMemoryException;

    // TODO: 6.11.21 more request
}
