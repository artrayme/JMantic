package context;

import org.jmantic.scmemory.model.exception.ScMemoryConfigurationException;
import org.jmantic.scmemory.websocketmemory.sync.SyncScMemory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author artrayme
 * 11/4/21
 */
public class ScMemoryTest {

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void testConfigurationException() {
        assertThrows(ScMemoryConfigurationException.class, () -> SyncScMemory.getSyncScMemory(" "));
    }
}
