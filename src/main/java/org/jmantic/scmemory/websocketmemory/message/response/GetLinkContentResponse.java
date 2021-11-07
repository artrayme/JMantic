package org.jmantic.scmemory.websocketmemory.message.response;

import java.util.List;

public interface GetLinkContentResponse extends ScResponse {
    List<Object> getContent();
}
