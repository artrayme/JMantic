package org.jmantic.scmemory.websocketmemory.sync;

import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkContentType;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.model.pattern.PatternElement;
import org.jmantic.scmemory.model.pattern.ScConstruction3;
import org.jmantic.scmemory.model.pattern.ScPattern3;
import org.jmantic.scmemory.websocketmemory.core.OstisClient;
import org.jmantic.scmemory.websocketmemory.message.request.CreateScElRequest;
import org.jmantic.scmemory.websocketmemory.message.request.DeleteScElRequest;
import org.jmantic.scmemory.websocketmemory.message.request.FindByPatternRequest;
import org.jmantic.scmemory.websocketmemory.message.request.GetLinkContentRequest;
import org.jmantic.scmemory.websocketmemory.message.request.SearchByTemplateRequest;
import org.jmantic.scmemory.websocketmemory.message.response.CreateScElResponse;
import org.jmantic.scmemory.websocketmemory.message.response.DeleteScElResponse;
import org.jmantic.scmemory.websocketmemory.message.response.FindByPatternResponse;
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
 * Implementation of the {@link ScMemory} interface. Basic operations
 * are available: creating elements, deleting, working with links, etc.
 * Communication with the base is established through the {@link OstisClient} and {@link RequestSender} interfaces.
 * Requests and responses are implemented using the {@link org.jmantic.scmemory.websocketmemory.message.request.ScRequest}
 * and {@link org.jmantic.scmemory.websocketmemory.message.response.ScResponse} family of classes.
 * The entity classes are also used, which store all the information about the sc-element:
 * <ul>
 *          <li>{@link ScEdge}</li>
 *          <li>{@link ScLink}</li>
 *          <li>{@link ScNode}</li>
 * </ul>
 *
 * @author Michael
 * @since 0.0.1
 */
public class SyncOstisScMemory implements ScMemory {
    private final RequestSender requestSender;
    private final OstisClient ostisClient;

