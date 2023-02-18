package org.ostis.scmemory.model.event;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.ScEdge;

public interface OnDeleteEvent extends ScEventConsumer {
    void onEvent(ScElement element);

    default EventType getEventType() {
        return EventType.ON_DELETE_ELEMENT;
    }
}
