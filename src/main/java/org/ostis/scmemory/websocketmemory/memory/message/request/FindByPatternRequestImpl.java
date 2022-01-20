package org.ostis.scmemory.websocketmemory.memory.message.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.model.pattern.ScPatternTriplet;
import org.ostis.scmemory.websocketmemory.message.request.FindByPatternRequest;
import org.ostis.scmemory.websocketmemory.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author artrayme
 * @since 0.3.2
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FindByPatternRequestImpl extends AbstractScRequest implements FindByPatternRequest {
    @JsonProperty("payload")
    private final List<ScPatternTriplet> components = new ArrayList<>();

    public FindByPatternRequestImpl() {
        super(RequestType.SEARCH_TEMPLATE);
    }

    @JsonIgnore
    @Override
    public boolean addComponent(ScPatternTriplet component) {
        return components.add(component);
    }
}
