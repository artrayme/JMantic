package scmemory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author artrayme
 * @since 0.6.0
 */
public class ScMemoryKeynodeTest {
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
    public void findKeynode() throws ScMemoryException {
        var first = scMemory.findKeynodes(Stream.of("nrel_main_idtf"))
                            .findFirst()
                            .get()
                            .get();
        var second = scMemory.findKeynodes(Stream.of("nrel_main_idtf"))
                             .findFirst()
                             .get()
                             .get();
        assertEquals(
                first,
                second);
    }

    @Test
    public void findMultipleKeynode() throws ScMemoryException {
        var first = scMemory.findKeynodes(Stream.of(
                                    "nrel_main_idtf",
                                    "nrel_main_idtf"))
                            .map(Optional::get)
                            .toList();
        var second = scMemory.findKeynodes(Stream.of(
                                     "nrel_main_idtf",
                                     "nrel_main_idtf"))
                             .map(Optional::get)
                             .toList();
        assertEquals(
                first,
                second);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void resolveKeynode() throws ScMemoryException {
        var keynode1 = scMemory.resolveKeynodes(
                                       Stream.of("JMantic_test_some_keynode"),
                                       Stream.of(NodeType.NODE))
                               .findFirst()
                               .get();
        var keynode2 = scMemory.resolveKeynodes(
                                       Stream.of("JMantic_test_some_keynode"),
                                       Stream.of(NodeType.NODE))
                               .findFirst()
                               .get();
        assertEquals(
                keynode1,
                keynode2);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void exceptionAtNodeResolving() throws ScMemoryException {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    scMemory.resolveKeynodes(
                            Stream.of("JMantic_test_some_keynode"),
                            Stream.of());
                });
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    scMemory.resolveKeynodes(
                            Stream.of(),
                            Stream.of(NodeType.NODE));
                });
    }
}
