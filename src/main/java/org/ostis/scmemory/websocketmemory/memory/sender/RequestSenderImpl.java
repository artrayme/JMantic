package org.ostis.scmemory.websocketmemory.memory.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.websocketmemory.core.OstisClient;
import org.ostis.scmemory.websocketmemory.memory.exception.OstisConnectionException;
import org.ostis.scmemory.websocketmemory.memory.message.response.*;
import org.ostis.scmemory.websocketmemory.message.request.*;
import org.ostis.scmemory.websocketmemory.message.response.*;
import org.ostis.scmemory.websocketmemory.sender.RequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * {@link RequestSender} implementation for serialization and sending requests
 * using the {@link OstisClient}, as well as for deserializing responses.
 * Deserialization and serialization occurs using the JACKSON-databind library.
 *
 * @author Michael
 * @since 0.0.1
 */
public class RequestSenderImpl implements RequestSender {
    private final static Logger logger = LoggerFactory.getLogger(RequestSenderImpl.class);
    private final OstisClient client;
    private final ObjectMapper mapper;
    private final ObjectWriter writer;

    public RequestSenderImpl(OstisClient client) {
        this.client = client;
        mapper = new ObjectMapper();
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        writer = mapper.writer();
    }

    @Override
    public URI getAddress() {
        return client.getAddress();
    }

    @Override
    public CreateScElResponse sendCreateElRequest(CreateScElRequest request) throws ScMemoryException {
        return send(
                request,
                CreateScElResponseImpl.class);
    }

    @Override
    public DeleteScElResponse sendDeleteElRequest(DeleteScElRequest request) throws ScMemoryException {
        return send(
                request,
                DeleteScElResponseImpl.class);
    }

    @Override
    public FindByPatternResponse sendFindByPatternRequest(FindByPatternRequest request) throws ScMemoryException {
        return send(
                request,
                FindByPatternResponseImpl.class);
    }

    @Override
    public GenerateByPatternResponse sendGenerateByPatternRequest(GenerateByPatternRequest request) throws ScMemoryException {
        return send(
                request,
                GenerateByPatternResponseImpl.class);
    }

    @Override
    public SetLinkContentResponse sendSetLinkContentRequest(SetLinkContentRequest request) throws ScMemoryException {
        return send(
                request,
                SetLinkContentResponseImpl.class);
    }

    @Override
    public GetLinkContentResponse sendGetLinkContentRequest(GetLinkContentRequest request) throws ScMemoryException {
        return send(
                request,
                GetLinkContentResponseImpl.class);
    }

    @Override
    public KeynodeResponse sendKeynodeRequest(KeynodeRequest request) throws ScMemoryException {
        return send(
                request,
                KeynodeResponseImpl.class);
    }

    @Override
    public CheckScElTypeResponse sendCheckScElTypeRequest(CheckScElTypeRequest request) throws ScMemoryException {
        return send(
                request,
                CheckScElTypeResponseImpl.class);
    }

    /**
     * The method that serializes the {@link ScRequest} object, sends a
     * request to the database through the {@link OstisClient} and deserializes the response
     * into a {@link org.ostis.scmemory.websocketmemory.message.response.ScResponse} object
     *
     * @param request           request
     * @param responseClassType type of response
     * @param <T1>              request generic
     * @param <T2>              responseClass generic
     * @return {@link org.ostis.scmemory.websocketmemory.message.response.ScResponse} with all the necessary data
     * @throws ScMemoryException if there is any problem with the {@link OstisClient}
     */
    private <T1, T2> T2 send(T1 request, Class<T2> responseClassType) throws ScMemoryException {
        try {
            String jsonRequest = writer.writeValueAsString(request);
            String msg = client.sendToOstis(jsonRequest);
            return mapper.readValue(
                    msg,
                    responseClassType);
        } catch (JsonProcessingException e) {
            String msg = "cant parse request/response - " + request;
            logger.error(
                    msg,
                    e);
            throw new ScMemoryException(
                    msg,
                    e);
        } catch (OstisConnectionException e) {
            throw new ScMemoryException(e);
        }
    }
}
