package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.core.OstisClient;
import org.jmantic.scmemory.websocketmemory.message.request.CreateScElRequest;
import org.jmantic.scmemory.websocketmemory.message.request.DeleteScElRequest;
import org.jmantic.scmemory.websocketmemory.message.request.GetLinkContentRequest;
import org.jmantic.scmemory.websocketmemory.message.request.SearchByTemplateRequest;
import org.jmantic.scmemory.websocketmemory.message.request.SetLinkContentRequest;
import org.jmantic.scmemory.websocketmemory.message.response.CreateScElResponse;
import org.jmantic.scmemory.websocketmemory.message.response.DeleteScElResponse;
import org.jmantic.scmemory.websocketmemory.message.response.GetLinkContentResponse;
import org.jmantic.scmemory.websocketmemory.message.response.SearchByTemplateResponse;
import org.jmantic.scmemory.websocketmemory.message.response.SetLinkContentResponse;
import org.jmantic.scmemory.websocketmemory.sender.RequestSender;
import org.jmantic.scmemory.websocketmemory.sync.exception.OstisConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael
 * @since 0.0.1
 */
class RequestSenderImpl implements RequestSender {
    private final static Logger logger = LoggerFactory.getLogger(RequestSenderImpl.class);
    private final OstisClient client;
    private final ObjectMapper mapper;
    private final ObjectWriter writer;

    public RequestSenderImpl(OstisClient client) {
        this.client = client;
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        writer = mapper.writer();
    }

    @Override
    public CreateScElResponse sendCreateElRequest(CreateScElRequest request) throws ScMemoryException {
        try {
            String jsonRequest = writer.writeValueAsString(request);
            logger.info("request to ostis - {}", request);
            String msg = client.sendToOstis(jsonRequest);
            CreateScElResponse response = mapper.readValue(msg, CreateScElResponseImpl.class);
            logger.info("response from ostis - {}", response);
            return response;
        } catch (JsonProcessingException e) {
            String msg = "cant parse request - " + request;
            logger.error(msg, e);
            throw new ScMemoryException(msg, e);
        } catch (OstisConnectionException e) {
            String msg = "something wrong with ostis websocket client";
            logger.error(msg, e);
            throw new ScMemoryException(msg, e);
        }
    }

    @Override
    public DeleteScElResponse sendDeleteElRequest(DeleteScElRequest request) throws ScMemoryException {
        try {
            String jsonRequest = writer.writeValueAsString(request);
            logger.info("request to ostis - {}", request);
            String msg = client.sendToOstis(jsonRequest);
            DeleteScElResponseImpl response = mapper.readValue(msg, DeleteScElResponseImpl.class);
            logger.info("response from ostis - {}", response);
            return response;
        } catch (JsonProcessingException e) {
            String msg = "cant parse request - " + request;
            logger.error(msg, e);
            throw new ScMemoryException(msg, e);
        } catch (OstisConnectionException e) {
            String msg = "something wrong with ostis websocket client";
            logger.error(msg, e);
            throw new ScMemoryException(msg, e);
        }

    }

    @Override
    public SearchByTemplateResponse sendSearchByTemplateRequest(SearchByTemplateRequest request) throws ScMemoryException {
        try {
            String jsonRequest = writer.writeValueAsString(request);
            logger.info("request to ostis - {}", jsonRequest);
            String msg = client.sendToOstis(jsonRequest);
            logger.info("msg - {}", msg);
            SearchByTemplateResponse response = mapper.readValue(msg, SearchByTemplateResponseImpl.class);
            logger.info("Response from ostis - {}", response);
            return response;
        } catch (JsonProcessingException e) {
            String msg = "cant parse request - " + request;
            logger.error(msg, e);
            throw new ScMemoryException(msg, e);
        } catch (OstisConnectionException e) {
            String msg = "something wrong with ostis websocket client";
            logger.error(msg, e);
            throw new ScMemoryException(msg, e);
        }
    }

    @Override
    public SetLinkContentResponse sendSetLinkContentRequest(SetLinkContentRequest request) throws ScMemoryException {
        try {
            String jsonRequest = writer.writeValueAsString(request);
            logger.info("request to ostis - {}", request);
            String msg = client.sendToOstis(jsonRequest);
            SetLinkContentResponse response = mapper.readValue(msg, SetLinkContentResponseImpl.class);
            logger.info("response from ostis - {}", response);
            return response;
        } catch (JsonProcessingException e) {
            String msg = "cant parse request - " + request;
            logger.error(msg, e);
            throw new ScMemoryException(msg, e);
        } catch (OstisConnectionException e) {
            String msg = "something wrong with ostis websocket client";
            logger.error(msg, e);
            throw new ScMemoryException(msg, e);
        }
    }

    @Override
    public GetLinkContentResponse sendGetLinkContentRequest(GetLinkContentRequest request) throws ScMemoryException {
        try {
            String jsonRequest = writer.writeValueAsString(request);
            logger.info("request to ostis - {}", request);
            String msg = client.sendToOstis(jsonRequest);
            GetLinkContentResponse response = mapper.readValue(msg, GetLinkContentResponseImpl.class);
            logger.info("response from ostis - {}", response);
            return response;
        } catch (JsonProcessingException e) {
            String msg = "cant parse request - " + request;
            logger.error(msg, e);
            throw new ScMemoryException(msg, e);
        } catch (OstisConnectionException e) {
            String msg = "something wrong with ostis websocket client";
            logger.error(msg, e);
            throw new ScMemoryException(msg, e);
        }
    }
}
