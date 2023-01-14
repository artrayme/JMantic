package org.ostis.scmemory.websocketmemory.memory.message.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.model.pattern.ScPatternTriplet;
import org.ostis.scmemory.websocketmemory.memory.structures.AliasElement;
import org.ostis.scmemory.websocketmemory.message.request.GenerateByPatternRequest;
import org.ostis.scmemory.websocketmemory.message.request.RequestType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author artrayme
 * @since 0.7.0
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GenerateByPatternRequestImpl extends AbstractScRequest implements GenerateByPatternRequest {
    @JsonProperty("payload")
    private final Wrapper component = new Wrapper();

    public GenerateByPatternRequestImpl() {
        super(RequestType.GENERATE_TEMPLATE);
    }

    @JsonIgnore
    @Override
    public boolean addComponent(ScPatternTriplet component) {
        return this.component.addComponent(component);
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    private class Wrapper{
        @JsonProperty("templ")
        private final List<ScPatternTriplet> components = new ArrayList<>();

        @JsonProperty("params")  //aliases (not hehe)
        private final AliasElement aliases = new AliasElement();

        @JsonIgnore
        public boolean addComponent(ScPatternTriplet component) {
            return components.add(component);
        }

        @JsonIgnore
        public boolean isEmpty() {
            return components.isEmpty();
        }
    }
}