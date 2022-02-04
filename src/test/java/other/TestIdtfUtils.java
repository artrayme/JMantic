package other;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;
import org.ostis.scmemory.websocketmemory.util.api.IdtfUtils;
import util.TimeExtension;

import java.net.URI;

@ExtendWith(TimeExtension.class)
public class TestIdtfUtils {
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
    public void testOptimizedVersion() throws Exception {
        IdtfUtils.getAllIdtfFast((SyncOstisScMemory) scMemory);
    }
}
