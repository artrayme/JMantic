package org.jmantic.scmemory.websocketmemory.message.response;

/**
 * @author Michael
 */
public interface ScResponse {
    long getResponseId();

    boolean getResponseStatus();

    boolean getEvent();
}
