package org.jmantic.api.context;

import org.jmantic.scmemory.model.element.ScElement;
import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.edge.ScEdge;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLinkFloat;
import org.jmantic.scmemory.model.element.link.ScLinkInteger;
import org.jmantic.scmemory.model.element.link.ScLinkString;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.model.pattern.ScConstruction3;
import org.jmantic.scmemory.model.pattern.ScConstruction5;
import org.jmantic.scmemory.model.pattern.ScPattern3;
import org.jmantic.scmemory.model.pattern.ScPattern5;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.2.0
 */
public class AsyncUncheckedScContext {
    private final UncheckedScContext context;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public AsyncUncheckedScContext(UncheckedScContext context) {
        this.context = context;
    }

    public Future<ScNode> createNode(NodeType type) {
        return executorService.submit(() -> context.createNode(type));
    }

    public Future<Stream<ScNode>> createNodes(Stream<NodeType> types) {
        return executorService.submit(() -> context.createNodes(types));
    }

    public Future<ScEdge> createEdge(EdgeType type, ScElement source, ScElement target) {
        return executorService.submit(() -> context.createEdge(type, source, target));
    }

    public Future<Stream<ScEdge>> createEdges(Stream<EdgeType> types, Stream<? extends ScElement> source, Stream<? extends ScElement> target) {
        return executorService.submit(() -> context.createEdges(types, source, target));
    }

    public Future<ScLinkInteger> createIntegerLink(LinkType type, Integer content) {
        return executorService.submit(() -> context.createIntegerLink(type, content));
    }

    public Future<ScLinkFloat> createFloatLink(LinkType type, Float content) {
        return executorService.submit(() -> context.createFloatLink(type, content));
    }

    public Future<ScLinkString> createStringLink(LinkType type, String content) {
        return executorService.submit(() -> context.createStringLink(type, content));
    }

    public Future<Boolean> deleteElement(ScElement element) {
        return executorService.submit(() -> context.deleteElement(element));
    }

    public Future<Boolean> deleteElements(Stream<? extends ScElement> element) {
        return executorService.submit(() -> context.deleteElements(element));
    }

    @Deprecated
    public Future<Stream<? extends ScEdge>> findAllConstructionsNodeEdgeNode(ScNode fixedNode, EdgeType edge, NodeType node) {
        return executorService.submit(() -> context.findAllConstructionsNodeEdgeNode(fixedNode, edge, node));
    }

    public <t1 extends ScElement, t2, T3 extends ScElement>
    Future<Stream<? extends ScConstruction3<t1, T3>>> find(ScPattern3<t1, t2, T3> pattern) {
        return executorService.submit(() -> context.find(pattern));
    }

    public <t1 extends ScElement, t2, t3, T2 extends ScElement, T3 extends ScElement>
    Future<Stream<? extends ScConstruction5<t1, T2, T3>>> find(ScPattern5<t1, t2, t3, T2, T3> pattern) {
        return executorService.submit(() -> context.find(pattern));
    }

    public Future<Boolean> setIntegerLinkContent(ScLinkInteger link, Integer content) {
        return executorService.submit(() -> context.setIntegerLinkContent(link, content));
    }

    public Future<Boolean> setFloatLinkContent(ScLinkFloat link, Float content) {
        return executorService.submit(() -> context.setFloatLinkContent(link, content));
    }

    public Future<Boolean> setStringLinkContent(ScLinkString link, String content) {
        return executorService.submit(() -> context.setStringLinkContent(link, content));
    }

    public Future<Integer> getIntegerLinkContent(ScLinkInteger link) {
        return executorService.submit(() -> context.getIntegerLinkContent(link));
    }

    public Future<Float> getFloatLinkContent(ScLinkFloat link) {
        return executorService.submit(() -> context.getFloatLinkContent(link));
    }

    public Future<String> getStringLinkContent(ScLinkString link) {
        return executorService.submit(() -> context.getStringLinkContent(link));
    }

    public Future<Optional<? extends ScLinkString>> findKeynode(String idtf) throws ScMemoryException {
        return executorService.submit(() -> context.findKeynode(idtf));

    }

}
