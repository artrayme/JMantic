package org.jmantic.scmemory.model.websocket.response;

import java.util.stream.Stream;

/**
 * @author Michael
 */
public interface CreateScElResponse extends ScResponse {
    Stream<Integer> getAddresses();
}
