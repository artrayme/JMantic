package org.jmantic.scmemory.websocketmemory.message.request;

import java.util.List;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface GetLinkContentRequest extends ScRequest {
    boolean addToRequest(List<Long> addresses);

    boolean addAddressToRequest(long address);

    void resetRequest();
}
