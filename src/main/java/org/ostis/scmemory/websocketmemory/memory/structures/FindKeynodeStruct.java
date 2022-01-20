package org.ostis.scmemory.websocketmemory.memory.structures;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author artrayme
 * @since 0.3.3
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonPropertyOrder({"command", "idtf"})
public class FindKeynodeStruct extends KeynodeStruct {
    @JsonProperty("command")
    private final String command = "find";

    public FindKeynodeStruct(String idtf) {
        super(idtf);
    }

}
