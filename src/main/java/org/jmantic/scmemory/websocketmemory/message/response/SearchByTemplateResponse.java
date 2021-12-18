package org.jmantic.scmemory.websocketmemory.message.response;

import java.util.stream.Stream;

/**
 * Response containing information about found elements by templates.
 *
 * @author artrayme
 * @since 0.0.1
 */
@Deprecated(since = "0.3.2", forRemoval = true)
public interface SearchByTemplateResponse extends ScResponse {

    /**
     * A method that returns the addresses of the elements that were found
     *
     * @return addresses of search results
     */
    Stream<Stream<Long>> getFoundAddresses();
}
