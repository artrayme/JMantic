package org.ostis.scmemory.model.event;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.ScEdge;

public interface OnEdgeEvent extends ScEventConsumer {
    void onEvent(ScElement mainElement, ScEdge edge, ScElement edgeTarget);
    EventType getEventType();
}
