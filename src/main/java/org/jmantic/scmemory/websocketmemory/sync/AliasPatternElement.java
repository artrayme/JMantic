package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author artrayme
 * @since 0.3.2
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
final class AliasPatternElement implements PatternElement {
    @JsonProperty("type")
    private final String type = "alias";
    @JsonProperty("value")
    private final String alias;

    AliasPatternElement(String alias) {
        this.alias = alias;
    }

}
