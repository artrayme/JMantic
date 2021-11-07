package org.jmantic.scmemory.websocketmemory.message.response;

import java.util.List;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface GetLinkContentResponse extends ScResponse {
    List<Object> getContent();
}
