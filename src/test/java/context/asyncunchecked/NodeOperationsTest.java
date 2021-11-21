package context.asyncunchecked;

import org.jmantic.api.context.AsyncUncheckedScContext;
import org.jmantic.api.context.UncheckedScContext;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.net.URISyntaxException;
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
    private AsyncUncheckedScContext scContext;

    @BeforeEach
    void setUp() throws URISyntaxException {
        scContext = new AsyncUncheckedScContext(new UncheckedScContext(new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"))));

    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createSingleNode() throws ExecutionException, InterruptedException {
        ScNode node = scContext.createNode(NodeType.NODE).get();
        assertEquals(NodeType.NODE, node.getType());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createMultipleNodesWithOneType() throws ExecutionException, InterruptedException {
        long size = 10;
        var types = Stream.iterate(NodeType.NODE, e -> NodeType.NODE).limit(size);
        Stream<ScNode> nodes = scContext.createNodes(types).get();
        nodes.forEach(e -> {
            assertEquals(e.getType(), NodeType.NODE);
        });
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createNodesWithAllAvailableTypes() throws ExecutionException, InterruptedException {
        Stream<ScNode> nodes = scContext.createNodes(Arrays.stream(NodeType.values())).get();
        Iterator<ScNode> iter1 = nodes.iterator();
        Iterator<NodeType> iter2 = Arrays.stream(NodeType.values()).iterator();
        while (iter1.hasNext() && iter2.hasNext())
            assertEquals(iter1.next().getType(), iter2.next());
        assertEquals(iter1.hasNext(), iter2.hasNext());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void createTwoNodesOneByOne() throws ExecutionException, InterruptedException {
        ScNode firstNode = scContext.createNode(NodeType.NODE).get();
        ScNode secondNode = scContext.createNode(NodeType.ABSTRACT).get();
        assertEquals(NodeType.NODE, firstNode.getType());
        assertEquals(NodeType.ABSTRACT, secondNode.getType());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteNode() throws ExecutionException, InterruptedException {
        ScNode node = scContext.createNode(NodeType.NODE).get();
        boolean result = scContext.deleteElement(node).get();
        assertTrue(result);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void deleteMultipleNodesWithOneType() throws ExecutionException, InterruptedException {
        long size = 10;
        var types = Stream.iterate(NodeType.NODE, e -> NodeType.NODE).limit(size);
        Stream<ScNode> nodes = scContext.createNodes(types).get();
        boolean result = scContext.deleteElements(nodes).get();
        assertTrue(result);
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingWithPausesNodes() throws InterruptedException, ExecutionException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode node = scContext.createNode(NodeType.NODE).get();
            Thread.sleep(ThreadLocalRandom.current().nextInt(0, 10));
            assertEquals(NodeType.NODE, node.getType());
        }
    }

    @Test
    @Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
    void benchmarkingNodes() throws ExecutionException, InterruptedException {
        int count = 100;
        for (int i = 0; i < count; i++) {
            ScNode node = scContext.createNode(NodeType.NODE).get();
            assertEquals(NodeType.NODE, node.getType());
        }
    }

}
