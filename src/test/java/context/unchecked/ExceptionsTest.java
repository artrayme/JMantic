package context.unchecked;

import org.jmantic.api.context.UncheckedScContext;
import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author artrayme
 * 12/7/21
 */
public class ExceptionsTest {
    ScMemory memory;

    private UncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws Exception {
        memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scContext = new UncheckedScContext(memory);
    }

    @Test
    public void exceptionAtNodeCreating() {
        assertThrows(RuntimeException.class, () -> {
            scContext.createNodes(Stream.of(new NodeType[]{NodeType.ABSTRACT}));
        });
    }

    @Test
    public void exceptionAtNodesCreating() {
        assertThrows(RuntimeException.class, () -> {
            scContext.createNode(NodeType.ABSTRACT);
        });
    }




}
