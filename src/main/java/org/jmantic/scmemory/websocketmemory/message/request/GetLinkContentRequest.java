package org.jmantic.scmemory.websocketmemory.message.request;

import org.jmantic.scmemory.model.element.link.ScLink;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface GetLinkContentRequest extends ScRequest {
    void addToRequest(long address);

    void resetRequest();
}
