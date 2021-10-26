package org.jmantic.scmemory.model.websocket.core;

import org.jmantic.scmemory.model.exception.ScMemoryException;

import java.net.URI;

/**
 * @author Michael
 */
public interface OstisClient {

    void configure(URI uriToServer);

    String sendToOstis(String jsonRequest) throws ScMemoryException;
}
