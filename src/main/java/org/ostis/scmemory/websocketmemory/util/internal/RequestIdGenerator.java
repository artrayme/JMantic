package org.ostis.scmemory.websocketmemory.util.internal;

import java.util.concurrent.atomic.AtomicLong;

public class RequestIdGenerator {
    private static final AtomicLong atomicInteger = new AtomicLong(1);

    public static long getId() {
        return atomicInteger.getAndIncrement();
    }
}
