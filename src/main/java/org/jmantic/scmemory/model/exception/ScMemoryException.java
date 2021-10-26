package org.jmantic.scmemory.model.exception;

public class ScMemoryException extends Exception{
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
