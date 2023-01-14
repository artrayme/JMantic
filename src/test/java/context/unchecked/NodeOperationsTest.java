package context.unchecked;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.api.context.UncheckedScContext;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;

import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author artrayme
 * @since 0.0.1
 */
public class NodeOperationsTest {
    ScMemory memory;

    private UncheckedScContext scContext;

    @BeforeEach
    public void setUp() throws Exception {
        memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scContext = new UncheckedScContext(memory);
        memory.open();
    }

    @AfterEach
    public void closeScMemory() throws Exception {
        memory.close();
    }


    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleNode() {
        ScNode node = scContext.createNode(NodeType.NODE);
        assertEquals(
                NodeType.NODE,
                node.getType());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleNodesWithOneType() {
        long size = 10;
        var types = Stream.iterate(
                                  NodeType.NODE,
                                  e -> NodeType.NODE)
                          .limit(size);
        var nodes = scContext.createNodes(types);
        nodes.forEach(e -> {
            assertEquals(
                    e.getType(),
                    NodeType.NODE);
        });
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createNodesWithAllAvailableTypes() {
        var nodes = scContext.createNodes(Arrays.stream(NodeType.values()));
        var iter1 = nodes.iterator();
        Iterator<NodeType> iter2 = Arrays.stream(NodeType.values())
                                         .iterator();
        while (iter1.hasNext() && iter2.hasNext()) assertEquals(
                iter1.next()
                     .getType(),
                iter2.next());
        assertEquals(
                iter1.hasNext(),
                iter2.hasNext());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createTwoNodesOneByOne() {
        ScNode firstNode = scContext.createNode(NodeType.NODE);
        ScNode secondNode = scContext.createNode(NodeType.ABSTRACT);
        assertEquals(
                NodeType.NODE,
                firstNode.getType());
        assertEquals(
                NodeType.ABSTRACT,
                secondNode.getType());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteNode() {
        ScNode node = scContext.createNode(NodeType.NODE);
        boolean result = scContext.deleteElement(node);
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteMultipleNodesWithOneType() {
        long size = 10;
        var types = Stream.iterate(
                                  NodeType.NODE,
                                  e -> NodeType.NODE)
                          .limit(size);
        var nodes = scContext.createNodes(types);
        boolean result = scContext.deleteElements(nodes);
        assertTrue(result);
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingWithPausesNodes() throws InterruptedException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode node = scContext.createNode(NodeType.NODE);
            Thread.sleep(ThreadLocalRandom.current()
                                          .nextInt(
                                                  0,
                                                  10));
            assertEquals(
                    NodeType.NODE,
                    node.getType());
        }
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingNodes() {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode node = scContext.createNode(NodeType.NODE);
            assertEquals(
                    NodeType.NODE,
                    node.getType());
        }
    }

}
