package context.mock;

import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;

import java.util.Objects;

/**
 * @author artrayme
 * 10/24/21
 */
class MockScLinkString implements ScLinkString {
    private final Long address;
    private final LinkType type;
    private String content;

    MockScLinkString(Long address, LinkType type, String content) {
        this.address = address;
        this.type = type;
        this.content = content;
    }

    @Override
    public Long getAddress() {
        return address;
    }

    @Override
    public LinkType getType() {
        return type;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MockScLinkString that = (MockScLinkString) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
