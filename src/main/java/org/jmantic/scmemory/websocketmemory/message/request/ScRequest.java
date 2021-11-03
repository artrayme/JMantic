package org.jmantic.scmemory.websocketmemory.message.request;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface ScRequest {
    long getRequestId();

    RequestType getRequestType();

    boolean isEmpty();
}
