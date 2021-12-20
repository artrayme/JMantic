package org.jmantic.api.context;

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
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.model.pattern.ScConstruction3;
import org.jmantic.scmemory.model.pattern.ScConstruction5;
import org.jmantic.scmemory.model.pattern.ScPattern3;
import org.jmantic.scmemory.model.pattern.ScPattern5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.0.1
 */
public class UncheckedScContext {
    private final static Logger logger = LoggerFactory.getLogger(UncheckedScContext.class);
    private final ScMemory memory;

    public UncheckedScContext(ScMemory memory) {
        this.memory = memory;
    }

    public ScNode createNode(NodeType type) {
        Optional<? extends ScElement> result;
        try {
            result = memory.createNodes(Stream.of(type)).findFirst();
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return (ScNode) result.get();
    }

    public Stream<ScNode> createNodes(Stream<NodeType> types) {
        Stream<? extends ScElement> result;
        try {
            result = memory.createNodes(types);
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result.map(e -> (ScNode) e);
    }

    public ScEdge createEdge(EdgeType type, ScElement source, ScElement target) {
        Optional<? extends ScElement> edge;
        try {
            edge = memory.createEdges(Stream.of(type), Stream.of(source), Stream.of(target)).findFirst();
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return (ScEdge) edge.get();
    }

    public Stream<ScEdge> createEdges(Stream<EdgeType> types, Stream<? extends ScElement> source, Stream<? extends ScElement> target) {
        Stream<ScEdge> scEdgeStream;
        try {
            scEdgeStream = memory.createEdges(types, source, target).map(e -> (ScEdge) e);
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return scEdgeStream;
    }

    public ScLinkInteger createIntegerLink(LinkType type, Integer content) {
        Optional<? extends ScLinkInteger> result;
        try {
            result = memory.createIntegerLinks(Stream.of(type), Stream.of(content)).findFirst();
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result.get();
    }

    public ScLinkFloat createFloatLink(LinkType type, Float content) {
        Optional<? extends ScLinkFloat> result;
        try {
            result = memory.createFloatLinks(Stream.of(type), Stream.of(content)).findFirst();
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result.get();
    }

    public ScLinkString createStringLink(LinkType type, String content) {
        Optional<? extends ScLinkString> result;
        try {
            result = memory.createStringLinks(Stream.of(type), Stream.of(content)).findFirst();
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result.get();
    }

    public Boolean deleteElement(ScElement element) {
        boolean result;
        try {
            result = memory.deleteElements(Stream.of(element));
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public Boolean deleteElements(Stream<? extends ScElement> elements) {
        boolean result;
        try {
            result = memory.deleteElements(elements);
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Deprecated
    public Stream<? extends ScEdge> findAllConstructionsNodeEdgeNode(ScNode fixedNode, EdgeType edge, NodeType node) {
        Stream<? extends ScEdge> result;
        try {
            result = memory.findByTemplateNodeEdgeNode(fixedNode, edge, node);
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public <t1 extends ScElement, t2, T3 extends ScElement>
    Stream<? extends ScConstruction3<t1, T3>> find(ScPattern3<t1, t2, T3> pattern) throws ScMemoryException {
        Stream<? extends ScConstruction3<t1, T3>> result;
        try {
            result = memory.findByPattern3(pattern);
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public <t1 extends ScElement, t2, t3, T2 extends ScElement, T3 extends ScElement>
    Stream<? extends ScConstruction5<t1, T2, T3>> find(ScPattern5<t1, t2, t3, T2, T3> pattern) {
        Stream<? extends ScConstruction5<t1, T2, T3>> result;
        try {
            result = memory.findByPattern5(pattern);
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public Boolean setIntegerLinkContent(ScLinkInteger link, Integer content) {
        Stream<Boolean> result;
        try {
            result = memory.setIntegerLinkContent(Stream.of(link), Stream.of(content));
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result.findFirst().get();
    }

    public Boolean setFloatLinkContent(ScLinkFloat link, Float content) {
        Stream<Boolean> result;
        try {
            result = memory.setFloatLinkContent(Stream.of(link), Stream.of(content));
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result.findFirst().get();
    }

    public Boolean setStringLinkContent(ScLinkString link, String content) {
        Stream<Boolean> result;
        try {
            result = memory.setStringLinkContent(Stream.of(link), Stream.of(content));
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result.findFirst().get();
    }

    //    Uncomment when the project updated to java with a pattern matching(((
    //    public <T> Boolean setAnyLinkContent(ScLink link, T content) {
    //        Stream<Boolean> result = Stream.empty();
    //        try {
    //            result = switch (content) {
    //                case String str -> {
    //                    if (!link.getContentType().equals(LinkContentType.STRING))
    //                        throw new IllegalArgumentException("You should pass link with String content type");
    //                    memory.setStringLinkContent(Stream.of((ScLinkString) link), Stream.of(str));
    //                }
    //                case Integer integer -> {
    //                    if (!link.getContentType().equals(LinkContentType.INTEGER))
    //                        throw new IllegalArgumentException("You should pass link with Integer content type");
    //                    memory.setIntegerLinkContent(Stream.of((ScLinkInteger) link), Stream.of(integer));
    //                }
    //                case Float flt -> {
    //                    if (!link.getContentType().equals(LinkContentType.FLOAT))
    //                        throw new IllegalArgumentException("You should pass link with Float content type");
    //                    memory.setFloatLinkContent(Stream.of((ScLinkFloat) link), Stream.of(flt));
    //                }
    //            };
    //        } catch (ScMemoryException e) {
    //            e.printStackTrace();
    //        }
    //        return result.findFirst().get();
    //    }

    public Integer getIntegerLinkContent(ScLinkInteger link) {
        Stream<Integer> result;
        try {
            result = memory.getIntegerLinkContent(Stream.of(link));
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result.findFirst().get();
    }

    public Float getFloatLinkContent(ScLinkFloat link) {
        Stream<Float> result;
        try {
            result = memory.getFloatLinkContent(Stream.of(link));
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result.findFirst().get();
    }

    public String getStringLinkContent(ScLinkString link) {
        Stream<String> result;
        try {
            result = memory.getStringLinkContent(Stream.of(link));
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result.findFirst().get();
    }

    public Optional<? extends ScLinkString> findKeynode(String idtf) {
        Optional<? extends ScLinkString> result;
        try {
            result = memory.findKeynodes(Stream.of(idtf)).findFirst().get();
        } catch (ScMemoryException e) {
            logger.error("It's really bad", e);
            throw new RuntimeException(e);
        }
        return result;
    }

}
