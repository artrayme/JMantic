package org.ostis.scmemory.websocketmemory.message.request;

import org.ostis.scmemory.websocketmemory.memory.event.ScEventWebsocketImpl;

public interface EventRequest extends ScRequest {

    void subscribe(ScEventWebsocketImpl event);
    void unsubscribe(Long eventId);
}
