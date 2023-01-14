package org.ostis.scmemory.websocketmemory.memory.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.memory.structures.PayloadGeneratedByTemplateStruct;
import org.ostis.scmemory.websocketmemory.message.response.GenerateByPatternResponse;

import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.7.0
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GenerateByPatternResponseImpl extends AbstractScResponse implements GenerateByPatternResponse {

    @JsonProperty("payload")
    private PayloadGeneratedByTemplateStruct payloadGeneratedByTemplateStruct;

    @Override
    public Stream<Long> getFoundAddresses() {
        return payloadGeneratedByTemplateStruct.getFoundAddresses().stream();
    }

}
