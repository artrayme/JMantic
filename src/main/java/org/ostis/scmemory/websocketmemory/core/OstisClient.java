package org.ostis.scmemory.websocketmemory.core;

import org.ostis.scmemory.websocketmemory.memory.exception.OstisConnectionException;

import java.net.URI;

/**
 * This interface is designed to send requests to the database
 * over the network and receive responses in the form of JSON.
 * Can be used in try-with-resources. Or you need to close it manually.
 *
 * @author Michael
 * @since 0.0.1
 */
public interface OstisClient extends AutoCloseable {

    /**
     * Method for configuring the URI address to the server
     *
     * @param uriToServer URI address to the server
     */
    void configure(URI uriToServer);

    /**
     * Method for opening a database connection
     */
    void open();


    /**
     * Method to check if a connection is open
     */
    boolean isOpen();

    /**
     * Method for sending a request to the database and getting the result.
     *
     * @param jsonRequest The request to be processed (in JSON format)
     * @return The answer that came from the base (in JSON format)
     * @throws OstisConnectionException database connection error
     */
    String sendToOstis(String jsonRequest) throws OstisConnectionException;

    URI getConfiguration();
}
