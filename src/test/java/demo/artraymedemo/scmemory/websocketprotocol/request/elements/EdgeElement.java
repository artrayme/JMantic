package demo.artraymedemo.scmemory.websocketprotocol.request.elements;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author artrayme
 * 10/22/21
 */
public class EdgeElement {
    @JsonProperty("type")
    protected int type;
    @JsonProperty("content")
    protected double content;
}
