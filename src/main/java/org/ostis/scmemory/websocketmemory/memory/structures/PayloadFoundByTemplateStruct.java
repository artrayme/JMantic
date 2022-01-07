package org.ostis.scmemory.websocketmemory.memory.structures;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author artrayme
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PayloadFoundByTemplateStruct {

    //    ToDo aliases
    @JsonProperty("aliases")
    private AliasesStruct aliases = new AliasesStruct();

    @JsonProperty("addrs")
    private List<List<Long>> foundAddresses;

    public AliasesStruct getAliases() {
        return aliases;
    }

    public List<List<Long>> getFoundAddresses() {
        return foundAddresses;
    }

    private static class AliasesStruct {
        @Override
        public String toString() {
            return "AliasesStruct{}";
        }
    }

    @Override
    public String toString() {
        return "PayloadFoundByTemplateStruct{" +
                "aliases=" + aliases +
                ", foundAddresses=" + foundAddresses +
                '}';
    }
}
