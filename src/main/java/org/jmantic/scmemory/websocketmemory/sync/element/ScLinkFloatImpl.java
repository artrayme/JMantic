package org.jmantic.scmemory.websocketmemory.sync.element;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;

/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ScLinkFloatImpl extends ScEntity implements ScLinkFloat {
    @JsonProperty("type")
    private final LinkType linkType;
    @JsonProperty("content_type")
    private final String contentType = "float";
    @JsonProperty("content")
    private float content;

    public ScLinkFloatImpl(LinkType linkType) {
        super("link");
        this.linkType = linkType;
    }

    public ScLinkFloatImpl(LinkType linkType, Long address) {
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
    public float getContent() {
        return content;
    }

    @JsonIgnore
    public void setContent(float content) {
        this.content = content;
    }

}
