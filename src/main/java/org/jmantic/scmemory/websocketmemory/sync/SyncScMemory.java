package org.jmantic.scmemory.websocketmemory.sync;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.model.element.link.ScLinkBinary;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.exception.ScMemoryConfigurationException;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.core.OstisClient;
import org.jmantic.scmemory.websocketmemory.message.request.CreateScElRequest;
import org.jmantic.scmemory.websocketmemory.message.request.DeleteScElRequest;
import org.jmantic.scmemory.websocketmemory.message.request.SearchByTemplateRequest;
import org.jmantic.scmemory.websocketmemory.message.response.CreateScElResponse;
import org.jmantic.scmemory.websocketmemory.message.response.DeleteScElResponse;
import org.jmantic.scmemory.websocketmemory.message.response.SearchByTemplateResponse;
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
    public Stream<? extends ScLinkInteger> createIntegerLink(Stream<LinkType> elements, Stream<Integer> content) throws ScMemoryException {
        List<ScLinkIntegerImpl> result = new ArrayList<>();
        CreateScElRequest request = new CreateScElRequestImpl();
        Iterator<LinkType> linkTypeIter = elements.iterator();
        Iterator<Integer> linkContentIter = content.iterator();
        while (linkTypeIter.hasNext() && linkContentIter.hasNext()) {
            ScLinkIntegerImpl link = new ScLinkIntegerImpl(linkTypeIter.next());
            link.setContent(linkContentIter.next());
            result.add(link);
            request.addElementToRequest(link);
        }
        logger.info("Integer links to create - {}", result);
        CreateScElResponse response = requestSender.sendCreateElRequest(request);
        var addresses = response.getAddresses().collect(Collectors.toList());
        logger.info("Sc addresses of integer links - {}", addresses);
        for (int i = 0; i < addresses.size(); i++) {
            long address = addresses.get(i);
            ScLinkIntegerImpl link = result.get(i);
            link.setAddress(address);
        }
        return result.stream();
    }

    @Override
    public Stream<? extends ScLinkFloat> createFloatLink(Stream<LinkType> elements, Stream<Float> content) throws ScMemoryException {
        List<ScLinkFloatImpl> result = new ArrayList<>();
        CreateScElRequest request = new CreateScElRequestImpl();
        Iterator<LinkType> linkTypeIter = elements.iterator();
        Iterator<Float> linkContentIter = content.iterator();
        while (linkTypeIter.hasNext() && linkContentIter.hasNext()) {
            ScLinkFloatImpl link = new ScLinkFloatImpl(linkTypeIter.next());
            link.setContent(linkContentIter.next());
            result.add(link);
            request.addElementToRequest(link);
        }
        logger.info("Float links to create - {}", result);
        CreateScElResponse response = requestSender.sendCreateElRequest(request);
        var addresses = response.getAddresses().collect(Collectors.toList());
        logger.info("Sc addresses of float links - {}", addresses);
        for (int i = 0; i < addresses.size(); i++) {
            long address = addresses.get(i);
            ScLinkFloatImpl link = result.get(i);
            link.setAddress(address);
        }
        return result.stream();
    }

    @Override
    public Stream<? extends ScLinkString> createStringLink(Stream<LinkType> elements, Stream<String> content) throws ScMemoryException {
        List<ScLinkStringImpl> result = new ArrayList<>();
        CreateScElRequest request = new CreateScElRequestImpl();
        Iterator<LinkType> linkTypeIter = elements.iterator();
        Iterator<String> linkContentIter = content.iterator();
        while (linkTypeIter.hasNext() && linkContentIter.hasNext()) {
            ScLinkStringImpl link = new ScLinkStringImpl(linkTypeIter.next());
            link.setContent(linkContentIter.next());
            result.add(link);
            request.addElementToRequest(link);
        }
        logger.info("String links to create - {}", result);
        CreateScElResponse response = requestSender.sendCreateElRequest(request);
        var addresses = response.getAddresses().collect(Collectors.toList());
        logger.info("Sc addresses of string links - {}", addresses);
        for (int i = 0; i < addresses.size(); i++) {
            long address = addresses.get(i);
            ScLinkStringImpl link = result.get(i);
            link.setAddress(address);
        }
        return result.stream();
    }

    @Override
    public Stream<? extends ScLinkBinary> createBinaryLink(Stream<LinkType> elements, Stream<Object> content) throws ScMemoryException {
        // TODO: 6.11.21 method to create binary link
        return null;
    }

    @Override
    public boolean deleteElements(Stream<ScElement> elements) throws ScMemoryException {
        DeleteScElRequest request = new DeleteScElRequestImpl();
        elements.forEach(el -> request.addAddressToRequest(el.getAddress()));
        logger.info("Elements to delete - {}", request);
        DeleteScElResponse response = requestSender.sendDeleteElRequest(request);
        boolean result = response.getResponseStatus();
        logger.info("delete operation status - {}", result);
        return result;
    }

    @Override
    public Stream<? extends ScEdge> findByTemplateNodeEdgeNode(ScNode node, EdgeType edgeType, NodeType nodeType) throws ScMemoryException {
        SearchByTemplateRequest request = new SearchByTemplateNodeEdgeNodeRequestImpl(node, edgeType, nodeType);
        SearchByTemplateResponse response = requestSender.sendSearchByTemplateRequest(request);
        List<ScEdge> result = new ArrayList<>();
        response.getFoundAddresses().forEach(e -> {
            var currentTriple = e.toList();
            var targetNode = new ScNodeImpl(nodeType, currentTriple.get(2));
            result.add(new ScEdgeImpl(edgeType, node, targetNode, currentTriple.get(1)));
        });
        return result.stream();
    }

    @Override
    public Stream<? extends ScLink> getLinkContent(Stream<? extends ScLink> elements) throws ScMemoryException {
        return null;
    }

    @Override
    public Stream<Boolean> setLinkContent(Stream<? extends ScLink> links, Stream<Object> content) throws ScMemoryException {
        return null;
    }

}
