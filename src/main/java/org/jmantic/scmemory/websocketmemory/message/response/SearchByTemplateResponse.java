package org.jmantic.scmemory.websocketmemory.message.response;

import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.0.1
 */
public interface SearchByTemplateResponse extends ScResponse {
    Stream<Stream<Long>> getFoundAddresses();
}
