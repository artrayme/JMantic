package org.ostis.scmemory.websocketmemory.message.request;

import org.ostis.scmemory.model.pattern.ScPatternTriplet;
import org.ostis.scmemory.websocketmemory.sync.structures.BasicPatternTriple;

import java.util.List;

/**
 * @author artrayme
 * @since 0.3.2
 */
public interface FindByPatternRequest extends ScRequest {
    List<ScPatternTriplet> getComponents();

    boolean addComponent(ScPatternTriplet component);

    boolean removeComponent(ScPatternTriplet component);
}
