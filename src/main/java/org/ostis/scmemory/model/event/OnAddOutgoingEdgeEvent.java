package org.ostis.scmemory.model.event;

public interface OnAddOutgoingEdgeEvent extends OnEdgeEvent {
    default EventType getEventType() {
        return EventType.ON_ADD_OUTGOING_EDGE;
    }
}
