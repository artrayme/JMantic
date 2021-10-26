package org.jmantic.scmemory.model.websocket.request;

import org.jmantic.scmemory.model.element.ScElement;

import java.util.stream.Stream;

/**
 * @author Michael
 */
public interface CreateScElRequest extends ScRequest {
    boolean addElementToRequest(ScElement element);

    Stream<ScElement> resetRequest();
}
