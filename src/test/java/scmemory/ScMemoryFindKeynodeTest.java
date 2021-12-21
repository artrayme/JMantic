package scmemory;

import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScMemoryFindKeynodeTest {
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
        var first = scMemory.findKeynodes(Stream.of("identifier*")).findFirst().get();
        var second = scMemory.findKeynodes(Stream.of("identifier*")).findFirst().get();
        assertEquals(first, second);
    }

    @Test
    public void findMultipleKeynode() throws ScMemoryException {
        var first = scMemory.findKeynodes(Stream.of("identifier*", "identifier*")).toList();
        var second = scMemory.findKeynodes(Stream.of("identifier*", "identifier*")).toList();
        assertEquals(first, second);
    }
}
