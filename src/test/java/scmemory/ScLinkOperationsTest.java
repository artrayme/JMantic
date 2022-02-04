package scmemory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.link.LinkContentType;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLinkFloat;
import org.ostis.scmemory.model.element.link.ScLinkInteger;
import org.ostis.scmemory.model.element.link.ScLinkString;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;

import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author artrayme
 * @since 0.6.0
 */

//ToDO exceptions test
public class ScLinkOperationsTest {
    private ScMemory scMemory;

    @BeforeEach
    public void init() throws Exception {
        scMemory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scMemory.open();
    }

    @AfterEach
    public void shutdown() throws Exception {
        scMemory.close();
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleIntegerLink() throws ScMemoryException {
        int content = 5;
        ScLinkInteger link = scMemory.createIntegerLinks(
                                             Stream.of(LinkType.LINK),
                                             Stream.of(content))
                                     .findFirst()
                                     .get();
        assertEquals(
                LinkType.LINK,
                link.getType());
        assertEquals(
                LinkContentType.INT,
                link.getContentType());
        assertEquals(
                content,
                link.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleFloatLink() throws ScMemoryException {
        float content = 42.1f;
        ScLinkFloat link = scMemory.createFloatLinks(
                                           Stream.of(LinkType.LINK),
                                           Stream.of(content))
                                   .findFirst()
                                   .get();
        assertEquals(
                LinkType.LINK,
                link.getType());
        assertEquals(
                LinkContentType.FLOAT,
                link.getContentType());
        assertEquals(
                content,
                link.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleStringLink() throws ScMemoryException {
        String content = "Hello";
        ScLinkString link = scMemory.createStringLinks(
                                            Stream.of(LinkType.LINK),
                                            Stream.of(content))
                                    .findFirst()
                                    .get();
        assertEquals(
                LinkType.LINK,
                link.getType());
        assertEquals(
                LinkContentType.STRING,
                link.getContentType());
        assertEquals(
                content,
                link.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleLinks() throws ScMemoryException {
        int content1 = 2;
        int content2 = 3;
        ScLinkInteger link1 = scMemory.createIntegerLinks(
                                              Stream.of(LinkType.LINK),
                                              Stream.of(content1))
                                      .findFirst()
                                      .get();
        ScLinkInteger link2 = scMemory.createIntegerLinks(
                                              Stream.of(LinkType.LINK),
                                              Stream.of(content2))
                                      .findFirst()
                                      .get();
        assertEquals(
                link1.getType(),
                link2.getType());
        assertEquals(
                link1.getContentType(),
                link2.getContentType());
        assertEquals(
                content1 + content2,
                link1.getContent() + link2.getContent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteLink() throws ScMemoryException {
        int content = 5;
        ScLinkInteger link = scMemory.createIntegerLinks(
                                             Stream.of(LinkType.LINK),
                                             Stream.of(content))
                                     .findFirst()
                                     .get();
        boolean result = scMemory.deleteElements(Stream.of(link));
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteMultipleLinks() throws ScMemoryException {
        int content1 = 2;
        int content2 = 3;
        ScLinkInteger link1 = scMemory.createIntegerLinks(
                                              Stream.of(LinkType.LINK),
                                              Stream.of(content1))
                                      .findFirst()
                                      .get();
        ScLinkInteger link2 = scMemory.createIntegerLinks(
                                              Stream.of(LinkType.LINK),
                                              Stream.of(content2))
                                      .findFirst()
                                      .get();
        boolean result = scMemory.deleteElements(Stream.of(
                link1,
                link2));
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromIntegerLink() throws ScMemoryException {
        int content = 5;
        ScLinkInteger link = scMemory.createIntegerLinks(
                                             Stream.of(LinkType.LINK),
                                             Stream.of(content))
                                     .findFirst()
                                     .get();
        assertEquals(
                content,
                scMemory.getIntegerLinkContent(Stream.of(link))
                        .findFirst()
                        .get());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromFloatLink() throws ScMemoryException {
        float content = 42.1f;
        ScLinkFloat link = scMemory.createFloatLinks(
                                           Stream.of(LinkType.LINK),
                                           Stream.of(content))
                                   .findFirst()
                                   .get();
        assertEquals(
                content,
                scMemory.getFloatLinkContent(Stream.of(link))
                        .findFirst()
                        .get());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromStringLink() throws ScMemoryException {
        String content = "JManric_test_syncScMemory";
        ScLinkString link = scMemory.createStringLinks(
                                            Stream.of(LinkType.LINK),
                                            Stream.of(content))
                                    .findFirst()
                                    .get();
        assertEquals(
                content,
                scMemory.getStringLinkContent(Stream.of(link))
                        .findFirst()
                        .get());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToIntegerLink() throws ScMemoryException {
        int oldContent = 5;
        int newContent = 13;
        ScLinkInteger link = scMemory.createIntegerLinks(
                                             Stream.of(LinkType.LINK),
                                             Stream.of(oldContent))
                                     .findFirst()
                                     .get();
        assertTrue(scMemory.setIntegerLinkContent(
                                   Stream.of(link),
                                   Stream.of(newContent))
                           .findFirst()
                           .get());
        assertEquals(
                newContent,
                link.getContent());
        assertEquals(
                newContent,
                scMemory.getIntegerLinkContent(Stream.of(link))
                        .findFirst()
                        .get());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToFloatLink() throws ScMemoryException {
        float oldContent = 42.1f;
        float newContent = 123.456f;
        ScLinkFloat link = scMemory.createFloatLinks(
                                           Stream.of(LinkType.LINK),
                                           Stream.of(oldContent))
                                   .findFirst()
                                   .get();
        assertTrue(scMemory.setFloatLinkContent(
                                   Stream.of(link),
                                   Stream.of(newContent))
                           .findFirst()
                           .get());
        assertEquals(
                newContent,
                link.getContent());
        assertEquals(
                newContent,
                scMemory.getFloatLinkContent(Stream.of(link))
                        .findFirst()
                        .get());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToStringLink() throws ScMemoryException {
        String oldContent = "Hello";
        String newContent = "World";
        ScLinkString link = scMemory.createStringLinks(
                                            Stream.of(LinkType.LINK),
                                            Stream.of(oldContent))
                                    .findFirst()
                                    .get();
        assertTrue(scMemory.setStringLinkContent(
                                   Stream.of(link),
                                   Stream.of(newContent))
                           .findFirst()
                           .get());
        assertEquals(
                newContent,
                link.getContent());
        assertEquals(
                newContent,
                scMemory.getStringLinkContent(Stream.of(link))
                        .findFirst()
                        .get());
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingWithPausesLinks() throws InterruptedException, ScMemoryException {
        int count = 100;
        int content = 5;
        for (int i = 0; i < count; i++) {
            ScLinkInteger link = scMemory.createIntegerLinks(
                                                 Stream.of(LinkType.LINK),
                                                 Stream.of(content))
                                         .findFirst()
                                         .get();
            Thread.sleep(ThreadLocalRandom.current()
                                          .nextInt(
                                                  0,
                                                  10));
            assertEquals(
                    LinkType.LINK,
                    link.getType());
            assertEquals(
                    LinkContentType.INT,
                    link.getContentType());
            assertEquals(
                    content,
                    link.getContent());
        }
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingLinks() throws ScMemoryException {
        int count = 100;
        int content = 5;
        for (int i = 0; i < count; i++) {
            ScLinkInteger link = scMemory.createIntegerLinks(
                                                 Stream.of(LinkType.LINK),
                                                 Stream.of(content))
                                         .findFirst()
                                         .get();
            assertEquals(
                    LinkType.LINK,
                    link.getType());
            assertEquals(
                    LinkContentType.INT,
                    link.getContentType());
            assertEquals(
                    content,
                    link.getContent());
        }
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void exceptionAtLinkCreating() {
        Integer content = 123;
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    scMemory.createIntegerLinks(
                            Stream.of(LinkType.LINK),
                            Stream.of());
                });
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    scMemory.createIntegerLinks(
                            Stream.of(),
                            Stream.of(content));
                });
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void exceptionAtLinkContentSetting() throws ScMemoryException {
        Integer content = 123;
        ScLinkInteger link = scMemory.createIntegerLinks(
                                             Stream.of(LinkType.LINK),
                                             Stream.of(content))
                                     .findFirst()
                                     .get();
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    scMemory.setIntegerLinkContent(
                            Stream.of(),
                            Stream.of(content));
                });
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    scMemory.setIntegerLinkContent(
                            Stream.of(link),
                            Stream.of());
                });
    }

}