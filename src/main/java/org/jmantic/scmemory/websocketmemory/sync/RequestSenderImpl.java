package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.core.OstisClient;
import org.jmantic.scmemory.websocketmemory.message.request.*;
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
        return send(request, CreateScElResponseImpl.class);
    }

    @Override
    public DeleteScElResponse sendDeleteElRequest(DeleteScElRequest request) throws ScMemoryException {
        return send(request, DeleteScElResponseImpl.class);
    }

    @Override
    public SearchByTemplateResponse sendSearchByTemplateRequest(SearchByTemplateRequest request) throws ScMemoryException {
        return send(request, SearchByTemplateResponseImpl.class);
    }

    @Override
    public SetLinkContentResponse sendSetLinkContentRequest(SetLinkContentRequest request) throws ScMemoryException {
        return send(request, SetLinkContentResponseImpl.class);
    }

    @Override
    public GetLinkContentResponse sendGetLinkContentRequest(GetLinkContentRequest request) throws ScMemoryException {
        return send(request, GetLinkContentResponseImpl.class);
    }

    private <T1, T2> T2 send(T1 request, Class<T2> responseClassType) throws ScMemoryException {
        try {
            String jsonRequest = writer.writeValueAsString(request);
            String msg = client.sendToOstis(jsonRequest);
            return mapper.readValue(msg, responseClassType);
        } catch (JsonProcessingException e) {
            String msg = "cant parse request/response - " + request;
            logger.error(msg, e);
            throw new ScMemoryException(msg, e);
        } catch (OstisConnectionException e) {
            throw new ScMemoryException(e);
        }
    }
}
