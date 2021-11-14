package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author artrayme
 * @since 0.2.0
 */

enum EdgeEndpointType {
    ADDR("addr"),
    REF("ref");

    @JsonValue
    private final String type;

    EdgeEndpointType(String type) {
        this.type = type;
    }
}
