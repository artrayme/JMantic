package context.defaultcontext;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.api.context.DefaultScContext;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void findExistedKeynodes() throws ScMemoryException {
        var first = scContext.findKeynode("nrel_main_idtf")
                             .get();
        var second = scContext.findKeynode("nrel_main_idtf")
                              .get();
        assertEquals(
                first,
                second);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void findNotExistedKeynodes() throws ScMemoryException {
        var keynode = scContext.findKeynode("ThIs_idTf_ca_nT_exists_123qwrt");
        assertFalse(keynode.isPresent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void resolveKeynode() throws ScMemoryException {
        var keynode1 = scContext.resolveKeynode(
                "some_keynode",
                NodeType.NODE);
        var keynode2 = scContext.resolveKeynode(
                "some_keynode",
                NodeType.NODE);
        assertEquals(
                keynode1,
                keynode2);
    }
}
