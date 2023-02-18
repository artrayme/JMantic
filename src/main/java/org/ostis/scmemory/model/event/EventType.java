package org.ostis.scmemory.model.event;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum of available {@link ScEventConsumer} types
 *
 * @author artrayme
 * @since 0.0.1
 */
public enum EventType {

    ON_ADD_OUTGOING_EDGE("add_outgoing_edge"),
    ON_ADD_INGOING_EDGE("add_ingoing_edge"),

    // ON_REMOVE_OUTGOING_EDGE("remove_outgoing_edge"),
    // ON_REMOVE_INGOING_EDGE("remove_ingoing_edge"),
    // ON_CONTENT_CHANGE("content_change"),
    ON_DELETE_ELEMENT("delete_element");

    @JsonValue
    private final String type;

    EventType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
