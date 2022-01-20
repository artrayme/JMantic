package org.ostis.scmemory.websocketmemory.message.request;

import org.ostis.scmemory.model.pattern.ScPatternTriplet;

/**
 * @author artrayme
 * @since 0.3.2
 */
public interface FindByPatternRequest extends ScRequest {
    boolean addComponent(ScPatternTriplet component);
}
