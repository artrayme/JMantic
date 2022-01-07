package org.ostis.scmemory.websocketmemory.memory.exception;

/**
 * This exception indicates a violation of the {@link org.ostis.scmemory.websocketmemory.core.OstisClient} configuration
 *
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
