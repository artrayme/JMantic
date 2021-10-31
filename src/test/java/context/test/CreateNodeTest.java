package context.test;

import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.websocketmemory.sync.SyncScMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author artrayme
 * 10/24/21
 */

public class CreateNodeTest {
    private DefaultScContext scContext;

    @BeforeEach
    public void setUp() throws URISyntaxException {
        scContext = new DefaultScContext(new SyncScMemory(new URI("ws://localhost:8090/ws_json")));
    }

    //    ToDo test timeout
    @Test
    void createNode() {
        ScNode node = scContext.createNode(NodeType.NODE);
        assertEquals(NodeType.NODE, node.getType());
    }

    @Test
    void createNodes() {
        long size = 10;
        var types = Stream.iterate(NodeType.NODE, e -> NodeType.NODE).limit(size);
        Stream<ScNode> nodes = scContext.createNodes(types);
        nodes.forEach(e -> {
            assertEquals(e.getType(), NodeType.NODE);
        });
    }

    @Test
    void createAllNodesTypes() {
        Stream<ScNode> nodes = scContext.createNodes(Arrays.stream(NodeType.values()));
        Iterator<ScNode> iter1 = nodes.iterator();
        Iterator<NodeType> iter2 = Arrays.stream(NodeType.values()).iterator();
        while (iter1.hasNext() && iter2.hasNext())
            assertEquals(iter1.next().getType(), iter2.next());
        assertEquals(iter1.hasNext(), iter2.hasNext());
    }

}
