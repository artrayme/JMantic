package org.ostis.scmemory.model.event;

import org.ostis.scmemory.model.element.ScElement;

/**
 * This is the main class of sc-events. It describes any sc-event, and has:
 * <ul>
 *     <li>sc-element -- is the sc-element this event is tracking</li>
 *     <li>type -- type of the event. All available types described in {@link EventType}</li>
 * </ul>
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScEvent {

    /**
     * @return tracking sc-element
     */
    ScElement getTrackingElement();

    /**
     * @return type of this event
     */
    EventType getEventType();

    ScEventConsumer getEventConsumer();
}
