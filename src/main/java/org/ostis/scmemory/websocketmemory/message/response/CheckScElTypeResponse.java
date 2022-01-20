package org.ostis.scmemory.websocketmemory.message.response;

import java.util.stream.Stream;

public interface CheckScElTypeResponse extends ScResponse {
    Stream<Object> getTypes();
}
