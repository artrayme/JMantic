package org.jmantic.scmemory.websocketmemory.sync;

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
import org.jmantic.scmemory.model.exception.ScMemoryConfigurationException;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.core.OstisClient;
import org.jmantic.scmemory.websocketmemory.message.request.CreateScElRequest;
import org.jmantic.scmemory.websocketmemory.message.response.CreateScElResponse;
import org.jmantic.scmemory.websocketmemory.sender.RequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Michael
 * @since 0.0.1
 */
public class SyncScMemory implements ScMemory {
    private final static Logger logger = LoggerFactory.getLogger(SyncScMemory.class);
    private final static SyncScMemory instance = new SyncScMemory();
    private final OstisClient ostisClient;
    private final RequestSender requestSender;

    private SyncScMemory() {
        ostisClient = OstisClientImpl.INSTANCE;
        requestSender = new RequestSenderImpl(ostisClient);
    }

    public static synchronized SyncScMemory getSyncScMemory(URI serverUri) {
        instance.ostisClient.configure(serverUri);
        return instance;
    }

    public static synchronized SyncScMemory getSyncScMemory(String uri) {
        try {
            instance.ostisClient.configure(new URI(uri));
        } catch (URISyntaxException e) {
            String msg = "error in uri";
            logger.error(msg);
            throw new ScMemoryConfigurationException(msg, e);
        }
        return instance;
    }

    public static synchronized SyncScMemory getSyncScMemory() {
        return instance;
    }

    //todo (0(

    @Override
    public Stream<? extends ScElement> createNodes(Stream<NodeType> elements) throws ScMemoryException {

        List<ScNodeImpl> nodesToCreate = elements
                .map(ScNodeImpl::new)
                .collect(Collectors.toList());

        CreateScElRequest request = new CreateScElRequestImpl();
        request.addToRequest(nodesToCreate);

        logger.info("Nodes to create - {}", nodesToCreate);

        CreateScElResponse response = requestSender.sendCreateElRequest(request);
        var addresses = response.getAddresses().collect(Collectors.toList());
        logger.info("Sc addresses of nodes - {}", addresses);
        for (int i = 0; i < addresses.size(); i++) {
            ScNodeImpl node = nodesToCreate.get(i);
            long addr = addresses.get(i);
            node.setAddress(addr);
        }
        return nodesToCreate.stream();
    }

    @Override
    public Stream<? extends ScElement> createEdges(Stream<EdgeType> elements,
                                                   Stream<? extends ScElement> firstComponents,
                                                   Stream<? extends ScElement> secondComponents) throws ScMemoryException {
        List<ScEdge> result = new ArrayList<>();
        CreateScElRequest request = new CreateScElRequestImpl();
        Iterator<EdgeType> elementsTypesIter = elements.iterator();
        Iterator<? extends ScElement> firstComponentsIter = firstComponents.iterator();
        Iterator<? extends ScElement> secondComponentsIter = secondComponents.iterator();
        while (elementsTypesIter.hasNext() && firstComponentsIter.hasNext() && secondComponentsIter.hasNext()) {
            ScEdge edge = new ScEdgeImpl(elementsTypesIter.next(), firstComponentsIter.next(), secondComponentsIter.next());
            request.addElementToRequest(edge);
            result.add(edge);
        }
        logger.info("Edges to create - {}", request);
        CreateScElResponse response = requestSender.sendCreateElRequest(request);
        var addresses = response.getAddresses().collect(Collectors.toList());
        logger.info("Sc addresses of edges - {}", addresses);

        for (int i = 0; i < addresses.size(); i++) {
            ScEdge e = result.get(i);
            ((ScEdgeImpl) e).setAddress(addresses.get(i));

        }
        return result.stream();
    }

    @Override
    public Stream<? extends ScElement> createIntegerLink(Stream<LinkType> elements, Stream<Integer> content) {
        return null;
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
