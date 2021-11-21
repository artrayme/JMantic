package org.jmantic.scmemory.websocketmemory.sync.exception;

/**
 * @author artrayme
 * @since 0.2.0
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
