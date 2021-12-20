package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

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
