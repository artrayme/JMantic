package scmemory;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.pattern.factory.DefaultScPattern3Factory;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author artrayme
 * @since 0.0.1
 */
public class ScMemoryTest {
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
    public void testConfigurationException() throws Exception {
        //        assertThrows(ScMemoryConfigurationException.class, () -> new SyncScMemory(new OstisClientSync(new URI("ws://localhost:8090"))));
//        ScMemory scMemory = new SyncOstisScMemory();

    }




}
