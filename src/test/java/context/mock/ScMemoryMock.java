package context.mock;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.event.ScEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author artrayme
 * 10/24/21
 */
public class ScMemoryMock implements ScMemory {
    private final List<ScNode> nodes = new ArrayList<>();
    private final List<ScEdge> edges = new ArrayList<>();
    private final List<ScLink> links = new ArrayList<>();
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final Logger logger = LoggerFactory.getLogger(ScMemoryMock.class);

    @Override
    public Stream<? extends ScElement> createNodes(Stream<NodeType> elements) {
        logger.info("START CREATE NODES");
        logger.info(elements.toString());
        int initSize = nodes.size();
        elements.forEach(type -> nodes.add(new MockScNode(random.nextLong(), type)));
        Stream<ScNode> result = nodes.stream().skip(initSize);
        logger.info(result.toString());
        logger.info("END CREATE NODES");
        return result;
    }

    @Override
    public Stream<? extends ScElement> createEdges(Stream<EdgeType> elements,
                                                   Stream<ScElement> firstComponents,
                                                   Stream<ScElement> secondComponents) {
        List<ScElement> first = firstComponents.toList();
        List<ScElement> second = secondComponents.toList();
        List<EdgeType> elem = elements.toList();
        logger.info("START CREATE EDGES");
        logger.info(elements.toString());
        int initSize = edges.size();
        for (int i = 0; i < elem.size(); i++) {
            edges.add(new MockScEdge(random.nextLong(), elem.get(i), first.get(i), second.get(i)));
        }
        Stream<ScEdge> result = edges.stream().skip(initSize);
        logger.info(result.toString());
        logger.info("END CREATE EDGES");
        return result;
    }

    @Override
    public Stream<? extends ScElement> createIntegerLink(Stream<LinkType> elements, Stream<Integer> content) {
        logger.info("START CREATE LINK INTEGER");
        List<Integer> elem = content.toList();
        List<LinkType> types = elements.toList();
        logger.info(elements.toString());
        int initSize = links.size();
        for (int i = 0; i < types.size(); i++) {
        links.add(new MockScLinkInteger(random.nextLong(), types.get(i), elem.get(i)));
        }
        Stream<ScLink> result = links.stream().skip(initSize);
        logger.info(result.toString());
        logger.info("END CREATE LINK INTEGER");
        return result;
    }

    @Override
    public Stream<? extends ScElement> createFloatLink(Stream<LinkType> elements, Stream<Float> content) {
        return null;
    }

    @Override
    public Stream<? extends ScElement> createStringLink(Stream<LinkType> elements, Stream<String> content) {
        return null;
    }

    @Override
    public Stream<? extends ScElement> createBinaryLink(Stream<LinkType> elements, Stream<Object> content) {
        return null;
    }


    @Override
    public Stream<ScElement> checkElements(Stream<ScElement> elements) {
        return null;
    }

    @Override
    public boolean deleteNode(Stream<ScElement> elements) {
        return false;
    }

    @Override
    public boolean deleteEdge(Stream<ScEdge> elements) {
        return false;
    }

    @Override
    public boolean deleteLink(Stream<ScLink> elements) {
        return false;
    }

    @Override
    public void addEventListener(ScEvent event, Function<ScElement, ScElement> predicate) {

    }

    @Override
    public void removeEventListener(ScEvent event) {

    }

    @Override
    public ScElement findKeynode(String identifier) {
        return null;
    }

    @Override
    public ScElement resolvedKeynode(String identifier, NodeType type) {
        return null;
    }

    @Override
    public Stream<ScLink> getContent(Stream<ScLink> elements) {
        return null;
    }

    @Override
    public Stream<Boolean> setIntegerContent(Stream<ScLinkInteger> links, Stream<Integer> content) {
        return null;
    }

    @Override
    public Stream<Boolean> setFloatContent(Stream<ScLinkFloat> links, Stream<Float> content) {
        return null;
    }

    @Override
    public Stream<Boolean> setStringContent(Stream<ScLinkString> links, Stream<String> content) {
        return null;
    }
}
