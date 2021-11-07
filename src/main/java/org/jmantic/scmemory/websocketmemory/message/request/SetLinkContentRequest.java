package org.jmantic.scmemory.websocketmemory.message.request;

import org.jmantic.scmemory.model.element.link.ScLink;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface SetLinkContentRequest extends ScRequest {
    void addToRequest(ScLink link, Object data);

    void resetRequest();
}
