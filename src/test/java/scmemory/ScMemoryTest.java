package scmemory;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.model.pattern.ScConstruction3;
import org.jmantic.scmemory.model.pattern.ScPattern3;
import org.jmantic.scmemory.model.pattern.pattern3.ScPattern3Factory;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.0.1
 */
public class ScMemoryTest {

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void testConfigurationException() throws Exception {
        //        assertThrows(ScMemoryConfigurationException.class, () -> new SyncScMemory(new OstisClientSync(new URI("ws://localhost:8090"))));
        ScMemory scMemory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));

    }

//    @Test
//    public void smallTest(){
//        long startTime = System.nanoTime();
//
//        long stopTime = System.nanoTime();
//        System.out.println(stopTime - startTime);
//    }
//
//    @Test
//    public void patternFindingTest() throws URISyntaxException, ScMemoryException {
//        ScMemory scMemory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
//        ScNode node1 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
//        var x = scMemory.findByPattern3(ScPattern3Factory.getFNodeEdgeNodePattern(node1, EdgeType.ACCESS, NodeType.NODE));
//        x.forEach(e->{
//            e.get1();
//        });
//    }


}
