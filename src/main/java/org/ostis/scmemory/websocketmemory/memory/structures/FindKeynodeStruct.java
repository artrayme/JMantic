package org.ostis.scmemory.websocketmemory.memory.structures;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author artrayme
 * @since 0.3.3
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FindKeynodeStruct {
    @JsonProperty("command")
    private final String command = "find";
    @JsonProperty("idtf")
    private final String idtf;

    public FindKeynodeStruct(String idtf) {
        this.idtf = idtf;
    }

}
