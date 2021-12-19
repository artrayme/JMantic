package org.jmantic.scmemory.websocketmemory.message.request;

import org.jmantic.scmemory.websocketmemory.sync.BasicPatternTriple;

import java.util.List;

public interface FindByPatternRequest extends ScRequest {
    List<BasicPatternTriple> getComponents();

    boolean addComponent(BasicPatternTriple component);

    boolean removeComponent(BasicPatternTriple component);
}
