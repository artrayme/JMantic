package org.jmantic.scmemory.websocketmemory.message.response;

import java.util.stream.Stream;

/**
 * @author artrayme
 * 11/6/21
 */
public interface SearchByTemplateResponse extends ScResponse {
    Stream<Stream<Long>> getFoundAddresses();
}
