package context.mock;

import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;

import java.util.Objects;

/**
 * @author artrayme
 * 10/24/21
 */
class MockScLinkInteger implements ScLinkInteger {
    private final Long address;
    private final LinkType type;
    private int content;

    MockScLinkInteger(Long address, LinkType type, int content) {
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
    public int getContent() {
        return content;
    }

    public void setContent(int content){
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MockScLinkInteger that = (MockScLinkInteger) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
