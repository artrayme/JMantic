package util;

import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GraphGenerator {

    public static Stream<? extends ScEdge> generatorByAdjacencyMatrix(List<List<Integer>> matrix,
                                                                      ScMemory memory) throws ScMemoryException {
        if (matrix.isEmpty() || matrix.size() != matrix.get(0)
                                                       .size()) {
            throw new IllegalArgumentException("Passed matrix is not an adjacency matrix");
        }

        List<? extends ScNode> nodes = memory.createNodes(matrix.stream()
                                                                .map(e -> NodeType.NODE))
                                             .toList();
        List<ScNode> edgeSources = new ArrayList<>();
        List<ScNode> edgeTargets = new ArrayList<>();
        List<EdgeType> edgeTypes = new ArrayList<>();

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = i; j < matrix.size(); j++) {
                if (matrix.get(i)
                          .get(j) == 1) {
                    edgeSources.add(nodes.get(i));
                    edgeTargets.add(nodes.get(j));
                    edgeTypes.add(EdgeType.ACCESS);
                }
            }
        }

        return memory.createEdges(
                edgeTypes.stream(),
                edgeSources.stream(),
                edgeTargets.stream());

    }
}
