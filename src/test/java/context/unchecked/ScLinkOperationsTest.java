package context.unchecked;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.api.context.UncheckedScContext;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.link.LinkContentType;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLinkBinary;
import org.ostis.scmemory.model.element.link.ScLinkFloat;
import org.ostis.scmemory.model.element.link.ScLinkInteger;
import org.ostis.scmemory.model.element.link.ScLinkString;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author artrayme
 * @since 0.0.1
 */
public class ScLinkOperationsTest {
    ScMemory memory;

    private UncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws Exception {
        memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scContext = new UncheckedScContext(memory);
        memory.open();
    }

    @AfterEach
    public void closeScMemory() throws Exception {
        memory.close();
    }


    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleIntegerLink() {
        int content = 5;
        ScLinkInteger link = scContext.createIntegerLink(
                LinkType.LINK,
                content);
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
    void createSingleFloatLink() {
        float content = 42.1f;
        ScLinkFloat link = scContext.createFloatLink(
                LinkType.LINK,
                content);
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
    void createSingleStringLink() {
        String content = "Hello";
        ScLinkString link = scContext.createStringLink(
                LinkType.LINK,
                content);
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
    void createSingleBinaryLink() throws IOException {
        String str = "Hello unchecked context";
        ByteArrayOutputStream content = new ByteArrayOutputStream();
        content.write(str.getBytes());

        ScLinkBinary link = scContext.createBinaryLink(
                LinkType.LINK,
                content);

        assertEquals(
                LinkType.LINK,
                link.getType());
        assertEquals(
                LinkContentType.BINARY,
                link.getContentType());
        assertEquals(
                content.toString(),
                link.getContent()
                    .toString());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleLinks() {
        int content1 = 2;
        int content2 = 3;
        ScLinkInteger link1 = scContext.createIntegerLink(
                LinkType.LINK,
                content1);
        ScLinkInteger link2 = scContext.createIntegerLink(
                LinkType.LINK,
                content2);
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
    void deleteLink() {
        int content = 5;
        ScLinkInteger link = scContext.createIntegerLink(
                LinkType.LINK,
                content);
        boolean result = scContext.deleteElement(link);
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteMultipleLinks() {
        int content1 = 2;
        int content2 = 3;
        ScLinkInteger link1 = scContext.createIntegerLink(
                LinkType.LINK,
                content1);
        ScLinkInteger link2 = scContext.createIntegerLink(
                LinkType.LINK,
                content2);
        boolean result = scContext.deleteElements(Stream.of(
                link1,
                link2));
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromIntegerLink() {
        int content = 5;
        ScLinkInteger link = scContext.createIntegerLink(
                LinkType.LINK,
                content);
        assertEquals(
                content,
                scContext.getIntegerLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromFloatLink() {
        float content = 42.1f;
        ScLinkFloat link = scContext.createFloatLink(
                LinkType.LINK,
                content);
        assertEquals(
                content,
                scContext.getFloatLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromStringLink() {
        String content = "Hello";
        ScLinkString link = scContext.createStringLink(
                LinkType.LINK,
                content);
        assertEquals(
                content,
                scContext.getStringLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void getContentFromBinaryLink() throws IOException {
        String str = "Hello from getContent method!";
        ByteArrayOutputStream expected = new ByteArrayOutputStream();
        expected.write(str.getBytes());
        ScLinkBinary linkBinary = scContext.createBinaryLink(
                LinkType.LINK,
                expected);

        ByteArrayOutputStream actual = scContext.getBinaryLinkContent(linkBinary);

        assertEquals(
                actual.toString(),
                expected.toString());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToIntegerLink() {
        int oldContent = 5;
        int newContent = 13;
        ScLinkInteger link = scContext.createIntegerLink(
                LinkType.LINK,
                oldContent);
        assertTrue(scContext.setIntegerLinkContent(
                link,
                newContent));
        assertEquals(
                newContent,
                link.getContent());
        assertEquals(
                newContent,
                scContext.getIntegerLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToFloatLink() {
        float oldContent = 42.1f;
        float newContent = 123.456f;
        ScLinkFloat link = scContext.createFloatLink(
                LinkType.LINK,
                oldContent);
        assertTrue(scContext.setFloatLinkContent(
                link,
                newContent));
        assertEquals(
                newContent,
                link.getContent());
        assertEquals(
                newContent,
                scContext.getFloatLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToStringLink() {
        String oldContent = "Hello";
        String newContent = "World";
        ScLinkString link = scContext.createStringLink(
                LinkType.LINK,
                oldContent);
        assertTrue(scContext.setStringLinkContent(
                link,
                newContent));
        assertEquals(
                newContent,
                link.getContent());
        assertEquals(
                newContent,
                scContext.getStringLinkContent(link));
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void setContentToBinaryString() throws IOException {
        String oldStr = "Hello";
        String newStr = "Bye";
        ByteArrayOutputStream oldContent = new ByteArrayOutputStream();
        oldContent.write(oldStr.getBytes());
        ByteArrayOutputStream newContent = new ByteArrayOutputStream();
        newContent.write(newStr.getBytes());
        ScLinkBinary linkBinary = scContext.createBinaryLink(
                LinkType.LINK,
                oldContent);

        Boolean resultOfSetContentOperation = scContext.setBinaryLinkContent(
                linkBinary,
                newContent);

        ByteArrayOutputStream fromOstis = scContext.getBinaryLinkContent(linkBinary);

        assertTrue(resultOfSetContentOperation);
        assertEquals(
                newContent.toString(),
                linkBinary.getContent()
                          .toString());
        assertEquals(
                newContent.toString(),
                fromOstis.toString());
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingWithPausesLinks() throws InterruptedException {
        int count = 100;
        int content = 5;
        for (int i = 0; i < count; i++) {
            ScLinkInteger link = scContext.createIntegerLink(
                    LinkType.LINK,
                    content);
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
    void benchmarkingLinks() {
        int count = 100;
        int content = 5;
        for (int i = 0; i < count; i++) {
            ScLinkInteger link = scContext.createIntegerLink(
                    LinkType.LINK,
                    content);
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
}
