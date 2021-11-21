package org.jmantic.scmemory.websocketmemory.sync.exception;

/**
 * @author artrayme
 * @since 0.2.0
 */
public class OstisClientConfigurationException extends RuntimeException {
    public OstisClientConfigurationException(String message) {
        super(message);
    }

    public OstisClientConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OstisClientConfigurationException(Throwable cause) {
        super(cause);
    }
}
