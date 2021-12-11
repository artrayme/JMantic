package org.jmantic.scmemory.websocketmemory.sender;

import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.message.request.*;
import org.jmantic.scmemory.websocketmemory.message.response.*;

/**
 * An interface that provides methods for sending various kinds of
 * {@link ScRequest} to the database through the {@link org.jmantic.scmemory.websocketmemory.core.OstisClient}
 * and receiving {@link ScResponse}.
 *
 * @author Michael
 * @since 0.0.1
 */
public interface RequestSender {

    /**
     * Method for sending the create request
     *
     * @param request a request that contains the items to be created
     * @return response where is the information about the created elements
     * @throws ScMemoryException if something went wrong with the connection to the base
     */
    CreateScElResponse sendCreateElRequest(CreateScElRequest request) throws ScMemoryException;

    /**
     * Method for sending the delete request
     *
     * @param request a request that contains the items to be deleted
     * @return response where is the information about the deleted elements
     * @throws ScMemoryException if something went wrong with the connection to the base
     */
    DeleteScElResponse sendDeleteElRequest(DeleteScElRequest request) throws ScMemoryException;

    /**
     * Method for sending the searchByTemplate request
     *
     * @param request a request that contains the items to be searched
     * @return response where is the information about the searched elements
     * @throws ScMemoryException if something went wrong with the connection to the base
     */
    SearchByTemplateResponse sendSearchByTemplateRequest(SearchByTemplateRequest request) throws ScMemoryException;

    /**
     * Method for sending the setLinkContent request
     *
     * @param request request storing links and data that needs to be placed in them
     * @return response where is the information about links
     * @throws ScMemoryException if something went wrong with the connection to the base
     */
    SetLinkContentResponse sendSetLinkContentRequest(SetLinkContentRequest request) throws ScMemoryException;

    /**
     * Method for sending the getLinkContent request
     *
     * @param request request storing links and data that needs to be placed in them
     * @return response where is the information about links
     * @throws ScMemoryException if something went wrong with the connection to the base
     */
    GetLinkContentResponse sendGetLinkContentRequest(GetLinkContentRequest request) throws ScMemoryException;
}
