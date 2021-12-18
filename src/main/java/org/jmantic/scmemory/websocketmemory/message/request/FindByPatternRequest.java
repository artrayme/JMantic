package org.jmantic.scmemory.websocketmemory.message.request;

import org.jmantic.scmemory.model.pattern.PatternElement;

import java.util.List;

public interface FindByPatternRequest extends ScRequest {
    List<PatternElement> getComponents();

    boolean addComponent(PatternElement component);

    boolean removeComponent(PatternElement component);
}
