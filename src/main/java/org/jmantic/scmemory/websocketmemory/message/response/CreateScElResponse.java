package org.jmantic.scmemory.websocketmemory.message.response;

import java.util.stream.Stream;

/**
 * @author Michael
 */
public interface CreateScElResponse extends ScResponse {
    Stream<Integer> getAddresses();
}
