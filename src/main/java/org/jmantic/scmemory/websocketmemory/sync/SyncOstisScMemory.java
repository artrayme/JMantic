package org.jmantic.scmemory.websocketmemory.sync;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.*;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.exception.ScMemoryException;
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

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;


/**
 * @author Michael
 * @since 0.0.1
 */
public class SyncOstisScMemory implements ScMemory {
    private final RequestSender requestSender;

    public SyncOstisScMemory(URI serverURI) {
        requestSender = new RequestSenderImpl(new OstisClientSync(serverURI));
    }

    @Override
    public Stream<? extends ScNode> createNodes(Stream<NodeType> elements) throws ScMemoryException {
        List<ScNodeImpl> nodesToCreate = elements
                .map(ScNodeImpl::new)
                .toList();
        CreateScElRequest request = new CreateScElRequestImpl();
        request.addToRequest(nodesToCreate);

        CreateScElResponse response = requestSender.sendCreateElRequest(request);

        var addresses = response.getAddresses().toList();
        for (int i = 0; i < addresses.size(); i++) {
            ScNodeImpl node = nodesToCreate.get(i);
            long address = addresses.get(i);
            node.setAddress(address);
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

        CreateScElResponse response = requestSender.sendCreateElRequest(request);

        var addresses = response.getAddresses().toList();
        for (int i = 0; i < addresses.size(); i++) {
            ScEdge e = result.get(i);
            ((ScEdgeImpl) e).setAddress(addresses.get(i));

        }
        return result.stream();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stream<? extends ScLinkInteger> createIntegerLink(Stream<LinkType> elements, Stream<Integer> content) throws ScMemoryException {
        return (Stream<? extends ScLinkInteger>) createLink(elements, content, LinkContentType.INTEGER);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stream<? extends ScLinkFloat> createFloatLink(Stream<LinkType> elements, Stream<Float> content) throws ScMemoryException {
        return (Stream<? extends ScLinkFloat>) createLink(elements, content, LinkContentType.FLOAT);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stream<? extends ScLinkString> createStringLink(Stream<LinkType> elements, Stream<String> content) throws ScMemoryException {
        return (Stream<? extends ScLinkString>) createLink(elements, content, LinkContentType.STRING);
    }

    @Override
    public boolean deleteElements(Stream<? extends ScElement> elements) throws ScMemoryException {
        DeleteScElRequest request = new DeleteScElRequestImpl();
        elements.forEach(el -> request.addAddressToRequest(el.getAddress()));

        DeleteScElResponse response = requestSender.sendDeleteElRequest(request);

        return response.getResponseStatus();
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
        return setLinkContent(links, content);
    }

    @Override
    public Stream<Boolean> setFloatLinkContent(Stream<? extends ScLinkFloat> links, Stream<Float> content) throws ScMemoryException {
        return setLinkContent(links, content);
    }

    @Override
    public Stream<Boolean> setStringLinkContent(Stream<? extends ScLinkString> links, Stream<String> content) throws ScMemoryException {
        return setLinkContent(links, content);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stream<Integer> getIntegerLinkContent(Stream<? extends ScLinkInteger> elements) throws ScMemoryException {
        return (Stream<Integer>) getLinkContent(elements);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stream<Float> getFloatLinkContent(Stream<? extends ScLinkFloat> elements) throws ScMemoryException {
        return (Stream<Float>) getLinkContent(elements);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stream<String> getStringLinkContent(Stream<? extends ScLinkString> elements) throws ScMemoryException {
        return (Stream<String>) getLinkContent(elements);
    }

    private <C> Stream<? extends ScEntity> createLink(Stream<LinkType> elements, Stream<C> content
            , LinkContentType contentType) throws ScMemoryException {
        CreateScElRequest request = new CreateScElRequestImpl();
        List<ScEntity> result = new ArrayList<>();
        Iterator<LinkType> linkTypeIter = elements.iterator();
        Iterator<C> linkContentIter = content.iterator();
        while (linkTypeIter.hasNext() && linkContentIter.hasNext()) {
            ScEntity link;
            LinkType type = linkTypeIter.next();
            switch (contentType) {
                case FLOAT -> {
                    ScLinkFloatImpl l = new ScLinkFloatImpl(type);
                    l.setContent((float) linkContentIter.next());
                    link = l;
                }
                case STRING -> {
                    ScLinkStringImpl l = new ScLinkStringImpl(type);
                    l.setContent((String) linkContentIter.next());
                    link = l;
                }
                case INTEGER -> {
                    ScLinkIntegerImpl l = new ScLinkIntegerImpl(type);
                    l.setContent((Integer) linkContentIter.next());
                    link = l;
                }
                default -> throw new IllegalArgumentException("unknown type of content");
            }
            result.add(link);
            request.addElementToRequest(link);
        }

        CreateScElResponse response = requestSender.sendCreateElRequest(request);

        if (!response.getResponseStatus()) {
            throw new ScMemoryException("the response status is FALSE");
        }
        List<Long> addresses = response.getAddresses().toList();
        for (int i = 0; i < addresses.size(); i++) {
            long address = addresses.get(i);
            ScEntity link = result.get(i);
            link.setAddress(address);
        }
        return result.stream();
    }

    private <L, C> Stream<Boolean> setLinkContent(Stream<L> links, Stream<C> content) throws ScMemoryException {
        SetLinkContentRequestImpl request = new SetLinkContentRequestImpl();
        Iterator<L> linksIter = links.iterator();
        Iterator<C> contentIter = content.iterator();
        List<ScLink> linksWithoutContent = new ArrayList<>();
        List<C> contentWithoutLink = new ArrayList<>();
        while (linksIter.hasNext() && contentIter.hasNext()) {
            ScLink link = (ScLink) linksIter.next();
            linksWithoutContent.add(link);
            C data = contentIter.next();
            contentWithoutLink.add(data);
            request.addToRequest(link, data);
        }
        if (linksIter.hasNext() != contentIter.hasNext()) {
            throw new IllegalArgumentException("All passed streams must have same length");
        }

        SetLinkContentResponse response = requestSender.sendSetLinkContentRequest(request);

        if (!response.getResponseStatus()) {
            throw new ScMemoryException("the response status is FALSE");
        }
        List<Boolean> statusOfOperation = response.getOperationStatus();
        for (int i = 0; i < statusOfOperation.size(); i++) {
            boolean status = statusOfOperation.get(i);
            if (status) {
                ScLink link = linksWithoutContent.get(i);
                C data = contentWithoutLink.get(i);
                switch (link.getContentType()) {
                    case FLOAT -> ((ScLinkFloatImpl) link).setContent((float) data);
                    case INTEGER -> ((ScLinkIntegerImpl) link).setContent((int) data);
                    case STRING -> ((ScLinkStringImpl) link).setContent((String) data);
                }
            }
        }
        return statusOfOperation.stream();
    }

    private Stream<?> getLinkContent(Stream<? extends ScLink> elements) throws ScMemoryException {
        GetLinkContentRequest request = new GetLinkContentRequestImpl();
        List<? extends ScLink> links = elements.peek(l -> request.addAddressToRequest(l.getAddress())).toList();

        GetLinkContentResponse response = requestSender.sendGetLinkContentRequest(request);

        List<Object> values = response.getContent();
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < links.size(); i++) {
            Object value = values.get(i);
            if (value != null) {
                ScLink link = links.get(i);
                switch (link.getContentType()) {
                    case INTEGER -> {
                        Integer content = (Integer) value;
                        result.add(content);
                        ((ScLinkIntegerImpl) link).setContent(content);
                    }
                    case FLOAT -> {
                        float content = ((Double) value).floatValue();
                        result.add(content);
                        ((ScLinkFloatImpl) link).setContent(content);
                    }
                    case STRING -> {
                        String content = (String) value;
                        result.add(content);
                        ((ScLinkStringImpl) link).setContent(content);
                    }
                    default -> throw new IllegalArgumentException("unknown type of content");
                }
            }
        }

        return result.stream();
    }
}
