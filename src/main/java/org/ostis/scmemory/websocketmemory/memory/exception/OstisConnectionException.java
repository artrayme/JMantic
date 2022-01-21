package org.ostis.scmemory.websocketmemory.memory.exception;

/**
 * This exception indicates a malfunction of the connection with the base.
 *
 * @author artrayme
 * @since 0.2.0
 */
public class OstisConnectionException extends Exception {
    public OstisConnectionException() {
    }

    public OstisConnectionException(String message) {
        super(message);
    }

    public OstisConnectionException(String message, Throwable cause) {
        super(
                message,
                cause);
    }
}
