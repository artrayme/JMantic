package org.ostis.scmemory.websocketmemory.memory.message.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.event.EventType;
import org.ostis.scmemory.websocketmemory.memory.event.ScEventWebsocketImpl;
import org.ostis.scmemory.websocketmemory.message.request.RequestType;
import org.ostis.scmemory.websocketmemory.message.request.EventRequest;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EventRequestImpl extends AbstractScRequest implements EventRequest {

    @JsonProperty("payload")
    private EventsToCreate events = new EventsToCreate();

    public EventRequestImpl() {
        super(RequestType.EVENTS);
    }

    @Override
    public void subscribe(ScEventWebsocketImpl event) {
        events.subscribe(event.getTrackingElement(), event.getEventType());
    }

    @Override
    public void unsubscribe(Long eventId) {
        events.unsubscribe(eventId);
    }

    private static class EventsToCreate{
        @JsonProperty("create")
        List<EventStruct> events = new ArrayList<>();
        @JsonProperty("delete")
        List<Long> toDelete = new ArrayList<>();

        public void subscribe(ScElement trackingElement, EventType eventType){
            events.add(new EventStruct(
                    trackingElement,
                    eventType));
        }

        public void unsubscribe(Long eventId) {
            toDelete.add(eventId);
        }

        private static class EventStruct{
            @JsonProperty("type")
            private String type;
            @JsonProperty("addr")
            private Long trackingElementAddr;

            public EventStruct(ScElement trackingElement, EventType type) {
                this.type = type.getType();
                this.trackingElementAddr = trackingElement.getAddress();
            }
        }
    }
}
