package org.jmantic.scmemory.model.websocket.request;

/**
 * @author Michael
 */
public interface ScRequest {
    long getRequestId();

    String getRequestType();
}
