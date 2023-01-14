package org.ostis.scmemory.websocketmemory.memory.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.memory.structures.PayloadFoundByTemplateStruct;
import org.ostis.scmemory.websocketmemory.message.response.GenerateByPatternResponse;

import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.6.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GenerateByPatternResponseImpl extends AbstractScResponse implements GenerateByPatternResponse {

    @JsonProperty("payload")
    private PayloadFoundByTemplateStruct payloadFoundByTemplateStruct;

    @Override
    public Stream<Long> getFoundAddresses() {
        return payloadFoundByTemplateStruct.getFoundAddresses().get(0).stream();
    }

}
