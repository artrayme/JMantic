package org.ostis.scmemory.websocketmemory.message.response;

import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.3.2
 */
public interface FindByPatternResponse extends ScResponse {
    /**
     * A method that returns the addresses of the elements that were found
     *
     * @return addresses of search results
     */
    Stream<Stream<Long>> getFoundAddresses();
}
