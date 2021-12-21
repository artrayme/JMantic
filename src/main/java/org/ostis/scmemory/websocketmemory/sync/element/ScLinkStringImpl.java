package org.ostis.scmemory.websocketmemory.sync.element;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLinkString;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ScLinkStringImpl extends ScEntity implements ScLinkString {
    @JsonProperty("type")
    private final LinkType linkType;
    @JsonProperty("content_type")
    private final String contentType = "string";
    @JsonProperty("content")
    private String content;

    public ScLinkStringImpl(LinkType linkType) {
        super("link");
        this.linkType = linkType;
    }

    public ScLinkStringImpl(LinkType linkType, Long address) {
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
    public String getContent() {
        return content;
    }

    @JsonIgnore
    public void setContent(String content) {
        this.content = content;
    }

}
