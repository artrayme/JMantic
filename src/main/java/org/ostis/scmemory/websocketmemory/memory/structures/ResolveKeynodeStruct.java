package org.ostis.scmemory.websocketmemory.memory.structures;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author artrayme
 * @since 0.6.0
 */
@JsonPropertyOrder({ "command", "idtf" })
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ResolveKeynodeStruct extends KeynodeStruct{
    @JsonProperty("command")
    private final String command = "resolve";

    public ResolveKeynodeStruct(String idtf) {
        super(idtf);
    }

}
