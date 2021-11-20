package org.jmantic.scmemory.websocketmemory.message.request;

import org.jmantic.scmemory.model.element.ScElement;

import java.util.List;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface CreateScElRequest extends ScRequest {
    boolean addToRequest(List<? extends ScElement> elements);

    boolean addElementToRequest(ScElement element);

    void resetRequest();
}
