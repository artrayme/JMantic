package context.defaultcontext;

import org.ostis.api.context.DefaultScContext;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeynodesTest {
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
    public void findKeynode() throws ScMemoryException {
        var first = scContext.findKeynode("identifier*").get();
        var second = scContext.findKeynode("identifier*").get();
        assertEquals(first, second);
    }
}
