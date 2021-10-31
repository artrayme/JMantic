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
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.core.OstisClient;
import org.jmantic.scmemory.websocketmemory.message.request.CreateScElRequest;
import org.jmantic.scmemory.websocketmemory.message.response.CreateScElResponse;
import org.jmantic.scmemory.websocketmemory.sender.RequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.stream.Stream;


/**
 * @author Michael
 */
public class SyncScMemory implements ScMemory {
    private final static Logger logger = LoggerFactory.getLogger(SyncScMemory.class);
    private final OstisClient ostisClient = OstisClientImpl.INSTANCE;
    private final RequestSender requestSender = new RequestSenderImpl(ostisClient);

    public SyncScMemory(URI serverUri) {
        ostisClient.configure(serverUri);
    }

    //todo (0(

    @Override
    public Stream<? extends ScElement> createNodes(Stream<NodeType> elements) throws ScMemoryException {
        CreateScElRequest request = new CreateScElRequestImpl();
        var nodesToCreate = elements
                .map(ScNodeImpl::new)
                .toList();
        nodesToCreate.forEach(request::addElementToRequest);

        logger.info("nodes to create - {}", nodesToCreate);

        CreateScElResponse response = requestSender.sendCreateElRequest(request);
        var addresses = response.getAddresses().toList();
        logger.info("sc addresses of nodes - {}", addresses);
        for (int i = 0; i < addresses.size(); i++) {
            ScNodeImpl node = nodesToCreate.get(i);
            long addr = addresses.get(i);
            node.setAddress(addr);
        }
        return nodesToCreate.stream();
    }

    @Override
    public Stream<? extends ScElement> createEdges(Stream<EdgeType> elements, Stream<ScElement> firstComponents, Stream<ScElement> secondComponents) {
        return null;
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
