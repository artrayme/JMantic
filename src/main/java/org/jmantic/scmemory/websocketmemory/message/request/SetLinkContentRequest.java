package org.jmantic.scmemory.websocketmemory.message.request;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface SetLinkContentRequest extends ScRequest {
    void addToRequest(long addresses, Object data);

    boolean resetRequest();
}
