package org.ostis.scmemory.model.event;

public interface OnAddIngoingEdgeEvent extends OnEdgeEvent{
    default EventType getEventType() {
        return EventType.ON_ADD_INGOING_EDGE;
    }
}
