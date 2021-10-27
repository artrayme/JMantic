package org.jmantic.scmemory.model.sync;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.model.websocket.core.OstisClient;
import org.jmantic.scmemory.model.websocket.message.ScMemoryView;
import org.jmantic.scmemory.model.websocket.message.request.CreateScElRequest;
import org.jmantic.scmemory.model.websocket.message.response.CreateScElResponse;
import org.jmantic.scmemory.model.websocket.sender.RequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael
 */
class RequestSenderImpl implements RequestSender {
    private final static Logger logger = LoggerFactory.getLogger(RequestSenderImpl.class);
    private final OstisClient client;
    private final ObjectMapper mapper;
    private final ObjectWriter writer;

    public RequestSenderImpl(OstisClient client) {
        this.client = client;
        mapper = new ObjectMapper();
        writer = mapper.writerWithView(ScMemoryView.Request.class);
    }

    @Override
    public CreateScElResponse sendCreateElRequest(CreateScElRequest request) throws ScMemoryException {
        try {
            String jsonRequest = writer.writeValueAsString(request);
            logger.info("Request to ostis - {}", request);
            String msg = client.sendToOstis(jsonRequest);
            CreateScElResponse response = mapper.readValue(msg, CreateScElResponseImpl.class);
            logger.info("Response from ostis - {}", response);
            return response;
        } catch (JsonProcessingException e) {
            String msg = "cant parse request - " + request;
            logger.error(msg);
            throw new ScMemoryException(msg, e);
        }
    }
}
