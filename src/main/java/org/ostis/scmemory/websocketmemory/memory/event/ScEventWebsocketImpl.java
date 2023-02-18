package org.ostis.scmemory.websocketmemory.memory.event;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.event.EventType;
import org.ostis.scmemory.model.event.ScEvent;
import org.ostis.scmemory.model.event.ScEventConsumer;

public class ScEventWebsocketImpl implements ScEvent {

    private final ScElement trackingElement;
    private final ScEventConsumer eventConsumer;

    public ScEventWebsocketImpl(ScElement targetElement, ScEventConsumer eventConsumer) {
        this.trackingElement = targetElement;
        this.eventConsumer = eventConsumer;
    }

    @Override
    public ScElement getTrackingElement() {
        return trackingElement;
    }

    @Override
    public EventType getEventType() {
        return eventConsumer.getEventType();
    }

    @Override
    public ScEventConsumer getEventConsumer() {
        return eventConsumer;
    }
}
