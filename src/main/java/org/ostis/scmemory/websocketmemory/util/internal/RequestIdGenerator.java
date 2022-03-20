package org.ostis.scmemory.websocketmemory.util.internal;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author artrayme
 * @since 0.5.0
 */
public class RequestIdGenerator {
    private static final AtomicLong atomicInteger = new AtomicLong(1);

    private RequestIdGenerator() {
    }

    public static long getId() {
        return atomicInteger.getAndIncrement();
    }
}
