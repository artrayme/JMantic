package org.jmantic.scmemory.websocketmemory.sync.element;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ScLinkIntegerImpl extends ScEntity implements ScLinkInteger {
    @JsonProperty("type")
    private final LinkType linkType;
    @JsonProperty("content_type")
    private final String contentType = "int";
    @JsonProperty("content")
    private int content;

    public ScLinkIntegerImpl(LinkType linkType) {
        super("link");
        this.linkType = linkType;
    }

    public ScLinkIntegerImpl(LinkType linkType, Long address) {
        super("link", address);
        this.linkType = linkType;
    }

    @JsonIgnore
    @Override
    public LinkType getType() {
        return linkType;
    }

    @JsonIgnore
    @Override
    public int getContent() {
        return content;
    }

    @JsonIgnore
    public void setContent(int content) {
        this.content = content;
    }

}
