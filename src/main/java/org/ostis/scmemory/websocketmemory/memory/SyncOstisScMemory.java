package org.ostis.scmemory.websocketmemory.memory;

import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.UnknownScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.link.LinkContentType;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLink;
import org.ostis.scmemory.model.element.link.ScLinkFloat;
import org.ostis.scmemory.model.element.link.ScLinkInteger;
import org.ostis.scmemory.model.element.link.ScLinkString;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.model.pattern.element.ScAliasedElement;
import org.ostis.scmemory.model.pattern.pattern3.ScConstruction3;
import org.ostis.scmemory.model.pattern.pattern5.ScConstruction5;
import org.ostis.scmemory.model.pattern.element.ScFixedElement;
import org.ostis.scmemory.model.pattern.ScPattern;
import org.ostis.scmemory.model.pattern.pattern3.ScPattern3;
import org.ostis.scmemory.model.pattern.pattern5.ScPattern5;
import org.ostis.scmemory.model.pattern.element.ScPatternElement;
import org.ostis.scmemory.model.pattern.ScPatternTriplet;
import org.ostis.scmemory.model.pattern.element.ScTypedElement;
import org.ostis.scmemory.websocketmemory.core.OstisClient;
import org.ostis.scmemory.websocketmemory.memory.exception.ExceptionMessages;
import org.ostis.scmemory.websocketmemory.message.request.CheckScElTypeRequest;
import org.ostis.scmemory.websocketmemory.message.request.CreateScElRequest;
import org.ostis.scmemory.websocketmemory.message.request.DeleteScElRequest;
import org.ostis.scmemory.websocketmemory.message.request.FindByPatternRequest;
import org.ostis.scmemory.websocketmemory.message.request.FindKeynodeRequest;
import org.ostis.scmemory.websocketmemory.message.request.GetLinkContentRequest;
import org.ostis.scmemory.websocketmemory.message.response.CheckScElTypeResponse;
import org.ostis.scmemory.websocketmemory.message.response.CreateScElResponse;
import org.ostis.scmemory.websocketmemory.message.response.DeleteScElResponse;
import org.ostis.scmemory.websocketmemory.message.response.FindByPatternResponse;
import org.ostis.scmemory.websocketmemory.message.response.FindKeynodeResponse;
import org.ostis.scmemory.websocketmemory.message.response.GetLinkContentResponse;
import org.ostis.scmemory.websocketmemory.message.response.SetLinkContentResponse;
import org.ostis.scmemory.websocketmemory.sender.RequestSender;
import org.ostis.scmemory.websocketmemory.memory.core.OstisClientSync;
import org.ostis.scmemory.websocketmemory.memory.element.ScEdgeImpl;
import org.ostis.scmemory.websocketmemory.memory.element.ScEntity;
import org.ostis.scmemory.websocketmemory.memory.element.ScLinkFloatImpl;
import org.ostis.scmemory.websocketmemory.memory.element.ScLinkIntegerImpl;
import org.ostis.scmemory.websocketmemory.memory.element.ScLinkStringImpl;
import org.ostis.scmemory.websocketmemory.memory.element.ScNodeImpl;
import org.ostis.scmemory.websocketmemory.memory.message.request.CheckScElTypeRequestImpl;
import org.ostis.scmemory.websocketmemory.memory.message.request.CreateScElRequestImpl;
import org.ostis.scmemory.websocketmemory.memory.message.request.DeleteScElRequestImpl;
import org.ostis.scmemory.websocketmemory.memory.message.request.FindByPatternRequestImpl;
import org.ostis.scmemory.websocketmemory.memory.message.request.FindKeynodeRequestImpl;
import org.ostis.scmemory.websocketmemory.memory.message.request.GetLinkContentRequestImpl;
import org.ostis.scmemory.websocketmemory.memory.message.request.SetLinkContentRequestImpl;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.AliasPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.FixedPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.TypePatternElement;
import org.ostis.scmemory.websocketmemory.memory.sender.RequestSenderImpl;
import org.ostis.scmemory.websocketmemory.memory.pattern.BasicPatternTriple;
import org.ostis.scmemory.websocketmemory.memory.structures.ScConstruction3Impl;
import org.ostis.scmemory.websocketmemory.memory.structures.ScConstruction5Impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;


