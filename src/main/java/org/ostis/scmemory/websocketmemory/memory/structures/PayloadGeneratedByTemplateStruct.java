package org.ostis.scmemory.websocketmemory.memory.structures;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author artrayme
 * @since 0.7.0
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PayloadGeneratedByTemplateStruct {

    @JsonProperty("aliases")
    private AliasesStruct aliases = new AliasesStruct();

    @JsonProperty("addrs")
    private List<Long> foundAddresses;

    public AliasesStruct getAliases() {
        return aliases;
    }

    public List<Long> getFoundAddresses() {
        return foundAddresses;
    }

    private static class AliasesStruct {
    }
}
