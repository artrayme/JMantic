package demo.artraymedemo.scmemory.websocketprotocol.request;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author artrayme
 * 10/22/21
 */
public enum RequestType {
    CREATE_ELEMENT("create_elements");

    private final String type;

    RequestType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}
