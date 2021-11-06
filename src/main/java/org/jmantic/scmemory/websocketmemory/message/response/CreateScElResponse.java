package org.jmantic.scmemory.websocketmemory.message.response;

import java.util.stream.Stream;

/**
 * @author Michael
 * @since 0.0.1
 */
public interface CreateScElResponse extends ScResponse {
    Stream<Long> getAddresses();
}
