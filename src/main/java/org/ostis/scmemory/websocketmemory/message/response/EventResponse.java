package org.ostis.scmemory.websocketmemory.message.response;

import java.util.stream.Stream;

public interface EventResponse extends ScResponse{
    Stream<Long> getEventIds();
}
