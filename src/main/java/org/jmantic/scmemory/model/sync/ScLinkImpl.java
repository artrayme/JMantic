package org.jmantic.scmemory.model.sync;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;


/**
 * @author Michael
 */
public class ScLinkImpl implements ScLinkFloat {
    private final LinkType linkType;
    private float content;
    private long address;

    public ScLinkImpl(LinkType linkType) {
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
 */
class ScLinkIntegerImpl implements ScLinkInteger {
    private final LinkType linkType;
    private int content;
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
 */
class ScLinkStringImpl implements ScLinkString {
    private final LinkType linkType;
    private String content;
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
