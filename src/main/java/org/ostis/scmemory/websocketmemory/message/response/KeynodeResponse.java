package org.ostis.scmemory.websocketmemory.message.response;

import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.3.3
 */
public interface KeynodeResponse extends ScResponse {
    Stream<Long> getFindAddresses();
}
