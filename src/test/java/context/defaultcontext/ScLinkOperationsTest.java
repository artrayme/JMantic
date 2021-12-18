package context.defaultcontext;

import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.link.LinkContentType;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author artrayme
 * @since 0.2.0
 */

//ToDO exceptions test
public class ScLinkOperationsTest {
    ScMemory memory;
    private DefaultScContext scContext;

    @BeforeEach
    public void setUp() throws Exception {
        memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scContext = new DefaultScContext(memory);
        memory.open();
    }

    @AfterEach
    public void closeScMemory() throws Exception {
        memory.close();
    }


    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleIntegerLink() throws ScMemoryException {
        int content = 5;
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content);
        assertEquals(LinkType.LINK, link.getType());
        assertEquals(LinkContentType.INT, link.getContentType());
        assertEquals(content, link.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleFloatLink() throws ScMemoryException {
        float content = 42.1f;
        ScLinkFloat link = scContext.createFloatLink(LinkType.LINK, content);
        assertEquals(LinkType.LINK, link.getType());
        assertEquals(LinkContentType.FLOAT, link.getContentType());
        assertEquals(content, link.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleStringLink() throws ScMemoryException {
        String content = "Hello";
        ScLinkString link = scContext.createStringLink(LinkType.LINK, content);
        assertEquals(LinkType.LINK, link.getType());
        assertEquals(LinkContentType.STRING, link.getContentType());
        assertEquals(content, link.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleLinks() throws ScMemoryException {
        int content1 = 2;
        int content2 = 3;
        ScLinkInteger link1 = scContext.createIntegerLink(LinkType.LINK, content1);
        ScLinkInteger link2 = scContext.createIntegerLink(LinkType.LINK, content2);
        assertEquals(link1.getType(), link2.getType());
        assertEquals(link1.getContentType(), link2.getContentType());
        assertEquals(content1 + content2, link1.getContent() + link2.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteLink() throws ScMemoryException {
        int content = 5;
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content);
        boolean result = scContext.deleteElement(link);
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteMultipleLinks() throws ScMemoryException {
        int content1 = 2;
        int content2 = 3;
        ScLinkInteger link1 = scContext.createIntegerLink(LinkType.LINK, content1);
        ScLinkInteger link2 = scContext.createIntegerLink(LinkType.LINK, content2);
        boolean result = scContext.deleteElements(Stream.of(link1, link2));
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromIntegerLink() throws ScMemoryException {
        int content = 5;
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content);
        assertEquals(content, scContext.getIntegerLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromFloatLink() throws ScMemoryException {
        float content = 42.1f;
        ScLinkFloat link = scContext.createFloatLink(LinkType.LINK, content);
        assertEquals(content, scContext.getFloatLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromStringLink() throws ScMemoryException {
        String content = "Hello";
        ScLinkString link = scContext.createStringLink(LinkType.LINK, content);
        assertEquals(content, scContext.getStringLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToIntegerLink() throws ScMemoryException {
        int oldContent = 5;
        int newContent = 13;
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, oldContent);
        assertTrue(scContext.setIntegerLinkContent(link, newContent));
        assertEquals(newContent, link.getContent());
        assertEquals(newContent, scContext.getIntegerLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToFloatLink() throws ScMemoryException {
        float oldContent = 42.1f;
        float newContent = 123.456f;
        ScLinkFloat link = scContext.createFloatLink(LinkType.LINK, oldContent);
        assertTrue(scContext.setFloatLinkContent(link, newContent));
        assertEquals(newContent, link.getContent());
        assertEquals(newContent, scContext.getFloatLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToStringLink() throws ScMemoryException {
        String oldContent = "Hello";
        String newContent = "World";
        ScLinkString link = scContext.createStringLink(LinkType.LINK, oldContent);
        assertTrue(scContext.setStringLinkContent(link, newContent));
        assertEquals(newContent, link.getContent());
        assertEquals(newContent, scContext.getStringLinkContent(link));
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingWithPausesLinks() throws InterruptedException, ScMemoryException {
        int count = 100;
        int content = 5;
        for (int i = 0; i < count; i++) {
            ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content);
            Thread.sleep(ThreadLocalRandom.current().nextInt(0, 10));
            assertEquals(LinkType.LINK, link.getType());
            assertEquals(LinkContentType.INT, link.getContentType());
            assertEquals(content, link.getContent());
        }
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingLinks() throws ScMemoryException {
        int count = 100;
        int content = 5;
        for (int i = 0; i < count; i++) {
            ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content);
            assertEquals(LinkType.LINK, link.getType());
            assertEquals(LinkContentType.INT, link.getContentType());
            assertEquals(content, link.getContent());
        }
    }
}
