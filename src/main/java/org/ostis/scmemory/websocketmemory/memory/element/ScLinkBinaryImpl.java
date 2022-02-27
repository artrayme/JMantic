package org.ostis.scmemory.websocketmemory.memory.element;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLinkBinary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ScLinkBinaryImpl extends ScEntity implements ScLinkBinary {
    @JsonProperty("type")
    private final LinkType linkType;
    @JsonProperty("content_type")
    private final String contentType = "binary";
    @JsonProperty("content")
    private String content;
    @JsonIgnore
    private ByteArrayOutputStream byteStream;

    public ScLinkBinaryImpl(LinkType linkType) {
        super("link");
        this.linkType = linkType;
    }

    public ScLinkBinaryImpl(LinkType linkType, Long address) {
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
    public ByteArrayOutputStream getContent() throws IOException {
        if (byteStream == null) {
            byteStream = new ByteArrayOutputStream();
            if (content != null) {
                byte[] bytes = Base64.getDecoder()
                                     .decode(content);
                byteStream.write(bytes);
            }
        }
        return byteStream;
    }

    @JsonIgnore
    public void setContent(ByteArrayOutputStream content) {
        this.byteStream = content;
        byte[] bytes = byteStream.toByteArray();
        this.content = Base64.getEncoder()
                             .encodeToString(bytes);
    }

    @JsonIgnore
    public void setContent(String content) throws IOException {
        this.content = content;
        if (content != null) {
            byte[] bytes = Base64.getDecoder()
                                 .decode(content);
            byteStream = new ByteArrayOutputStream();
            byteStream.write(bytes);
        }
    }
}
