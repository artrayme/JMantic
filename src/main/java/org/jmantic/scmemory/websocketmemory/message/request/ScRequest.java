package org.jmantic.scmemory.websocketmemory.message.request;

/**
 * @author Michael
 */
public interface ScRequest {
    long getRequestId();

    RequestType getRequestType();

    boolean isEmpty();
}
