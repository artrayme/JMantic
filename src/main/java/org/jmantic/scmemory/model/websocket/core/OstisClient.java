package org.jmantic.scmemory.model.websocket.core;

import java.net.URI;

/**
 * @author Michael
 */
public interface OstisClient {

    void configure(URI uriToServer);

    String sendToOstis(String jsonRequest);
}
