package org.ostis.scmemory.websocketmemory.message.request;

import org.ostis.scmemory.websocketmemory.memory.structures.KeynodeStruct;

import java.util.List;

/**
 * @author artrayme
 * @since 0.3.3
 */
public interface KeynodeRequest extends ScRequest {
    void addAllIdtf(List<? extends KeynodeStruct> idtf);

}
