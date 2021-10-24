package org.jmantic.scmemory.model.event;

import org.jmantic.scmemory.model.element.ScElement;

/**
 * This is the main class of sc-events. It describes any sc-event, and has:
 * <ul>
 *     <li>id -- unique number. Can be used to identify events</li>
 *     <li>sc-element -- is the sc-element this event is tracking</li>
 *     <li>type -- type of the event. All available types described in {@link EventType}</li>
 * </ul>
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScEvent {
    /**
     * @return if of this event
     */
    Long getId();

    /**
     * @return tracking sc-element
     */
    ScElement getTrackingElement();

    /**
     * @return type of this event
     */
    EventType getEventType();
}
