package org.ostis.scmemory.websocketmemory.memory.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.message.response.SetLinkContentResponse;

import java.util.List;

/**
 * Implementation of the {@link SetLinkContentResponse}. The payload part consists of a {@link List}
 * of boolean elements (true or false for a set command result).
 *
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SetLinkContentResponseImpl extends AbstractScResponse implements SetLinkContentResponse {
    @JsonProperty("payload")
    private List<Boolean> statusOfOperations;

    @Override
    public List<Boolean> getOperationStatus() {
        return statusOfOperations;
    }

}
