package org.ostis.scmemory.websocketmemory.memory.structures;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author artrayme
 * @since 0.6.0
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class KeynodeStruct {
    @JsonProperty("idtf")
    protected final String idtf;

    public KeynodeStruct(String idtf) {
        this.idtf = idtf;
    }
}
