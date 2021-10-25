package org.jmantic.scmemory.model.impl;

import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkString;

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
}
