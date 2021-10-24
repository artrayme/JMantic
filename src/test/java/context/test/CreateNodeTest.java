package context.test;

import context.mock.ScMemoryMock;
import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.junit.jupiter.api.Test;

/**
 * @author artrayme
 * 10/24/21
 */

public class CreateNodeTest {
    DefaultScContext scContext = new DefaultScContext(new ScMemoryMock());

    @Test
    void createSimpleNode() {
        ScNode node = scContext.createNode(NodeType.NODE);
    }
}
