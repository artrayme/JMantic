package org.jmantic.scmemory.websocketmemory.message.response;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface ScResponse {
    long getResponseId();

    boolean getResponseStatus();

    boolean getEvent();
}
