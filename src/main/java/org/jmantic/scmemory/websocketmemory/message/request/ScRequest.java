package org.jmantic.scmemory.websocketmemory.message.request;

/**
 * An interface that is a generic class of system requests.
 * <p>
 * Each request has a common structure:
 * {
 *      "id": 2,
 *      "type": "request type",
 *      "payload": {...}
 * }
 * Different requests have different payload structure
 *
 * @author Michael
 * @since 0.0.1
 */
public interface ScRequest {
    /**
     * Method for getting the ID of the request.
     * Each request has its own unique ID.
     *
     * @return id
     */
    long getRequestId();

    /**
     * Method for getting the {@link RequestType} of request.
     *
     * @return {@link RequestType}
     */
    RequestType getRequestType();

    /**
     * Method that checks if the request is empty or not
     */
    boolean isEmpty();
}
