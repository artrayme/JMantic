package scmemory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
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
 * @since 0.6.0
 */

public class NodeOperationsTest {
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
    void createSingleNode() throws ScMemoryException {
        ScNode node = scMemory.createNodes(Stream.of(NodeType.NODE))
                              .findFirst()
                              .get();
        assertEquals(
                NodeType.NODE,
                node.getType());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleNodesWithOneType() throws ScMemoryException {
        long size = 10;
        var types = Stream.iterate(
                                  NodeType.NODE,
                                  e -> NodeType.NODE)
                          .limit(size);
        Stream<? extends ScNode> nodes = scMemory.createNodes(types);
        nodes.forEach(e -> {
            assertEquals(
                    e.getType(),
                    NodeType.NODE);
        });
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createNodesWithAllAvailableTypes() throws ScMemoryException {
        Stream<? extends ScNode> nodes = scMemory.createNodes(Arrays.stream(NodeType.values()));
        Iterator<? extends ScNode> iter1 = nodes.iterator();
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
    void createTwoNodesOneByOne() throws ScMemoryException {
        ScNode firstNode = scMemory.createNodes(Stream.of(NodeType.NODE))
                                   .findFirst()
                                   .get();
        ScNode secondNode = scMemory.createNodes(Stream.of(NodeType.ABSTRACT))
                                    .findFirst()
                                    .get();
        assertEquals(
                NodeType.NODE,
                firstNode.getType());
        assertEquals(
                NodeType.ABSTRACT,
                secondNode.getType());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteNode() throws ScMemoryException {
        ScNode node = scMemory.createNodes(Stream.of(NodeType.NODE))
                              .findFirst()
                              .get();
        boolean result = scMemory.deleteElements(Stream.of(node));
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteMultipleNodesWithOneType() throws ScMemoryException {
        long size = 10;
        var types = Stream.iterate(
                                  NodeType.NODE,
                                  e -> NodeType.NODE)
                          .limit(size);
        Stream<? extends ScNode> nodes = scMemory.createNodes(types);
        boolean result = scMemory.deleteElements(nodes);
        assertTrue(result);
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingWithPausesNodes() throws InterruptedException, ScMemoryException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode node = scMemory.createNodes(Stream.of(NodeType.NODE))
                                  .findFirst()
                                  .get();
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
    void benchmarkingNodes() throws ScMemoryException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode node = scMemory.createNodes(Stream.of(NodeType.NODE))
                                  .findFirst()
                                  .get();
            assertEquals(
                    NodeType.NODE,
                    node.getType());
        }
    }

}