    public SyncOstisScMemory(URI serverURI) {
        ostisClient = new OstisClientSync(serverURI);
        requestSender = new RequestSenderImpl(ostisClient);
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
    public Stream<? extends ScLinkInteger> createIntegerLinks(Stream<LinkType> elements, Stream<Integer> content) throws ScMemoryException {
        return (Stream<? extends ScLinkInteger>) createLink(elements, content, LinkContentType.INTEGER);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stream<? extends ScLinkFloat> createFloatLinks(Stream<LinkType> elements, Stream<Float> content) throws ScMemoryException {
        return (Stream<? extends ScLinkFloat>) createLink(elements, content, LinkContentType.FLOAT);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stream<? extends ScLinkString> createStringLinks(Stream<LinkType> elements, Stream<String> content) throws ScMemoryException {
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
    public <t1 extends ScElement, t3, T3 extends ScElement> Stream<? extends ScConstruction3<t1, T3>> findByPattern3(ScPattern3<t1, t3, T3> pattern) throws ScMemoryException {
        FindByPatternRequest request = new FindByPatternRequestImpl();
        BasicPatternTriple triple = new BasicPatternTriple(convertToPatternElement(pattern.get1()),
                convertToPatternElement(pattern.get2()),
                convertToPatternElement(pattern.get3()));
        request.addComponent(triple);

        FindByPatternResponse response = requestSender.sendFindByPatternRequest(request);
        List<ScConstruction3<t1, T3>> result = new ArrayList<>();
        List<Long> linksAddresses = new ArrayList<>();
        response.getFoundAddresses().forEach(e -> {
            var currentTriple = e.toList();
            var construction = new ScConstruction3Impl<t1, T3>();
            construction.setElement1(pattern.get1());
            if (pattern.get3() instanceof ScLink || pattern.get3() instanceof ScNode)
                construction.setElement3((T3) pattern.get3());
            else if (pattern.get3() instanceof NodeType nodeType) {
                construction.setElement3((T3) new ScNodeImpl(nodeType, currentTriple.get(2)));
            } else if (pattern.get3() instanceof LinkType linkType) {
                linksAddresses.add(currentTriple.get(2));
            }
            construction.setEdge(new ScEdgeImpl(pattern.get2(), construction.get1(), construction.get3(), currentTriple.get(1)));
            result.add(construction);
        });

        if (!linksAddresses.isEmpty()) {
            List<? extends ScLink> links = createLinksByAddresses(linksAddresses.stream(), (LinkType) pattern.get3()).toList();

            for (int i = 0; i < result.size(); i++) {
                ScConstruction3<t1, T3> construction = result.get(i);
                ((ScEdgeImpl) construction.getEdge()).setAddress(links.get(i).getAddress());
            }
        }
        return result.stream();
    }

    @Override
    public Stream<? extends ScEdge> findByTemplateNodeEdgeNode(ScNode fixedNode,
                                                               EdgeType edgeType,
                                                               NodeType nodeType) throws ScMemoryException {
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
    public Stream<? extends ScEdge> findByTemplateNodeEdgeLink(ScNode fixedNode,
                                                               EdgeType edgeType,
                                                               LinkType linkType,
                                                               LinkContentType contentType) throws ScMemoryException {
        SearchByTemplateRequest request = new SearchByTemplateNodeEdgeLinkRequestImpl(fixedNode, edgeType, linkType);

        return getScEdgesFromSearchingTemplate(fixedNode, edgeType, linkType, contentType, request);
    }

    @Override
    public Stream<? extends ScEdge> findByTemplateNodeEdgeLinkWithRelation(ScNode fixedNode,
                                                                           EdgeType edgeType,
                                                                           LinkType linkType,
                                                                           LinkContentType contentType,
                                                                           ScNode fixedRelationNode,
                                                                           EdgeType relationEdgeType) throws ScMemoryException {

        SearchByTemplateRequest request = new SearchByTemplateNodeEdgeLinkWithRelationRequestImpl(fixedNode, edgeType, linkType, fixedRelationNode, relationEdgeType);

        return getScEdgesFromSearchingTemplate(fixedNode, edgeType, linkType, contentType, request);
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
    public Stream<Integer> getIntegerLinkContent(Stream<? extends ScLinkInteger> links) throws ScMemoryException {
        return (Stream<Integer>) getLinkContent(links);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stream<Float> getFloatLinkContent(Stream<? extends ScLinkFloat> links) throws ScMemoryException {
        return (Stream<Float>) getLinkContent(links);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stream<String> getStringLinkContent(Stream<? extends ScLinkString> links) throws ScMemoryException {
        return (Stream<String>) getLinkContent(links);
    }

    @Override
    public void open() throws Exception {
        ostisClient.open();
    }

    @Override
    public void close() throws Exception {
        ostisClient.close();
    }

    private PatternElement convertToPatternElement(Object object) {
        if (object instanceof ScElement element) {
            return new FixedPatternElement(element);
        } else if (object instanceof NodeType type) {
            return new TypePatternElement<>(type);
        } else if (object instanceof EdgeType type) {
            return new TypePatternElement<>(type);
        } else if (object instanceof LinkType type) {
            return new TypePatternElement<>(type);
        }

        throw new IllegalArgumentException("You should path in ScPatterns only objects of type ScElement or of type NodeType|EdgeType|LinkType");
    }

    private Stream<? extends ScEdge> getScEdgesFromSearchingTemplate(ScNode fixedNode, EdgeType edgeType, LinkType linkType, LinkContentType contentType, SearchByTemplateRequest request) throws ScMemoryException {
        SearchByTemplateResponse response = requestSender.sendSearchByTemplateRequest(request);

        List<ScEdge> result = new ArrayList<>();
        response.getFoundAddresses().forEach(e -> {
            var currentTriple = e.toList();
            ScLink targetLink = null;
            try {
                targetLink = createLinkByContentType(linkType, currentTriple.get(2), contentType);
            } catch (ScMemoryException ex) {
                //                ToDo normal exception throwing
                ex.printStackTrace();
            }
            result.add(new ScEdgeImpl(edgeType, fixedNode, targetLink, currentTriple.get(1)));
        });
        return result.stream();
    }

    /**
     * Method for creating links already existing in the base
     *
     * @param linkType    type of sc-link
     * @param address     address of sc-link in base
     * @param contentType type of content
     * @return created sc-link
     * @throws ScMemoryException
     */
    private ScLink createLinkByContentType(LinkType linkType, Long address, LinkContentType contentType) throws ScMemoryException {
        return switch (contentType) {
            case INTEGER -> {
                var result = new ScLinkIntegerImpl(linkType, address);

                result.setContent(getIntegerLinkContent(Stream.of(result)).findFirst().get());
                yield result;
            }
            case FLOAT -> {
                var result = new ScLinkFloatImpl(linkType, address);
                result.setContent(getFloatLinkContent(Stream.of(result)).findFirst().get());
                yield result;
            }
            case STRING -> {
                var result = new ScLinkStringImpl(linkType, address);
                result.setContent(getStringLinkContent(Stream.of(result)).findFirst().get());
                yield result;
            }
            case BINARY -> throw new UnsupportedOperationException("Binary type is not implemented yet");
        };
    }


    /**
     * Method for creating links of different types of content.
     *
     * @param elements    type of links
     * @param content     stream of content
     * @param contentType type of content
     * @param <C>         generic for content
     * @return created sc-link
     * @throws ScMemoryException
     */
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

    /**
     * Method for replacing content in a link.
     *
     * @param links   links
     * @param content new content
     * @param <L>     generic for link
     * @param <C>     generic for content
     * @return link with new content
     * @throws ScMemoryException
     */
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

    /**
     * Method for getting content from link.
     *
     * @param elements links
     * @return stream of content
     * @throws ScMemoryException
     */
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

    /**
     * Method for getting content from link.
     *
     * @param addresses links
     * @return stream of content
     * @throws ScMemoryException
     */
    private Stream<? extends ScLink> createLinksByAddresses(Stream<Long> addresses, LinkType type) throws ScMemoryException {
        GetLinkContentRequest request = new GetLinkContentRequestImpl();
        List<Long> links = addresses.toList();

        GetLinkContentResponse response = requestSender.sendGetLinkContentRequest(request);

        List<Object> values = response.getContent();
        List<LinkContentType> types = response.getType();
        List<ScLink> result = new ArrayList<>();
        for (int i = 0; i < links.size(); i++) {
            switch (types.get(i)) {
                case INTEGER -> {
                    Integer content = (Integer) values.get(i);
                    ScLinkIntegerImpl scLinkInteger = new ScLinkIntegerImpl(type, links.get(i));
                    scLinkInteger.setContent(content);
                    result.add(scLinkInteger);
                }
                case FLOAT -> {
                    Float content = (Float) values.get(i);
                    ScLinkFloatImpl scLinkInteger = new ScLinkFloatImpl(type, links.get(i));
                    scLinkInteger.setContent(content);
                    result.add(scLinkInteger);
                }
                case STRING -> {
                    String content = (String) values.get(i);
                    ScLinkStringImpl scLinkInteger = new ScLinkStringImpl(type, links.get(i));
                    scLinkInteger.setContent(content);
                    result.add(scLinkInteger);
                }
                default -> throw new IllegalArgumentException("unknown type of content");
            }
        }

        return result.stream();
    }
}