/**
 * Implementation of the {@link ScMemory} interface. Basic operations
 * are available: creating elements, deleting, working with links, etc.
 * Communication with the base is established through the {@link OstisClient} and {@link RequestSender} interfaces.
 * Requests and responses are implemented using the {@link org.ostis.scmemory.websocketmemory.message.request.ScRequest}
 * and {@link org.ostis.scmemory.websocketmemory.message.response.ScResponse} family of classes.
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
    private final Map<Long, ScElement> searchedScElements = new HashMap<>();
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

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
        return (Stream<? extends ScLinkInteger>) createLink(elements, content, LinkContentType.INT);
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

        ScPattern pattern3 = new DefaultWebsocketScPattern();

        ScPatternTriplet triple = new BasicPatternTriple(
                new FixedPatternElement(pattern.get1()),
                new TypePatternElement<>(pattern.get2(), new AliasPatternElement("edge_2")),
                convertToPatternElement(pattern.get3(), new AliasPatternElement("element_3"))
        );
        pattern3.addElement(triple);

        var response = this.find(pattern3);

        List<Stream<? extends ScElement>> streams = response.toList();
        List<ScConstruction3<t1, T3>> result = new ArrayList<>(streams.size());
        for (Stream<? extends ScElement> stream : streams) {
            List<? extends ScElement> currentTriplet = stream.toList();
            ScConstruction3<t1, T3> temp = new ScConstruction3Impl<>(
                    ((t1) currentTriplet.get(0)),
                    ((ScEdge) currentTriplet.get(1)),
                    ((T3) currentTriplet.get(2)));
            result.add(temp);
        }

        return result.stream();
    }

    @Override
    public <t1 extends ScElement, t3, t5, T3 extends ScElement, T5 extends ScElement> Stream<? extends ScConstruction5<t1, T3, T5>> findByPattern5(ScPattern5<t1, t3, t5, T3, T5> pattern) throws ScMemoryException {

        ScPattern pattern5 = new DefaultWebsocketScPattern();

        ScAliasedElement edge2Alias = new AliasPatternElement("edge_2");
        ScPatternTriplet triple = new BasicPatternTriple(
                new FixedPatternElement(pattern.get1()),
                new TypePatternElement<>(pattern.get2(), edge2Alias),
                convertToPatternElement(pattern.get3(), new AliasPatternElement("element_3"))
        );
        ScPatternTriplet relTriple = new BasicPatternTriple(
                convertToPatternElement(pattern.get5(), new AliasPatternElement("element_5")),
                new TypePatternElement<>(pattern.get4(), new AliasPatternElement("edge_4")),
                edge2Alias
        );
        pattern5.addElement(triple);
        pattern5.addElement(relTriple);

        var response = this.find(pattern5);

        List<Stream<? extends ScElement>> streams = response.toList();
        List<ScConstruction5<t1, T3, T5>> result = new ArrayList<>();

        for (Stream<? extends ScElement> stream : streams) {
            List<? extends ScElement> currentElementsSet = stream.toList();
            ScConstruction5<t1, T3, T5> temp = new ScConstruction5Impl<>(
                    ((t1) currentElementsSet.get(0)),
                    ((ScEdge) currentElementsSet.get(1)),
                    ((T3) currentElementsSet.get(2)),
                    ((ScEdge) currentElementsSet.get(4)),
                    ((T5) currentElementsSet.get(3)));
            result.add(temp);
        }

        return result.stream();
    }

    public Stream<Stream<? extends ScElement>> find(ScPattern pattern) throws ScMemoryException {
        searchedScElements.clear();
        return findPattern(pattern);
    }

    private Stream<Stream<? extends ScElement>> findPattern(ScPattern pattern) throws ScMemoryException {
        FindByPatternRequest request = new FindByPatternRequestImpl();
        pattern.getElements().forEach(request::addComponent);

        FindByPatternResponse response = requestSender.sendFindByPatternRequest(request);
        List<List<ScElement>> result = new ArrayList<>();

        List<ScPatternElement> patternElements = pattern.getElements().flatMap(e -> Stream.of(e.get1(), e.get2(), e.get3())).toList();
        Map<ScAliasedElement, ScElement> aliases = new HashMap<>(patternElements.size(), 1);

        for (Stream<Long> triplet : response.getFoundAddresses().toList()) {
            Iterator<ScPatternElement> patternElementIterator = patternElements.iterator();
            List<ScElement> tempResult = new ArrayList<>(patternElements.size());
            Iterator<Long> addressesIterator = triplet.iterator();
            while (patternElementIterator.hasNext()) {
                var el = patternElementIterator.next();
                switch (el.getType()) {
                    case ALIAS -> {
                        tempResult.add(aliases.get((ScAliasedElement) el));
                        addressesIterator.next();
                    }
                    case TYPE -> {
                        var element = createScElementByType(((ScTypedElement<?>) el).getValue(), addressesIterator.next());
                        tempResult.add(element);
                        aliases.put(((ScTypedElement<?>) el).getAlias(), element);
                        searchedScElements.put(element.getAddress(), element);
                    }
                    case ADDR -> {
                        ScFixedElement fixedElement = (ScFixedElement) el;
                        tempResult.add(fixedElement.getElement());
                        searchedScElements.put(fixedElement.getElement().getAddress(), fixedElement.getElement());
                        addressesIterator.next();
                    }
                    default -> throw new IllegalStateException(ExceptionMessages.sendReportToDeveloper);
                }
            }
            result.add(tempResult);
        }

        return result.stream().map(Collection::stream);
    }

    private ScElement createScElementByType(Object type, Long addr) throws ScMemoryException {
        if (type instanceof EdgeType edgeType) {
            ScPattern pattern = new DefaultWebsocketScPattern();
            ScEdgeImpl edge = new ScEdgeImpl(edgeType, addr);
            pattern.addElement(new BasicPatternTriple(
                    new TypePatternElement<>(UnknownScElement.ELEMENT, new AliasPatternElement("1")),
                    new FixedPatternElement(edge),
                    new TypePatternElement<>(UnknownScElement.ELEMENT, new AliasPatternElement("2"))
            ));
            FindByPatternRequest request = new FindByPatternRequestImpl();
            pattern.getElements().forEach(request::addComponent);
            List<? extends ScElement> triplet;
            Optional<Stream<? extends ScElement>> first = findPattern(pattern).findFirst();
            if (first.isPresent()) {
                triplet = first.get().toList();
            } else {
                throw new IllegalStateException(ExceptionMessages.sendReportToDeveloper);
            }
            ScElement sourceElement = searchedScElements.get(triplet.get(0).getAddress());
            ScElement targetElement = searchedScElements.get(triplet.get(2).getAddress());
            edge.setSourceElement(sourceElement);
            edge.setTargetElement(targetElement);

            return edge;
        } else if (type instanceof NodeType nodeType) {
            return new ScNodeImpl(nodeType, addr);
        } else if (type instanceof LinkType linkType) {

            Optional<? extends ScLink> first = createLinksByAddresses(Stream.of(addr), linkType).findFirst();
            if (first.isPresent()) {
                return first.get();
            } else {
                throw new IllegalStateException(ExceptionMessages.sendReportToDeveloper);
            }
        } else if (type instanceof UnknownScElement) {
            try {
                Object o = checkElementType(addr);
                ScElement element = createScElementByType(o, addr);
                searchedScElements.put(addr, element);
                return element;
            } catch (Exception e) {
                throw new ScMemoryException(e);
            }
        }
        throw new IllegalArgumentException(ExceptionMessages.functionalityNotImplementedYet);
    }

    private Object checkElementType(Long addr) throws Exception {
        Callable<CheckScElTypeResponse> task = () -> {
            OstisClient client = new OstisClientSync(requestSender.getAddress());
            RequestSender sender = new RequestSenderImpl(client);
            client.open();
            CheckScElTypeRequest request = new CheckScElTypeRequestImpl();
            request.add(addr);
            CheckScElTypeResponse res = sender.sendCheckScElTypeRequest(request);
            client.close();
            return res;
        };
        var result = forkJoinPool.submit(task).get();
        Optional<Object> first = result.getTypes().findFirst();
        if (first.isPresent()) {
            return first.get();
        } else {
            throw new IllegalStateException(ExceptionMessages.sendReportToDeveloper);
        }
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
    public Stream<Optional<? extends ScLinkString>> findKeynodes(Stream<String> idtf) throws ScMemoryException {
        FindKeynodeRequest request = new FindKeynodeRequestImpl();
        List<String> content = idtf.toList();
        request.addAllIdtf(content);
        FindKeynodeResponse response = requestSender.sendFindKeynodeRequest(request);

        Iterator<String> contentIterator = content.iterator();
        List<Optional<? extends ScLinkString>> result = new ArrayList<>(content.size());
        response.getFindAddresses().forEach(e -> {
            if (e == 0) {
                ScLinkStringImpl link = new ScLinkStringImpl(LinkType.LINK, e);
                link.setContent(contentIterator.next());
                result.add(Optional.of(link));
            } else
                result.add(Optional.empty());
        });
        return result.stream();
    }

    @Override
    public void open() throws Exception {
        ostisClient.open();
    }

    @Override
    public void close() throws Exception {
        ostisClient.close();
    }

    private ScPatternElement convertToPatternElement(Object object, ScAliasedElement alias) {
        if (object instanceof ScElement element) {
            return new FixedPatternElement(element);
        } else if (object instanceof NodeType type) {
            return new TypePatternElement<>(type, alias);
        } else if (object instanceof LinkType type) {
            return new TypePatternElement<>(type, alias);
        }

        throw new IllegalArgumentException("You should path in ScPatterns only objects of type ScElement or of type NodeType|EdgeType|LinkType");
    }

    /**
     * Method for creating links of different types of content.
     *
     * @param elements    type of links
     * @param content     stream of content
     * @param contentType type of content
     * @param <C>         generic for content
     * @return created sc-link
     * @throws ScMemoryException - see cause for more details.
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
                case INT -> {
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
     * @throws ScMemoryException - see cause for more details.
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
                    case INT -> ((ScLinkIntegerImpl) link).setContent((int) data);
                    case STRING -> ((ScLinkStringImpl) link).setContent((String) data);
                    case BINARY -> {
                        throw new UnsupportedOperationException("Binary links are not implemented yet");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + link.getContentType());
                }
            }
        }
        return statusOfOperation.stream();
    }

    /**
     * Method for getting content from a link.
     *
     * @param elements links
     * @return stream of content
     * @throws ScMemoryException - see cause for more details.
     */
    private Stream<?> getLinkContent(Stream<? extends ScLink> elements) throws ScMemoryException {
        GetLinkContentRequest request = new GetLinkContentRequestImpl();
        //        todo change peek to something else (cause peek is used only for debugging)
        List<? extends ScLink> links = elements.peek(l -> request.addAddressToRequest(l.getAddress())).toList();

        GetLinkContentResponse response = requestSender.sendGetLinkContentRequest(request);

        List<Object> values = response.getContent();
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < links.size(); i++) {
            Object value = values.get(i);
            if (value != null) {
                ScLink link = links.get(i);
                switch (link.getContentType()) {
                    case INT -> {
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
     * Method for getting content from a link.
     *
     * @param addresses links
     * @return stream of content
     * @throws ScMemoryException - see cause for more details.
     */
    private Stream<? extends ScLink> createLinksByAddresses(Stream<Long> addresses, LinkType type) throws ScMemoryException {
        GetLinkContentRequest request = new GetLinkContentRequestImpl();
        List<Long> links = addresses.toList();
        request.addToRequest(links);

        GetLinkContentResponse response = requestSender.sendGetLinkContentRequest(request);

        List<Object> values = response.getContent();
        List<LinkContentType> types = response.getType();
        List<ScLink> result = new ArrayList<>();
        for (int i = 0; i < links.size(); i++) {
            switch (types.get(i)) {
                case INT -> {
                    Integer content = (Integer) values.get(i);
                    ScLinkIntegerImpl scLinkInteger = new ScLinkIntegerImpl(type, links.get(i));
                    scLinkInteger.setContent(content);
                    result.add(scLinkInteger);
                }
                case FLOAT -> {
                    float content = ((Double) values.get(i)).floatValue();
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
