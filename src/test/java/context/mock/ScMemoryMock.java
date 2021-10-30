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
        logger.info("START CREATING NODES");
        int initSize = nodes.size();
        elements.forEach(type -> {
            long id = random.nextLong();
            logger.info("creating node with type {} and id {}", type, id);
            nodes.add(new MockScNode(id, type));
        });
        Stream<ScNode> result = nodes.stream().skip(initSize);
        logger.info("END CREATING NODES");
        return result;
    }

    @Override
    public Stream<? extends ScElement> createEdges(Stream<EdgeType> elements,
                                                   Stream<ScElement> firstComponents,
                                                   Stream<ScElement> secondComponents) {
        List<ScElement> first = firstComponents.toList();
        List<ScElement> second = secondComponents.toList();
        List<EdgeType> type = elements.toList();
        logger.info("START CREATING EDGES");
        int initSize = edges.size();
        for (int i = 0; i < type.size(); i++) {
            long id = random.nextLong();
            logger.info("creating edge with type {}, first element {}, second element {} and id {}", type.get(i), first.get(i), second.get(i), id);
            edges.add(new MockScEdge(id, type.get(i), first.get(i), second.get(i)));
        }
        Stream<ScEdge> result = edges.stream().skip(initSize);
        logger.info("END CREATING EDGES");
        return result;
    }

    @Override
    public Stream<? extends ScElement> createIntegerLink(Stream<LinkType> elements, Stream<Integer> content) {
        logger.info("START CREATING LINK INTEGER");
        List<Integer> elem = content.toList();
        List<LinkType> types = elements.toList();
        int initSize = links.size();
        for (int i = 0; i < types.size(); i++) {
            long id = random.nextLong();
            logger.info("creating integer link with type {}, content {} and id {}", types.get(i), elem.get(i), id);
            links.add(new MockScLinkInteger(random.nextLong(), types.get(i), elem.get(i)));
        }
        Stream<ScLink> result = links.stream().skip(initSize);
        logger.info("END CREATING LINK INTEGER");
        return result;
    }

    @Override
    public Stream<? extends ScElement> createFloatLink(Stream<LinkType> elements, Stream<Float> content) {
        logger.info("START CREATING LINK FLOAT");
        List<Float> elem = content.toList();
        List<LinkType> types = elements.toList();
        int initSize = links.size();
        for (int i = 0; i < types.size(); i++) {
            long id = random.nextLong();
            logger.info("creating float link with type {}, content {} and id {}", types.get(i), elem.get(i), id);
            links.add(new MockScLinkFloat(random.nextLong(), types.get(i), elem.get(i)));
        }
        Stream<ScLink> result = links.stream().skip(initSize);
        logger.info("END CREATING LINK FLOAT");
        return result;
    }

    @Override
    public Stream<? extends ScElement> createStringLink(Stream<LinkType> elements, Stream<String> content) {
        logger.info("START CREATING LINK STRING");
        List<String> elem = content.toList();
        List<LinkType> types = elements.toList();
        int initSize = links.size();
        for (int i = 0; i < types.size(); i++) {
            long id = random.nextLong();
            logger.info("creating string link with type {}, content {} and id {}", types.get(i), elem.get(i), id);
            links.add(new MockScLinkString(random.nextLong(), types.get(i), elem.get(i)));
        }
        Stream<ScLink> result = links.stream().skip(initSize);
        logger.info("END CREATING LINK STRING");
        return result;    }

    @Override
    public Stream<? extends ScElement> createBinaryLink(Stream<LinkType> elements, Stream<Object> content) {
        throw new UnsupportedOperationException();
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

    @Override
    public Stream<Boolean> setBinaryContent(Stream<ScLinkString> links, Stream<Object> content) {
        return null;
    }
}
