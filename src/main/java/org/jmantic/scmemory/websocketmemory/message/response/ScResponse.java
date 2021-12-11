package org.jmantic.scmemory.websocketmemory.message.response;

/**
 * An interface that is a generic class of system responses
 *
 * @author Michael
 * @since 0.0.1
 */
public interface ScResponse {

    /**
     * Method for getting the ID of the response.
     * The response ID is identical to the request ID
     *
     * @return id
     */
    long getResponseId();

    /**
     * Has true value when command processed; otherwise has a false value.
     */
    boolean getResponseStatus();

    /**
     * Flag that mark if it is an emited event or not
     */
    boolean getEvent();
}
