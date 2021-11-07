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
        Optional<? extends ScElement> result = Optional.empty();
        try {
            result = memory.createNodes(Stream.of(type)).findFirst();
        } catch (ScMemoryException e) {
            //            ToDo logger
            e.printStackTrace();
        }
        return (ScNode) result.get();
    }

    public Stream<ScNode> createNodes(Stream<NodeType> types) {
        Stream<? extends ScElement> result = Stream.empty();
        try {
            result = memory.createNodes(types);
        } catch (ScMemoryException e) {
            //            ToDO logger
            e.printStackTrace();
        }
        return result.map(e -> (ScNode) e);
    }

    public ScEdge createEdge(EdgeType type, ScElement source, ScElement target) {
        Optional<? extends ScElement> edge = Optional.empty();
        try {
            edge = memory.createEdges(Stream.of(type), Stream.of(source), Stream.of(target)).findFirst();
        } catch (ScMemoryException e) {
            //            ToDo logger
            e.printStackTrace();
        }
        return (ScEdge) edge.get();
    }

    public Stream<ScEdge> createEdges(Stream<EdgeType> types, Stream<? extends ScElement> source, Stream<? extends ScElement> target) {
        Stream<ScEdge> scEdgeStream = Stream.empty();
        try {
            scEdgeStream = memory.createEdges(types, source, target).map(e -> (ScEdge) e);
        } catch (ScMemoryException e) {
            //            ToDo logger
            e.printStackTrace();
        }
        return scEdgeStream;
    }

    public ScLinkInteger createIntegerLink(LinkType type, Integer content) {
        Optional<? extends ScLinkInteger> result = Optional.empty();
        try {
            result = memory.createIntegerLink(Stream.of(type), Stream.of(content)).findFirst();
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.get();
    }

    public ScLinkFloat createFloatLink(LinkType type, Float content) {
        Optional<? extends ScLinkFloat> result = Optional.empty();
        try {
            result = memory.createFloatLink(Stream.of(type), Stream.of(content)).findFirst();
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.get();
    }

    public ScLinkString createStringLink(LinkType type, String content) {
        Optional<? extends ScLinkString> result = Optional.empty();
        try {
            result = memory.createStringLink(Stream.of(type), Stream.of(content)).findFirst();
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.get();
    }

    //    public ScLinkBinary createBinaryLink(LinkType type, String content) {
    //        Optional<? extends ScLinkBinary> result = Optional.empty();
    //        try {
    //            result = memory.createBinaryLink(Stream.of(type), Stream.of(content)).findFirst();
    //        } catch (ScMemoryException e) {
    //            e.printStackTrace();
    //        }
    //        return result.get();
    //    }

    public boolean deleteElement(ScElement element) {
        boolean result = false;
        try {
            result = memory.deleteElements(Stream.of(element));
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Stream<? extends ScEdge> findAllConstructionsNodeEdgeNode(ScNode fixedNode, EdgeType edge, NodeType node) {
        Stream<? extends ScEdge> result = Stream.empty();
        try {
            result = memory.findByTemplateNodeEdgeNode(fixedNode, edge, node);
        } catch (ScMemoryException e) {
            //            ToDo logger
            e.printStackTrace();
        }
        return result;
    }

    public Boolean setIntegerLinkContent(ScLinkInteger link, Integer content) {
        Stream<Boolean> result = Stream.empty();
        try {
            result = memory.setIntegerLinkContent(Stream.of(link), Stream.of(content));
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.findFirst().get();
    }

    public Boolean setFloatLinkContent(ScLinkFloat link, Float content) {
        Stream<Boolean> result = Stream.empty();
        try {
            result = memory.setFloatLinkContent(Stream.of(link), Stream.of(content));
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.findFirst().get();
    }

    public Boolean setStringLinkContent(ScLinkString link, String content) {
        Stream<Boolean> result = Stream.empty();
        try {
            result = memory.setStringLinkContent(Stream.of(link), Stream.of(content));
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.findFirst().get();
    }

    //    Uncomment when the project updated to java 17
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
        Stream<Integer> result = Stream.empty();
        try {
            result = memory.getIntegerLinkContent(Stream.of(link));
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.findFirst().get();
    }

    public Float getFloatLinkContent(ScLinkFloat link) {
        Stream<Float> result = Stream.empty();
        try {
            result = memory.getFloatLinkContent(Stream.of(link));
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.findFirst().get();
    }

    public String getStringLinkContent(ScLinkString link) {
        Stream<String> result = Stream.empty();
        try {
            result = memory.getStringLinkContent(Stream.of(link));
        } catch (ScMemoryException e) {
            e.printStackTrace();
        }
        return result.findFirst().get();
    }
}
