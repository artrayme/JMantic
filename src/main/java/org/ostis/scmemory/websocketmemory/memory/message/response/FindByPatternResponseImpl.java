package org.ostis.scmemory.websocketmemory.memory.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.memory.structures.PayloadFoundByTemplateStruct;
import org.ostis.scmemory.websocketmemory.message.response.FindByPatternResponse;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.3.2
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FindByPatternResponseImpl extends AbstractScResponse implements FindByPatternResponse {

    @JsonProperty("payload")
    private PayloadFoundByTemplateStruct payloadFoundByTemplateStruct;

    @Override
    public Stream<Stream<Long>> getFoundAddresses() {
        return payloadFoundByTemplateStruct.getFoundAddresses()
                                           .stream()
                                           .map(Collection::stream);
    }

}
