package org.ostis.scmemory.websocketmemory.message.request;

import org.ostis.scmemory.websocketmemory.sync.structures.BasicPatternTriple;

import java.util.List;

/**
 * @author artrayme
 * @since 0.3.2
 */
public interface FindByPatternRequest extends ScRequest {
    List<BasicPatternTriple> getComponents();

    boolean addComponent(BasicPatternTriple component);

    boolean removeComponent(BasicPatternTriple component);
}
