package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.pattern.PatternElement;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AliasPatternElement implements PatternElement {
    @JsonProperty("type")
    private final String type = "alias";
    @JsonProperty("value")
    private final String alias;

    AliasPatternElement(String alias) {
        this.alias = alias;
    }

}
