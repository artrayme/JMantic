package org.jmantic.scmemory.websocketmemory.core;

import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.sync.exception.OstisConnectionException;

import java.net.URI;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface OstisClient extends AutoCloseable{

    void configure(URI uriToServer);

    void open();

    String sendToOstis(String jsonRequest) throws OstisConnectionException;
}
