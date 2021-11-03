package org.jmantic.scmemory.model.exception;

/** This exception is indicating that the user configuration cannot be applied
 *
 * @author artrayme
 * @since 0.0.1
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
