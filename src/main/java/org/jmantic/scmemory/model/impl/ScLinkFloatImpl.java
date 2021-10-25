package org.jmantic.scmemory.model.impl;

import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;

public class ScLinkFloatImpl implements ScLinkFloat {
    private final LinkType linkType;
    private float content;
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
}
