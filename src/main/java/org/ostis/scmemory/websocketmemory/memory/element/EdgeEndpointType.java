package org.ostis.scmemory.websocketmemory.memory.element;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author artrayme
 * @since 0.2.0
 */
public enum EdgeEndpointType {
    ADDR("addr"), REF("ref");

    @JsonValue
    private final String type;

    EdgeEndpointType(String type) {
        this.type = type;
    }
}
