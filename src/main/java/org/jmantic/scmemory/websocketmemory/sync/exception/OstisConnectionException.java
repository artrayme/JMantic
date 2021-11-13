package org.jmantic.scmemory.websocketmemory.sync.exception;

/**
 * @author artrayme
 * 11/12/21
 */
public class OstisConnectionException extends Exception{
    public OstisConnectionException() {
    }

    public OstisConnectionException(String message) {
        super(message);
    }

    public OstisConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
