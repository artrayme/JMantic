package context.asyncunchecked;

import org.jmantic.api.context.AsyncUncheckedScContext;
import org.jmantic.api.context.UncheckedScContext;
import org.jmantic.scmemory.model.element.link.LinkContentType;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author artrayme
 * @since 0.2.0
 */

//ToDO multithreading tests
public class ScLinkOperationsTest {
    private AsyncUncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws URISyntaxException {
        scContext = new AsyncUncheckedScContext(new UncheckedScContext(new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"))));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleIntegerLink() throws ExecutionException, InterruptedException {
        int content = 5;
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content).get();
        assertEquals(LinkType.LINK, link.getType());
        assertEquals(LinkContentType.INTEGER, link.getContentType());
        assertEquals(content, link.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleFloatLink() throws ExecutionException, InterruptedException {
        float content = 42.1f;
        ScLinkFloat link = scContext.createFloatLink(LinkType.LINK, content).get();
        assertEquals(LinkType.LINK, link.getType());
        assertEquals(LinkContentType.FLOAT, link.getContentType());
        assertEquals(content, link.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleStringLink() throws ExecutionException, InterruptedException {
        String content = "Hello";
        ScLinkString link = scContext.createStringLink(LinkType.LINK, content).get();
        assertEquals(LinkType.LINK, link.getType());
        assertEquals(LinkContentType.STRING, link.getContentType());
        assertEquals(content, link.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleLinks() throws ExecutionException, InterruptedException {
        int content1 = 2;
        int content2 = 3;
        ScLinkInteger link1 = scContext.createIntegerLink(LinkType.LINK, content1).get();
        ScLinkInteger link2 = scContext.createIntegerLink(LinkType.LINK, content2).get();
        assertEquals(link1.getType(), link2.getType());
        assertEquals(link1.getContentType(), link2.getContentType());
        assertEquals(content1 + content2, link1.getContent() + link2.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteLink() throws ExecutionException, InterruptedException {
        int content = 5;
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content).get();
        boolean result = scContext.deleteElement(link).get();
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteMultipleLinks() throws ExecutionException, InterruptedException {
        int content1 = 2;
        int content2 = 3;
        ScLinkInteger link1 = scContext.createIntegerLink(LinkType.LINK, content1).get();
        ScLinkInteger link2 = scContext.createIntegerLink(LinkType.LINK, content2).get();
        boolean result = scContext.deleteElements(Stream.of(link1, link2)).get();
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromIntegerLink() throws ExecutionException, InterruptedException {
        int content = 5;
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content).get();
        assertEquals(content, scContext.getIntegerLinkContent(link).get());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromFloatLink() throws ExecutionException, InterruptedException {
        float content = 42.1f;
        ScLinkFloat link = scContext.createFloatLink(LinkType.LINK, content).get();
        assertEquals(content, scContext.getFloatLinkContent(link).get());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromStringLink() throws ExecutionException, InterruptedException {
        String content = "Hello";
        ScLinkString link = scContext.createStringLink(LinkType.LINK, content).get();
        assertEquals(content, scContext.getStringLinkContent(link).get());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToIntegerLink() throws ExecutionException, InterruptedException {
        int oldContent = 5;
        int newContent = 13;
        ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, oldContent).get();
        assertTrue(scContext.setIntegerLinkContent(link, newContent).get());
        assertEquals(newContent, link.getContent());
        assertEquals(newContent, scContext.getIntegerLinkContent(link).get());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToFloatLink() throws ExecutionException, InterruptedException {
        float oldContent = 42.1f;
        float newContent = 123.456f;
        ScLinkFloat link = scContext.createFloatLink(LinkType.LINK, oldContent).get();
        assertTrue(scContext.setFloatLinkContent(link, newContent).get());
        assertEquals(newContent, link.getContent());
        assertEquals(newContent, scContext.getFloatLinkContent(link).get());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToStringLink() throws ExecutionException, InterruptedException {
        String oldContent = "Hello";
        String newContent = "World";
        ScLinkString link = scContext.createStringLink(LinkType.LINK, oldContent).get();
        assertTrue(scContext.setStringLinkContent(link, newContent).get());
        assertEquals(newContent, link.getContent());
        assertEquals(newContent, scContext.getStringLinkContent(link).get());
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingWithPausesLinks() throws InterruptedException, ExecutionException {
        int count = 100;
        int content = 5;
        for (int i = 0; i < count; i++) {
            ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content).get();
            Thread.sleep(ThreadLocalRandom.current().nextInt(0, 10));
            assertEquals(LinkType.LINK, link.getType());
            assertEquals(LinkContentType.INTEGER, link.getContentType());
            assertEquals(content, link.getContent());
        }
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingLinks() throws ExecutionException, InterruptedException {
        int count = 100;
        int content = 5;
        for (int i = 0; i < count; i++) {
            ScLinkInteger link = scContext.createIntegerLink(LinkType.LINK, content).get();
            assertEquals(LinkType.LINK, link.getType());
            assertEquals(LinkContentType.INTEGER, link.getContentType());
            assertEquals(content, link.getContent());
        }
    }
}
