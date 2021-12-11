package org.jmantic.scmemory.websocketmemory.sync;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.jmantic.scmemory.websocketmemory.core.OstisClient;
import org.jmantic.scmemory.websocketmemory.sync.exception.OstisClientConfigurationException;
import org.jmantic.scmemory.websocketmemory.sync.exception.OstisConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

/**
 * {@link OstisClient} implementation for sending requests in JSON format
 *
 * @author artrayme
 * @since 0.2.0
 */
class OstisClientSync implements OstisClient {

    private final static Logger logger = LoggerFactory.getLogger(OstisClientSync.class);
    private WebSocketClient webSocketClient;
    private String responseMassage;
    private CountDownLatch latch;

    private OstisClientSync() {

    }

    public OstisClientSync(URI serverUri) {
        configure(serverUri);
    }

    @Override
    public synchronized void configure(URI serverUri) {
        webSocketClient = new OstisWebsocketClient(serverUri);
        logger.info("ostis client is configured to the uri {}", serverUri);
    }

    @Override
    public synchronized void open() {
        try {
            webSocketClient.connectBlocking();
            logger.info("ostis client is connected to uri {}", webSocketClient.getURI());
        } catch (InterruptedException e) {
            logger.error("cannot connect to uri {}", webSocketClient.getURI());
            throw new OstisClientConfigurationException("cannot connect to this uri", e);
        }
    }

    @Override
    public synchronized String sendToOstis(String jsonRequest) throws OstisConnectionException {
        latch = new CountDownLatch(1);
        try {
            logger.info("try to send request: {}", jsonRequest);
            webSocketClient.send(jsonRequest);
            latch.await();
        } catch (InterruptedException e) {
            logger.error("try to send request: {}", jsonRequest);
            throw new OstisConnectionException();
        }
        logger.info("ostis client return response {}", responseMassage);
        return responseMassage;
    }


    @Override
    public void close() throws Exception {
        webSocketClient.closeBlocking();
        logger.info("ostis client closed");
    }

    /**
     * A class designed to send requests and receive responses from the base
     */
    private class OstisWebsocketClient extends WebSocketClient {

        public OstisWebsocketClient(URI serverUri) {
            super(serverUri);
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            logger.info("ostis client received handshake {}", handshakedata);
        }

        @Override
        public void onMessage(String message) {
            logger.info("ostis client catch response {}" + message);
            responseMassage = message;
            latch.countDown();
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            logger.info("ostis closed with code {} and reason {}. Is connection closed by server - {}", code, reason, remote);
        }

        @Override
        public void onError(Exception ex) {
            logger.error("something wrong at ostis websocket client", ex);
        }
    }
}
