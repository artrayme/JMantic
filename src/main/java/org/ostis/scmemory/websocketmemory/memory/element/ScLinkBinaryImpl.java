package org.ostis.scmemory.websocketmemory.memory.element;

import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLinkBinary;

import java.io.ByteArrayOutputStream;

public class ScLinkBinaryImpl extends ScEntity implements ScLinkBinary {
    @JsonProperty("type")
    private final LinkType linkType;
    @JsonProperty("content_type")
    private final String contentType = "binary";
    @JsonProperty("content")
    private ByteArrayOutputStream content;

    public ScLinkBinaryImpl(LinkType linkType){
        super("link");
        this.linkType = linkType;
    }

    public ScLinkBinaryImpl(LinkType linkType, Long address){
        super(
                "link",
                address);
        this.linkType = linkType;
    }

    @JsonIgnore
    @Override
    public LinkType getType() {
        return linkType;
    }

    @JsonIgnore
    @Override
    public ByteArrayOutputStream getContent() {
        return content;
    }

    @JsonIgnore
    public void setContent(ByteArrayOutputStream content) {
        this.content = content;
    }
}
