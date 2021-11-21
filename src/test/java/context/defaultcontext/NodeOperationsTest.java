package context.defaultcontext;

import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author artrayme
 * 11/21/21
 */

//ToDO exceptions test
public class NodeOperationsTest {
    private DefaultScContext scContext;

    @BeforeEach
    void setUp() throws URISyntaxException {
        scContext = new DefaultScContext(new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json")));

    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleNode() throws ScMemoryException {
        ScNode node = scContext.createNode(NodeType.NODE);
        assertEquals(NodeType.NODE, node.getType());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleNodesWithOneType() throws ScMemoryException {
        long size = 10;
        var types = Stream.iterate(NodeType.NODE, e -> NodeType.NODE).limit(size);
        Stream<? extends ScNode> nodes = scContext.createNodes(types);
        nodes.forEach(e -> {
            assertEquals(e.getType(), NodeType.NODE);
        });
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createNodesWithAllAvailableTypes() throws ScMemoryException {
        Stream<? extends ScNode> nodes = scContext.createNodes(Arrays.stream(NodeType.values()));
        Iterator<? extends ScNode> iter1 = nodes.iterator();
        Iterator<NodeType> iter2 = Arrays.stream(NodeType.values()).iterator();
        while (iter1.hasNext() && iter2.hasNext())
            assertEquals(iter1.next().getType(), iter2.next());
        assertEquals(iter1.hasNext(), iter2.hasNext());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createTwoNodesOneByOne() throws ScMemoryException {
        ScNode firstNode = scContext.createNode(NodeType.NODE);
        ScNode secondNode = scContext.createNode(NodeType.ABSTRACT);
        assertEquals(NodeType.NODE, firstNode.getType());
        assertEquals(NodeType.ABSTRACT, secondNode.getType());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteNode() throws ScMemoryException {
        ScNode node = scContext.createNode(NodeType.NODE);
        boolean result = scContext.deleteElement(node);
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteMultipleNodesWithOneType() throws ScMemoryException {
        long size = 10;
        var types = Stream.iterate(NodeType.NODE, e -> NodeType.NODE).limit(size);
        Stream<? extends ScNode> nodes = scContext.createNodes(types);
        boolean result = scContext.deleteElements(nodes);
        assertTrue(result);
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingWithPausesNodes() throws InterruptedException, ScMemoryException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode node = scContext.createNode(NodeType.NODE);
            Thread.sleep(ThreadLocalRandom.current().nextInt(0, 10));
            assertEquals(NodeType.NODE, node.getType());
        }
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingNodes() throws ScMemoryException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode node = scContext.createNode(NodeType.NODE);
            assertEquals(NodeType.NODE, node.getType());
        }
    }

}
