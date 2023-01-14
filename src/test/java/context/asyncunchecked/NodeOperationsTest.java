package context.asyncunchecked;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.api.context.AsyncUncheckedScContext;
import org.ostis.api.context.UncheckedScContext;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;

import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author artrayme
 * @since 0.2.0
 */

//ToDO multithreading tests
public class NodeOperationsTest {
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
    void createSingleNode() throws ExecutionException, InterruptedException {
        ScNode node = scContext.createNode(NodeType.NODE)
                               .get();
        assertEquals(
                NodeType.NODE,
                node.getType());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleNodesWithOneType() throws ExecutionException, InterruptedException {
        long size = 10;
        var types = Stream.iterate(
                                  NodeType.NODE,
                                  e -> NodeType.NODE)
                          .limit(size);
        var nodes = scContext.createNodes(types)
                                        .get();
        nodes.forEach(e -> {
            assertEquals(
                    e.getType(),
                    NodeType.NODE);
        });
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createNodesWithAllAvailableTypes() throws ExecutionException, InterruptedException {
        var  nodes = scContext.createNodes(Arrays.stream(NodeType.values()))
                                        .get();
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
    void createTwoNodesOneByOne() throws ExecutionException, InterruptedException {
        ScNode firstNode = scContext.createNode(NodeType.NODE)
                                    .get();
        ScNode secondNode = scContext.createNode(NodeType.ABSTRACT)
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
    void deleteNode() throws ExecutionException, InterruptedException {
        ScNode node = scContext.createNode(NodeType.NODE)
                               .get();
        boolean result = scContext.deleteElement(node)
                                  .get();
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteMultipleNodesWithOneType() throws ExecutionException, InterruptedException {
        long size = 10;
        var types = Stream.iterate(
                                  NodeType.NODE,
                                  e -> NodeType.NODE)
                          .limit(size);
        var nodes = scContext.createNodes(types)
                                        .get();
        boolean result = scContext.deleteElements(nodes)
                                  .get();
        assertTrue(result);
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingWithPausesNodes() throws InterruptedException, ExecutionException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode node = scContext.createNode(NodeType.NODE)
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
    void benchmarkingNodes() throws ExecutionException, InterruptedException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode node = scContext.createNode(NodeType.NODE)
                                   .get();
            assertEquals(
                    NodeType.NODE,
                    node.getType());
        }
    }

}
