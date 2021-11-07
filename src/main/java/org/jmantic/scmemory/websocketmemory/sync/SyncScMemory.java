package org.jmantic.scmemory.websocketmemory.sync;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkType;
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
import org.jmantic.scmemory.websocketmemory.message.request.GetLinkContentRequest;
import org.jmantic.scmemory.websocketmemory.message.request.SearchByTemplateRequest;
import org.jmantic.scmemory.websocketmemory.message.response.CreateScElResponse;
import org.jmantic.scmemory.websocketmemory.message.response.DeleteScElResponse;
import org.jmantic.scmemory.websocketmemory.message.response.GetLinkContentResponse;
import org.jmantic.scmemory.websocketmemory.message.response.SearchByTemplateResponse;
import org.jmantic.scmemory.websocketmemory.message.response.SetLinkContentResponse;
import org.jmantic.scmemory.websocketmemory.sender.RequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    public Stream<? extends ScNode> createNodes(Stream<NodeType> elements) throws ScMemoryException {

        List<ScNodeImpl> nodesToCreate = elements
                .map(ScNodeImpl::new)
                .toList();

        CreateScElRequest request = new CreateScElRequestImpl();
        request.addToRequest(nodesToCreate);

        logger.info("Nodes to create - {}", nodesToCreate);

        CreateScElResponse response = requestSender.sendCreateElRequest(request);
        var addresses = response.getAddresses().toList();
        logger.info("Sc addresses of nodes - {}", addresses);
        for (int i = 0; i < addresses.size(); i++) {
            ScNodeImpl node = nodesToCreate.get(i);
            long addr = addresses.get(i);
            node.setAddress(addr);
        }
        return nodesToCreate.stream();
    }

    @Override
    public Stream<? extends ScEdge> createEdges(Stream<EdgeType> elements,
                                                Stream<? extends ScElement> sources,
                                                Stream<? extends ScElement> targets) throws ScMemoryException {
        List<ScEdge> result = new ArrayList<>();
        CreateScElRequest request = new CreateScElRequestImpl();
        Iterator<EdgeType> elementsTypesIter = elements.iterator();
        Iterator<? extends ScElement> firstComponentsIter = sources.iterator();
        Iterator<? extends ScElement> secondComponentsIter = targets.iterator();
        while (elementsTypesIter.hasNext() && firstComponentsIter.hasNext() && secondComponentsIter.hasNext()) {
            ScEdge edge = new ScEdgeImpl(elementsTypesIter.next(), firstComponentsIter.next(), secondComponentsIter.next());
            request.addElementToRequest(edge);
            result.add(edge);
        }
        if (elementsTypesIter.hasNext() != firstComponentsIter.hasNext() || elementsTypesIter.hasNext() != secondComponentsIter.hasNext()) {
            throw new IllegalArgumentException("All passed streams must have same length");
        }
        logger.info("Edges to create - {}", request);
        CreateScElResponse response = requestSender.sendCreateElRequest(request);
        var addresses = response.getAddresses().toList();
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
        var addresses = response.getAddresses().toList();
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
        var addresses = response.getAddresses().toList();
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
        var addresses = response.getAddresses().toList();
        logger.info("Sc addresses of string links - {}", addresses);
        for (int i = 0; i < addresses.size(); i++) {
            long address = addresses.get(i);
            ScLinkStringImpl link = result.get(i);
            link.setAddress(address);
        }
        return result.stream();
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
    public Stream<? extends ScEdge> findByTemplateNodeEdgeNode(ScNode fixedNode, EdgeType edgeType, NodeType nodeType) throws ScMemoryException {
        SearchByTemplateRequest request = new SearchByTemplateNodeEdgeNodeRequestImpl(fixedNode, edgeType, nodeType);
        SearchByTemplateResponse response = requestSender.sendSearchByTemplateRequest(request);
        List<ScEdge> result = new ArrayList<>();
        response.getFoundAddresses().forEach(e -> {
            var currentTriple = e.toList();
            var targetNode = new ScNodeImpl(nodeType, currentTriple.get(2));
            result.add(new ScEdgeImpl(edgeType, fixedNode, targetNode, currentTriple.get(1)));
        });
        return result.stream();
    }

    @Override
    public Stream<Boolean> setIntegerLinkContent(Stream<? extends ScLinkInteger> links, Stream<Integer> content) throws ScMemoryException {
        SetLinkContentRequestImpl request = new SetLinkContentRequestImpl();
        Iterator<? extends ScLinkInteger> linksIter = links.iterator();
        Iterator<Integer> contentIter = content.iterator();
        List<ScLinkIntegerImpl> linksWithoutContent = new ArrayList<>();
        List<Integer> contentWithoutLink = new ArrayList<>();
        while (linksIter.hasNext() && contentIter.hasNext()) {
            ScLinkIntegerImpl link = (ScLinkIntegerImpl) linksIter.next();
            linksWithoutContent.add(link);
            Integer data = contentIter.next();
            contentWithoutLink.add(data);
            request.addToRequest(link, data);
        }
        SetLinkContentResponse response = requestSender.sendSetLinkContentRequest(request);
        List<Boolean> statusOfOperation = response.getOperationStatus();
        for (int i = 0; i < statusOfOperation.size(); i++) {
            boolean status = statusOfOperation.get(i);
            if (status) {
                ScLinkIntegerImpl link = linksWithoutContent.get(i);
                Integer data = contentWithoutLink.get(i);
                link.setContent(data);
            }
        }
        return statusOfOperation.stream();
    }

    @Override
    public Stream<Boolean> setFloatLinkContent(Stream<? extends ScLinkFloat> links, Stream<Float> content) throws ScMemoryException {
        SetLinkContentRequestImpl request = new SetLinkContentRequestImpl();
        Iterator<? extends ScLinkFloat> linksIter = links.iterator();
        Iterator<Float> contentIter = content.iterator();
        List<ScLinkFloatImpl> linksWithoutContent = new ArrayList<>();
        List<Float> contentWithoutLink = new ArrayList<>();
        while (linksIter.hasNext() && contentIter.hasNext()) {
            ScLinkFloatImpl link = (ScLinkFloatImpl) linksIter.next();
            linksWithoutContent.add(link);
            Float data = contentIter.next();
            contentWithoutLink.add(data);
            request.addToRequest(link, data);
        }
        SetLinkContentResponse response = requestSender.sendSetLinkContentRequest(request);
        List<Boolean> statusOfOperation = response.getOperationStatus();
        for (int i = 0; i < statusOfOperation.size(); i++) {
            boolean status = statusOfOperation.get(i);
            if (status) {
                ScLinkFloatImpl link = linksWithoutContent.get(i);
                Float data = contentWithoutLink.get(i);
                link.setContent(data);
            }
        }
        return statusOfOperation.stream();
    }

    @Override
    public Stream<Boolean> setStringLinkContent(Stream<? extends ScLinkString> links, Stream<String> content) throws ScMemoryException {
        SetLinkContentRequestImpl request = new SetLinkContentRequestImpl();
        Iterator<? extends ScLinkString> linksIter = links.iterator();
        Iterator<String> contentIter = content.iterator();
        List<ScLinkStringImpl> linksWithoutContent = new ArrayList<>();
        List<String> contentWithoutLink = new ArrayList<>();
        while (linksIter.hasNext() && contentIter.hasNext()) {
            ScLinkStringImpl link = (ScLinkStringImpl) linksIter.next();
            linksWithoutContent.add(link);
            String data = contentIter.next();
            contentWithoutLink.add(data);
            request.addToRequest(link, data);
        }
        SetLinkContentResponse response = requestSender.sendSetLinkContentRequest(request);
        List<Boolean> statusOfOperation = response.getOperationStatus();
        for (int i = 0; i < statusOfOperation.size(); i++) {
            boolean status = statusOfOperation.get(i);
            if (status) {
                ScLinkStringImpl link = linksWithoutContent.get(i);
                String data = contentWithoutLink.get(i);
                link.setContent(data);
            }
        }
        return statusOfOperation.stream();
    }

    @Override
    public Stream<Integer> getIntegerLinkContent(Stream<? extends ScLinkInteger> elements) throws ScMemoryException {
        GetLinkContentRequest request = new GetLinkContentRequestImpl();
        List<ScLinkIntegerImpl> links = elements.map(l -> {
            request.addToRequest(l.getAddress());
            return (ScLinkIntegerImpl) l;
        }).toList();

        GetLinkContentResponse response = requestSender.sendGetLinkContentRequest(request);
        List<Object> values = response.getContent();
        for (int i = 0; i < links.size(); i++) {
            Object value = values.get(i);
            if (value != null) {
                ScLinkIntegerImpl link = links.get(i);
                link.setContent((Integer) value);
            }
        }

        return links.stream().map(ScLinkIntegerImpl::getContent);
    }

    @Override
    public Stream<Float> getFloatLinkContent(Stream<? extends ScLinkFloat> elements) throws ScMemoryException {
        GetLinkContentRequest request = new GetLinkContentRequestImpl();
        List<ScLinkFloatImpl> links = elements.map(l -> {
            request.addToRequest(l.getAddress());
            return (ScLinkFloatImpl) l;
        }).toList();

        GetLinkContentResponse response = requestSender.sendGetLinkContentRequest(request);
        List<Object> values = response.getContent();
        for (int i = 0; i < links.size(); i++) {
            Object value = values.get(i);
            if (value != null) {
                ScLinkFloatImpl link = links.get(i);
                link.setContent(((Double) value).floatValue());
            }
        }

        return links.stream().map(ScLinkFloatImpl::getContent);
    }

    @Override
    public Stream<String> getStringLinkContent(Stream<? extends ScLinkString> elements) throws ScMemoryException {
        GetLinkContentRequest request = new GetLinkContentRequestImpl();
        List<ScLinkStringImpl> links = elements.map(l -> {
            request.addToRequest(l.getAddress());
            return (ScLinkStringImpl) l;
        }).toList();

        GetLinkContentResponse response = requestSender.sendGetLinkContentRequest(request);
        List<Object> values = response.getContent();
        for (int i = 0; i < links.size(); i++) {
            Object value = values.get(i);
            if (value != null) {
                ScLinkStringImpl link = links.get(i);
                link.setContent((String) value);
            }
        }

        return links.stream().map(ScLinkStringImpl::getContent);
    }
}
