package org.jmantic.scmemory.websocketmemory.core;

import org.jmantic.scmemory.model.exception.ScMemoryException;

import java.net.URI;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface OstisClient {

    void configure(URI uriToServer);

    String sendToOstis(String jsonRequest) throws ScMemoryException;
}
