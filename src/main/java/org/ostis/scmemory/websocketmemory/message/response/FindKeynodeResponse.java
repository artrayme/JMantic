package org.ostis.scmemory.websocketmemory.message.response;

import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.3.3
 */
public interface FindKeynodeResponse extends ScResponse {
    Stream<Long> getFindAddresses();
}
