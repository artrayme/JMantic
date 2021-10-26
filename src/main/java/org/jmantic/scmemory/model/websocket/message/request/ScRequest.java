package org.jmantic.scmemory.model.websocket.message.request;

/**
 * @author Michael
 */
public interface ScRequest {
    long getRequestId();

    RequestType getRequestType();
}
