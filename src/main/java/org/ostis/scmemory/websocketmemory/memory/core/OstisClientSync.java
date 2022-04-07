package org.ostis.scmemory.websocketmemory.memory.core;

import org.ostis.scmemory.websocketmemory.core.OstisClient;
import org.ostis.scmemory.websocketmemory.memory.exception.OstisClientConfigurationException;
import org.ostis.scmemory.websocketmemory.memory.exception.OstisConnectionException;
import org.ostis.scmemory.websocketmemory.memory.exception.OstisWebsocketClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

/**
 * {@link OstisClient} implementation for sending requests in JSON format
 *
 * @author artrayme
 * @since 0.2.0
 */
public class OstisClientSync implements OstisClient {

    private final static Logger logger = LoggerFactory.getLogger(OstisClientSync.class);
    private final OstisWebsocketClient webSocketClient;
    private String responseMassage;
    private CountDownLatch latch;
    private URI address;

    public OstisClientSync() {
        webSocketClient = new OstisWebsocketClient();
    }

    public OstisClientSync(URI serverUri) {
        webSocketClient = new OstisWebsocketClient(serverUri);
    }

    @Override
    public synchronized void configure(URI serverUri) {
        address = serverUri;
        webSocketClient.setAddress(address);
        logger.info(
                "ostis client is configured to the uri {}",
                serverUri);
    }

    @Override
    public synchronized void open() {
        try {
            webSocketClient.connect();
            logger.info(
                    "ostis client is connected to uri {}",
                    webSocketClient.getAddress());
        } catch (DeploymentException | IOException | OstisWebsocketClientException e) {
            logger.error(
                    "cannot connect to uri {}",
                    webSocketClient.getAddress());
            throw new OstisClientConfigurationException(
                    "cannot connect to this uri",
                    e);
        }
    }

    @Override
    public synchronized String sendToOstis(String jsonRequest) throws OstisConnectionException {
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
        }
        logger.info(
                "ostis client return response {}",
                responseMassage);
        return responseMassage;
    }
////    implementation 'org.glassfish.tyrus.bundles:tyrus-standalone-client:2.0.2'
    @Override
    public URI getConfiguration() {
        return address;
    }


    @Override
    public void close() throws Exception {
        webSocketClient.disconnect();
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
                throw new OstisWebsocketClientException("address is null");
            }

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();

            container.connectToServer(
                    this,
                    address);
        }

        public void disconnect() throws IOException {
            if (session != null) {
                session.close();
                session = null;
                logger.info(
                        "ostis client was disconnected from URI {}",
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
                    "ostis client send message {}",
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
            this.session = session;

            session.addMessageHandler((MessageHandler.Whole<String>) message -> {
                logger.info(
                        "ostis client catch response {}",
                        message);
                responseMassage = message;
                latch.countDown();
            });
        }

        public URI getAddress() {
            return address;
        }

        public void setAddress(URI address) {
            this.address = address;
        }
    }
}
