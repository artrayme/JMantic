package org.jmantic.scmemory.websocketmemory.sync.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.response.FindByPatternResponse;
import org.jmantic.scmemory.websocketmemory.sync.structures.PayloadFoundByTemplateStruct;

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
        return payloadFoundByTemplateStruct.getFoundAddresses().stream().map(Collection::stream);
    }

}
