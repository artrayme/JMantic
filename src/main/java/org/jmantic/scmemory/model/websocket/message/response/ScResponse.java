package org.jmantic.scmemory.model.websocket.message.response;

/**
 * @author Michael
 */
public interface ScResponse {
    long getResponseId();

    boolean getResponseStatus();

    boolean getEvent();
}
