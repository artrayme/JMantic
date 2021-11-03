package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.websocketmemory.message.ScMemoryView;


/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScLinkFloatImpl implements ScLinkFloat {
    @JsonView(ScMemoryView.Request.class)
    private final String el = "link";
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("type")
    private final LinkType linkType;
    @JsonView(ScMemoryView.Request.class)
    private float content;
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("content_type")
    private final String contentType = "float";
    @JsonView(ScMemoryView.Address.class)
    private long address;

    public ScLinkFloatImpl(LinkType linkType) {
        this.linkType = linkType;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public void setContent(int content) {
        this.content = content;
    }

    @Override
    public Long getAddress() {
        return address;
    }

    @Override
    public LinkType getType() {
        return linkType;
    }

    @Override
    public float getContent() {
        return content;
    }

    @JsonIgnore
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ScLinkImpl{");
        sb.append("linkType=").append(linkType);
        sb.append(", content=").append(content);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}


/**
 * @author Michael
 * @since 0.0.1
 */
class ScLinkIntegerImpl implements ScLinkInteger {
    @JsonView(ScMemoryView.Request.class)
    private final String el = "link";
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("type")
    private final LinkType linkType;
    @JsonView(ScMemoryView.Request.class)
    private int content;
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("content_type")
    private final String contentType = "int";
    @JsonView(ScMemoryView.Address.class)
    private long address;

    public ScLinkIntegerImpl(LinkType linkType) {
        this.linkType = linkType;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public void setContent(int content) {
        this.content = content;
    }

    @Override
    public Long getAddress() {
        return address;
    }

    @Override
    public LinkType getType() {
        return linkType;
    }

    @Override
    public int getContent() {
        return content;
    }

    @JsonIgnore
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ScLinkIntegerImpl{");
        sb.append("linkType=").append(linkType);
        sb.append(", content=").append(content);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}

/**
 * @author Michael
 * @since 0.0.1
 */
class ScLinkStringImpl implements ScLinkString {
    @JsonView(ScMemoryView.Request.class)
    private final String el = "link";
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("type")
    private final LinkType linkType;
    @JsonView(ScMemoryView.Request.class)
    private String content;
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("content_type")
    private final String contentType = "string";
    @JsonView(ScMemoryView.Address.class)
    private long address;

    public ScLinkStringImpl(LinkType linkType) {
        this.linkType = linkType;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Long getAddress() {
        return address;
    }

    @Override
    public LinkType getType() {
        return linkType;
    }

    @Override
    public String getContent() {
        return content;
    }

    @JsonIgnore
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ScLinkStringImpl{");
        sb.append("linkType=").append(linkType);
        sb.append(", content='").append(content).append('\'');
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}

//todo binary link
