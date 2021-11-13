package org.jmantic.scmemory.websocketmemory.sync;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.jmantic.scmemory.model.exception.ScMemoryConfigurationException;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.core.OstisClient;
import org.jmantic.scmemory.websocketmemory.sync.exception.OstisClientConfigurationException;
import org.jmantic.scmemory.websocketmemory.sync.exception.OstisConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

/**
 * @author Michael
 * @since 0.0.1
 */
@Deprecated (forRemoval = true, since = "0.2.0")
enum OstisClientImpl implements OstisClient {
    INSTANCE;
    private final static Logger logger = LoggerFactory.getLogger(OstisClient.class);
    private volatile WebSocketClient client = null;
    private volatile String result;
    private volatile CountDownLatch latch;
    private volatile boolean firstConnect = true;

    @Override
    public synchronized void configure(URI uriToServer) {
        this.client = new OstisWebSocketClient(uriToServer);
        firstConnect = true;
    }

    @Override
    public void open() {
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized String sendToOstis(String jsonRequest) throws OstisConnectionException {
        checkWebsocketClient();
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
            String msg = "something wrong with Threads in OstisClient";
            logger.error(msg);
            throw new OstisConnectionException(msg, e);
        } catch (Exception e) {
            String msg = "unknown error in OstisClient";
            logger.error(msg);
            throw new OstisConnectionException(msg, e);
        }
        logger.info("return value - " + result);
        return result;
    }

    private void checkWebsocketClient() {
        if (client == null) {
            String msg = "Ostis client not configured. Call the configure method first";
            logger.error(msg);
            throw new ScMemoryConfigurationException(new OstisClientConfigurationException(msg));
        }
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("This Ostis client is deprecated");
    }

    private class OstisWebSocketClient extends WebSocketClient {

        public OstisWebSocketClient(URI serverUri) {
            super(serverUri);
        }

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
    }
}
