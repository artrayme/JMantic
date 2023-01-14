package org.ostis.scmemory.websocketmemory.sender;

import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.websocketmemory.message.request.CheckScElTypeRequest;
import org.ostis.scmemory.websocketmemory.message.request.CreateScElRequest;
import org.ostis.scmemory.websocketmemory.message.request.DeleteScElRequest;
import org.ostis.scmemory.websocketmemory.message.request.FindByPatternRequest;
import org.ostis.scmemory.websocketmemory.message.request.GetLinkContentRequest;
import org.ostis.scmemory.websocketmemory.message.request.KeynodeRequest;
import org.ostis.scmemory.websocketmemory.message.request.ScRequest;
import org.ostis.scmemory.websocketmemory.message.request.SetLinkContentRequest;
import org.ostis.scmemory.websocketmemory.message.response.CheckScElTypeResponse;
import org.ostis.scmemory.websocketmemory.message.response.CreateScElResponse;
import org.ostis.scmemory.websocketmemory.message.response.DeleteScElResponse;
import org.ostis.scmemory.websocketmemory.message.response.FindByPatternResponse;
import org.ostis.scmemory.websocketmemory.message.response.GetLinkContentResponse;
import org.ostis.scmemory.websocketmemory.message.response.KeynodeResponse;
import org.ostis.scmemory.websocketmemory.message.response.ScResponse;
import org.ostis.scmemory.websocketmemory.message.response.SetLinkContentResponse;

import java.net.URI;

/**
 * An interface that provides methods for sending various kinds of
 * {@link ScRequest} to the database through the {@link org.ostis.scmemory.websocketmemory.core.OstisClient}
 * and receiving {@link ScResponse}.
 *
 * @author Michael
 * @since 0.0.1
 */
public interface RequestSender {

    URI getAddress();

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
    FindByPatternResponse sendFindByPatternRequest(FindByPatternRequest request) throws ScMemoryException;

    /**
     * Method for sending the generateByTemplate request
     *
     * @param request a request that contains the items to be generated
     * @return response where is the information about the generated elements
     * @throws ScMemoryException if something went wrong with the connection to the base
     */
    GenerateByPatternResponse sendGenerateByPatternRequest(GenerateByPatternRequest request) throws ScMemoryException;

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

    /**
     * Method for sending the keynode operations request
     *
     * @param request information about keynode and operation with it
     * @return response where is the information about keynode-links
     * @throws ScMemoryException if something went wrong with the connection to the base
     */
    KeynodeResponse sendKeynodeRequest(KeynodeRequest request) throws ScMemoryException;

    /**
     * Method for sending the checkElementType request
     *
     * @param request information about checked addresses
     * @return response where is the information about types of checked addresses
     * @throws ScMemoryException if something went wrong with the connection to the base
     */
    CheckScElTypeResponse sendCheckScElTypeRequest(CheckScElTypeRequest request) throws ScMemoryException;
}
