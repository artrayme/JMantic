package scmemory;

import org.junit.jupiter.api.Test;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.websocketmemory.memory.element.ScLinkBinaryImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Michael
 * @since 0.6.2
 */
public class ScLInkBinaryImplTest {
    @Test
    void getContentFromEmptyLink() throws IOException {
        String expected = new ByteArrayOutputStream().toString();
        String content = null;
        ScLinkBinaryImpl link = new ScLinkBinaryImpl(
                LinkType.LINK,
                33L);
        link.setContent(content);
        String actual = link.getContent()
                            .toString();

        assertEquals(
                actual,
                expected);
    }
}
