package org.ostis.scmemory.websocketmemory.message.request;

import org.ostis.scmemory.model.pattern.ScPatternTriplet;

/**
 * @author artrayme
 * @since 0.7.0
 */
public interface GenerateByPatternRequest extends ScRequest {
    boolean addComponent(ScPatternTriplet component);
}
