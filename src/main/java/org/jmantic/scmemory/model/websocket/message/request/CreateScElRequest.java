package org.jmantic.scmemory.model.websocket.message.request;

import org.jmantic.scmemory.model.element.ScElement;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Michael
 */
public interface CreateScElRequest extends ScRequest {
    void replaceRequest(List<ScElement> elements);

    boolean addElementToRequest(ScElement element);

    List<ScElement> resetRequest();
}
