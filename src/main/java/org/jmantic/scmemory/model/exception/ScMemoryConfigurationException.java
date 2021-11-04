package org.jmantic.scmemory.model.exception;

/**
 * This exception is indicating that the user configuration cannot be applied
 *
 * @author artrayme
 * @since 0.0.1
 */
public class ScMemoryConfigurationException extends RuntimeException {
    public ScMemoryConfigurationException(String message) {
        super(message);
    }

    public ScMemoryConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScMemoryConfigurationException(Throwable cause) {
        super(cause);
    }
}
