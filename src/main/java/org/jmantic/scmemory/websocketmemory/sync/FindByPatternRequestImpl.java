package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.websocketmemory.message.request.FindByPatternRequest;
import org.jmantic.scmemory.websocketmemory.message.request.RequestType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author artrayme
 * @since 0.3.2
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FindByPatternRequestImpl extends AbstractScRequest implements FindByPatternRequest {
    @JsonProperty("payload")
    private final List<BasicPatternTriple> components = new ArrayList<>();

    public FindByPatternRequestImpl() {
        super(1, RequestType.SEARCH_TEMPLATE);
    }

    @JsonIgnore
    @Override
    public boolean isEmpty() {
        return components.isEmpty();
    }

    @JsonIgnore
    @Override
    public List<BasicPatternTriple> getComponents() {
        return Collections.unmodifiableList(components);
    }

    @JsonIgnore
    @Override
    public boolean addComponent(BasicPatternTriple component) {
        return components.add(component);
    }

    @JsonIgnore
    @Override
    public boolean removeComponent(BasicPatternTriple component) {
        return components.remove(component);
    }
}
