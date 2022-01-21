package context.asyncunchecked;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.api.context.AsyncUncheckedScContext;
import org.ostis.api.context.UncheckedScContext;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class KeynodesTest {
    ScMemory memory;

    private AsyncUncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws Exception {
        memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scContext = new AsyncUncheckedScContext(new UncheckedScContext(memory));
        memory.open();
    }

    @AfterEach
    public void closeScMemory() throws Exception {
        memory.close();
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void findExistedKeynodes() throws ExecutionException, InterruptedException {
        var first = scContext.findKeynode("nrel_main_idtf")
                             .get()
                             .get();
        var second = scContext.findKeynode("nrel_main_idtf")
                              .get()
                              .get();
        assertEquals(
                first,
                second);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void findNotExistedKeynodes() throws ExecutionException, InterruptedException {
        var keynode = scContext.findKeynode("ThIs_idTf_ca_nT_exists_123qwrt")
                               .get();
        assertFalse(keynode.isPresent());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void resolveKeynode() throws ExecutionException, InterruptedException {
        var keynode1 = scContext.resolveKeynode(
                                        "some_keynode",
                                        NodeType.NODE)
                                .get();
        var keynode2 = scContext.resolveKeynode(
                                        "some_keynode",
                                        NodeType.NODE)
                                .get();
        assertEquals(
                keynode1,
                keynode2);
    }

}
