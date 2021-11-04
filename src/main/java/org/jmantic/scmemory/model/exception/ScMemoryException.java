package org.jmantic.scmemory.model.exception;

/**
 * This exception is indicating that the sc memory cannot continue working
 * cause something that does not depend on sc-memory is broken
 *
 * @author Michael
 * @since 0.0.1
 */
public class ScMemoryException extends Exception {
    public ScMemoryException() {
        super();
    }

    public ScMemoryException(String message) {
        super(message);
    }

    public ScMemoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScMemoryException(Throwable cause) {
        super(cause);
    }
}
