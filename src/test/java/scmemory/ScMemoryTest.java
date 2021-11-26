package scmemory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author artrayme
 * @since 0.0.1
 */
public class ScMemoryTest {

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void testConfigurationException() {
//        assertThrows(ScMemoryConfigurationException.class, () -> new SyncScMemory(new OstisClientSync(new URI("ws://localhost:8090"))));

    }


}
