package org.jmantic.scmemory.model.websocket.response;

/**
 * @author Michael
 */
public interface ScResponse {
    long getResponseId();

    boolean getResponseStatus();

    boolean getEvent();
}
