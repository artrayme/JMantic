package org.ostis.scmemory.websocketmemory.message.request;

import java.util.List;

/**
 * @author artrayme
 * @since 0.3.3
 */
public interface FindKeynodeRequest extends ScRequest {
    void addAllIdtf(List<String> idtf);

}
