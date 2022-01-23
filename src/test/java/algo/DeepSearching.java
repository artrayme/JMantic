package algo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern3Factory;
import org.ostis.scmemory.model.pattern.pattern3.ScConstruction3;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;
import util.GraphGenerator;
import util.TimeExtension;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@ExtendWith(TimeExtension.class)
public class DeepSearching {
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
    @Timeout(value = 50000, unit = TimeUnit.MILLISECONDS)
    public void test1BinaryTree() throws IOException, ScMemoryException {
        List<List<Integer>> matrix = matrixReader("test1.txt");
        ScEdge startNode = GraphGenerator.generatorByAdjacencyMatrix(
                                                 matrix,
                                                 scMemory)
                                         .findFirst()
                                         .get();
        deepSearch(
                new HashSet<>(),
                (ScNode) startNode.getSource());
    }

    @Test
    @Timeout(value = 50000, unit = TimeUnit.MILLISECONDS)
    public void test2FullGraph() throws IOException, ScMemoryException {
        List<List<Integer>> matrix = matrixReader("test2.txt");
        ScEdge startNode = GraphGenerator.generatorByAdjacencyMatrix(
                                                 matrix,
                                                 scMemory)
                                         .findFirst()
                                         .get();
        deepSearch(
                new HashSet<>(),
                (ScNode) startNode.getSource());
    }

    @Test
    @Timeout(value = 50000, unit = TimeUnit.MILLISECONDS)
    public void test3RandomGraph() throws IOException, ScMemoryException {
        List<List<Integer>> matrix = matrixReader("test3.txt");
        ScEdge startNode = GraphGenerator.generatorByAdjacencyMatrix(
                                                 matrix,
                                                 scMemory)
                                         .findFirst()
                                         .get();
        deepSearch(
                new HashSet<>(),
                (ScNode) startNode.getSource());
    }

    public List<List<Integer>> matrixReader(String filename) throws IOException {
        var resource = DeepSearching.class.getResource("/algo/deepsearching/" + filename);
        return Files.readAllLines(Path.of(resource.getPath()))
                    .stream()
                    .map(e -> Arrays.stream(e.split(", "))
                                    .map(Integer::parseInt)
                                    .toList())
                    .toList();

    }

    public void deepSearch(Set<ScNode> visited, ScNode startNode) throws ScMemoryException {
        scMemory.findByPattern3(DefaultScPattern3Factory.get(
                        startNode,
                        EdgeType.ACCESS,
                        NodeType.NODE))
                .map(ScConstruction3::get3)
                .filter(e -> !visited.contains(e))
                .forEach(e -> {
                    visited.add(e);
                    try {
                        deepSearch(
                                visited,
                                e);
                    } catch (ScMemoryException ex) {
                        ex.printStackTrace();
                    }
                });
    }


}
