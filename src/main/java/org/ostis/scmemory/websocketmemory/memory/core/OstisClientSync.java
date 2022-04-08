package org.ostis.scmemory.websocketmemory.memory.core;

import jakarta.websocket.CloseReason;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.MessageHandler;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import org.ostis.scmemory.websocketmemory.core.OstisClient;
import org.ostis.scmemory.websocketmemory.memory.exception.OstisClientConfigurationException;
import org.ostis.scmemory.websocketmemory.memory.exception.OstisConnectionException;
import org.ostis.scmemory.websocketmemory.memory.exception.OstisWebsocketClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link OstisClient} implementation for sending requests in JSON format
 *
 * @author artrayme
 * @since 0.2.0
 */
public class OstisClientSync implements OstisClient {

    private final static Logger logger = LoggerFactory.getLogger(OstisClientSync.class);
    private final OstisWebsocketClient webSocketClient;
    private final ReentrantLock lock = new ReentrantLock();
    private String responseMassage;
    private CountDownLatch latch;
    private URI address;

    public OstisClientSync() {
        webSocketClient = new OstisWebsocketClient();
    }

    public OstisClientSync(URI serverUri) {
        webSocketClient = new OstisWebsocketClient(serverUri);
        address = serverUri;
    }

    @Override
    public void configure(URI serverUri) {
        lock.lock();
        address = serverUri;
        webSocketClient.setAddress(serverUri);
        lock.unlock();
        logger.info(
                "ostis client is configured to the URI: {}",
                serverUri);
    }

    @Override
    public void open() {
        try {
            lock.lock();
            webSocketClient.connect();
            logger.info(
                    "ostis client is connected to URI: {}",
                    webSocketClient.getAddress());
        } catch (DeploymentException | IOException | OstisWebsocketClientException e) {
            String msg = "cannot connect to URI: " + webSocketClient.getAddress();
            logger.error(msg);
            throw new OstisClientConfigurationException(
                    msg,
                    e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String sendToOstis(String jsonRequest) throws OstisConnectionException {
        lock.lock();
        latch = new CountDownLatch(1);
        try {
            logger.info(
                    "try to send request: {}",
                    jsonRequest);
            webSocketClient.sendMessage(jsonRequest);
            latch.await();
        } catch (InterruptedException e) {
            String msg = "some exception in concurrency";
            logger.error(
                    msg,
                    e);
            throw new OstisConnectionException(
                    msg,
                    e);
        } catch (OstisWebsocketClientException e) {
            String msg = "you should open connection first";
            logger.error(
                    msg,
                    jsonRequest);
            throw new OstisConnectionException(
                    msg,
                    e);
        } finally {
            lock.unlock();
        }
        logger.info(
                "ostis client return response: {}",
                responseMassage);
        return responseMassage;
    }

    @Override
    public URI getConfiguration() {
        return address;
    }

    @Override
    public boolean isOpen() {
        return webSocketClient.isOpen();
    }


    @Override
    public void close() throws Exception {
        lock.lock();
        webSocketClient.disconnect();
        lock.unlock();
        logger.info("ostis client closed");
    }

    /**
     * A class designed to send requests and receive responses from the base
     */
    private class OstisWebsocketClient extends Endpoint {
        private URI address;
        private Session session;

        public OstisWebsocketClient() {
        }

        public OstisWebsocketClient(URI address) {
            this.address = address;
        }

        public void connect() throws DeploymentException, IOException, OstisWebsocketClientException {
            disconnect();
            if (address == null) {
                throw new OstisWebsocketClientException("address is not correct: null");
            }

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();

            session = container.connectToServer(
                    this,
                    address);

            session.addMessageHandler((MessageHandler.Whole<String>) message -> {
                logger.info(
                        "ostis websocket client catch response: {}",
                        message);
                responseMassage = message;
                latch.countDown();
            });
        }

        public void disconnect() throws IOException {
            if (session != null) {
                session.close();
                session = null;
                logger.info(
                        "ostis websocket client was disconnected from URI: {}",
                        address);
            }
        }

        public void sendMessage(String message) throws OstisWebsocketClientException {
            if (session == null) {
                throw new OstisWebsocketClientException("session is null");
            }

            session.getAsyncRemote()
                   .sendText(message);
            logger.info(
                    "ostis websocket client send message {}",
                    message);
        }

        @Override
        public void onClose(Session session, CloseReason closeReason) {
            super.onClose(
                    session,
                    closeReason);
        }

        @Override
        public void onError(Session session, Throwable thr) {
            super.onError(
                    session,
                    thr);
        }

        @Override
        public void onOpen(Session session, EndpointConfig config) {

        }

        public boolean isOpen() {
            return session.isOpen();
        }

        public URI getAddress() {
            return address;
        }

        public void setAddress(URI address) {
            this.address = address;
        }
    }
}