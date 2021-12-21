package org.ostis.scmemory.websocketmemory.message.response;

import org.ostis.scmemory.model.element.link.LinkContentType;

import java.util.List;

/**
 * A response that contains data obtained from links
 * using a {@link org.ostis.scmemory.websocketmemory.message.request.GetLinkContentRequest}
 *
 * @author Michael
 * @since 0.0.1
 */
public interface GetLinkContentResponse extends ScResponse {

    /**
     * Method for getting data from {@link org.ostis.scmemory.model.element.link.ScLink}
     *
     * @return link data
     */
    List<Object> getContent();

    List<LinkContentType> getType();
}
