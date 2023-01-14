package org.ostis.scmemory.websocketmemory.message.response;

import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.6.1
 */
public interface GenerateByPatternResponse extends ScResponse {
    /**
     * A method that returns the addresses of the elements that were found
     *
     * @return addresses of generation results
     */
    Stream<Long> getFoundAddresses();
}