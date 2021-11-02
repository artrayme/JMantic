package org.jmantic.scmemory.websocketmemory.sync;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.jmantic.scmemory.model.exception.OstisClientConfigurationException;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.core.OstisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

/**
 * @author Michael
 */
//todo normalno zapili, debil
enum OstisClientImpl implements OstisClient {
    INSTANCE;
    private final static Logger logger = LoggerFactory.getLogger(OstisClient.class);
    private volatile WebSocketClient client = null;
    private volatile String result;
    private volatile CountDownLatch latch;
    private volatile boolean firstConnect = true;

    @Override
    public synchronized void configure(URI uriToServer) {
        this.client = new WebSocketClient(uriToServer) {
            @Override
            public void onOpen(ServerHandshake handshakeData) {
            }

            @Override
            public void onMessage(String message) {
                logger.info("Msg from server - " + message);
                result = message;
                latch.countDown();
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
            String msg = "Ostis client not configured. Call the configure method first";
            logger.error(msg);
            throw new OstisClientConfigurationException(msg);
        }
        latch = new CountDownLatch(1);
        try {
            if (firstConnect) {
                client.connectBlocking();
                firstConnect = false;
            } else {
                client.reconnectBlocking();
            }
            logger.info("send msg to server - " + jsonRequest);
            client.send(jsonRequest);
            latch.await();
            client.closeBlocking();
        } catch (InterruptedException e) {
            String msg = "smth wrong in OstisClient";
            logger.error(msg);
            throw new ScMemoryException(msg, e);
        }
        logger.info("return value - " + result);
        return result;
    }
}
