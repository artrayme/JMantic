package org.ostis.scmemory.websocketmemory.memory.structures;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ostis.scmemory.model.element.node.NodeType;

/**
 * @author artrayme
 * @since 0.6.0
 */
@JsonPropertyOrder({ "command", "idtf", "elType" })
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ResolveKeynodeStruct extends KeynodeStruct{
    @JsonProperty("command")
    private final String command = "resolve";
    @JsonProperty("elType")
    private final NodeType type;

    public ResolveKeynodeStruct(String idtf, NodeType type) {
        super(idtf);
        this.type = type;
    }

}
