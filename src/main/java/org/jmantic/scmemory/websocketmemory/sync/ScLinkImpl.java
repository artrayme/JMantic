package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.websocketmemory.message.ScMemoryView;

import java.util.Objects;


/**
 * @author Michael
 * @since 0.0.1
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class ScLinkFloatImpl implements ScLinkFloat {
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("el")
    private final String element = "link";

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

    @JsonIgnore
    public void setAddress(long address) {
        this.address = address;
    }

    @JsonIgnore
    public void setContent(float content) {
        this.content = content;
    }

    @JsonIgnore
    @Override
    public Long getAddress() {
        return address;
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
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScLinkFloatImpl that = (ScLinkFloatImpl) o;
        return address == that.address;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "ScLinkFloatImpl{" +
                "element='" + element + '\'' +
                ", linkType=" + linkType +
                ", content=" + content +
                ", contentType='" + contentType + '\'' +
                ", address=" + address +
                '}';
    }
}


/**
 * @author Michael
 * @since 0.0.1
 */
class ScLinkIntegerImpl implements ScLinkInteger {
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("el")
    private final String element = "link";

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

    @JsonIgnore
    public void setAddress(long address) {
        this.address = address;
    }

    @JsonIgnore
    public void setContent(int content) {
        this.content = content;
    }

    @JsonIgnore
    @Override
    public Long getAddress() {
        return address;
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
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScLinkIntegerImpl that = (ScLinkIntegerImpl) o;
        return address == that.address;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "ScLinkIntegerImpl{" +
                "element='" + element + '\'' +
                ", linkType=" + linkType +
                ", content=" + content +
                ", contentType='" + contentType + '\'' +
                ", address=" + address +
                '}';
    }
}

/**
 * @author Michael
 * @since 0.0.1
 */
class ScLinkStringImpl implements ScLinkString {
    @JsonView(ScMemoryView.Request.class)
    @JsonProperty("el")
    private final String element = "link";

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

    @JsonIgnore
    public void setAddress(long address) {
        this.address = address;
    }

    @JsonIgnore
    public void setContent(String content) {
        this.content = content;
    }

    @JsonIgnore
    @Override
    public Long getAddress() {
        return address;
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
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScLinkStringImpl that = (ScLinkStringImpl) o;
        return address == that.address;
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "ScLinkStringImpl{" +
                "el='" + element + '\'' +
                ", linkType=" + linkType +
                ", content='" + content + '\'' +
                ", contentType='" + contentType + '\'' +
                ", address=" + address +
                '}';
    }
}

// TODO: 6.11.21 binary link
