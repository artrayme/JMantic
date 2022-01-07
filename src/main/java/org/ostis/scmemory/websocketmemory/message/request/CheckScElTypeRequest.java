package org.ostis.scmemory.websocketmemory.message.request;

import java.util.stream.Stream;

public interface CheckScElTypeRequest extends ScRequest{
    boolean add(Long addr);
    boolean remove(Long addr);
    Stream<Long> getAddresses();
}
