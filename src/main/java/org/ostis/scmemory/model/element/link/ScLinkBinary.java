package org.ostis.scmemory.model.element.link;

import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.IOException;

/**
 * ScLink for storing serialized java-class
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScLinkBinary extends ScLink {
    /**
     * @return content of this ScLink
     */
    ByteArrayOutputStream getContent() throws IOException;

    @Override
    default LinkContentType getContentType() {
        return LinkContentType.BINARY;
    }
}
