package org.jmantic.scmemory.websocketmemory.message.response;

import java.util.List;

/**
 * A response that contains data obtained from links
 * using a {@link org.jmantic.scmemory.websocketmemory.message.request.GetLinkContentRequest}
 *
 * @author Michael
 * @since 0.0.1
 */
public interface GetLinkContentResponse extends ScResponse {

    /**
     * Method for getting data from {@link org.jmantic.scmemory.model.element.link.ScLink}
     *
     * @return link data
     */
    List<Object> getContent();
}
