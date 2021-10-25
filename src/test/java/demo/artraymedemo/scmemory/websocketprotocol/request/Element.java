package demo.artraymedemo.scmemory.websocketprotocol.request;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author artrayme
 * 10/22/21
 */
public enum Element {
    NODE("node"),
    EDGE("edge"),
    LINK("link");
    private final String type;

    Element(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}