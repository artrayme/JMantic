package org.jmantic.model;

import java.util.stream.Stream;

public interface ScRequest {
    Long getId();
    RequestType getType();
    Stream<Payload> getPayload();
}
