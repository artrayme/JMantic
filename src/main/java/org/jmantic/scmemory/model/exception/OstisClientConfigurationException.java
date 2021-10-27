package org.jmantic.scmemory.model.exception;

/**
 * @author artrayme
 * 10/28/21
 */
public class OstisClientConfigurationException extends RuntimeException{
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
