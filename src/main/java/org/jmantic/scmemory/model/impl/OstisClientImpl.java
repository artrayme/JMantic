package org.jmantic.scmemory.model.impl;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.model.websocket.core.OstisClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

/**
 * @author Michael
 */
enum OstisClientImpl implements OstisClient {
    INSTANCE;
    private final Logger logger = LoggerFactory.getLogger(OstisClient.class);
    private volatile WebSocketClient client;
    private volatile String result;
    private volatile CountDownLatch latch;

    @Override
    public synchronized void configure(URI uriToServer) {
        this.client = new WebSocketClient(uriToServer) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }

            @Override
            public void onMessage(String message) {
                result = message;
                if (latch != null) {
                    latch.countDown();
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void onError(Exception ex) {

            }
        };
    }

    @Override
    public synchronized String sendToOstis(String jsonRequest) throws ScMemoryException {
        if (client == null) {
            logger.info("");
            throw new ScMemoryException("pls, configure client with URI");
        }
        latch = new CountDownLatch(1);
        client.connect();
        client.send(jsonRequest);
        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.info("smth wrong in OstisClient");
            throw new ScMemoryException("smth wrong", e);
        }
        client.close();
        return result;
    }
}
