package org.ostis.api.context;

import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLinkFloat;
import org.ostis.scmemory.model.element.link.ScLinkInteger;
import org.ostis.scmemory.model.element.link.ScLinkString;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.model.pattern.ScPattern;
import org.ostis.scmemory.model.pattern.pattern3.ScConstruction3;
import org.ostis.scmemory.model.pattern.pattern3.ScPattern3;
import org.ostis.scmemory.model.pattern.pattern5.ScConstruction5;
import org.ostis.scmemory.model.pattern.pattern5.ScPattern5;
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

    public ScMemory getMemory() {
        return memory;
    }

    /**
     * Node creating.
     * This method creates a node in sc-memory with the specified type.
     *
     * @param type type of the node.
     * @return Some implementation of ScNode, which is linked with the corresponding sc-memory.
     */
    public ScNode createNode(NodeType type) {
        Optional<? extends ScElement> result;
        try {
            result = memory.createNodes(Stream.of(type))
                           .findFirst();
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return (ScNode) result.get();
    }

    /**
     * Nodes creating.
     * This method creates multiple nodes in sc-memory with the specified types.
     * If you want to create multiple nodes, this method will be more efficient than {@link #createNode(NodeType)}.
     *
     * @param types stream of node types.
     * @return Stream of some implementation of ScNode, which is linked with the corresponding sc-memory.
     */
    public Stream<ScNode> createNodes(Stream<NodeType> types) {
        Stream<? extends ScElement> result;
        try {
            result = memory.createNodes(types);
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result.map(e -> (ScNode) e);
    }

    /**
     * Edge creating.
     * This method creates an edge in sc-memory with the specified type and between two non-null nodes
     *
     * @param type   type of the edge.
     * @param source edge source.
     * @param target edge target.
     * @return Some implementation of ScEdge, which is linked with the corresponding sc-memory.
     */
    public ScEdge createEdge(EdgeType type, ScElement source, ScElement target) {
        Optional<? extends ScElement> edge;
        try {
            edge = memory.createEdges(
                                 Stream.of(type),
                                 Stream.of(source),
                                 Stream.of(target))
                         .findFirst();
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return (ScEdge) edge.get();
    }

    /**
     * Edges creating.
     * This method creates multiple edges in sc-memory with the specified types, sources and targets.
     * If you want to create multiple edges, this method will be more efficient than {@link #createEdges(Stream, Stream, Stream)}.
     * <p>
     * Edge[n] will be created between sources[n] and targets[n] with types[n].
     *
     * @param types   type of the edge.
     * @param sources edge sources.
     * @param targets edge targets.
     * @return Stream of some implementation of ScEdge, which is linked with the corresponding sc-memory.
     */
    public Stream<ScEdge> createEdges(Stream<EdgeType> types,
                                      Stream<? extends ScElement> sources,
                                      Stream<? extends ScElement> targets) {
        Stream<ScEdge> scEdgeStream;
        try {
            scEdgeStream = memory.createEdges(
                                         types,
                                         sources,
                                         targets)
                                 .map(e -> (ScEdge) e);
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return scEdgeStream;
    }

    /**
     * Link with integer content creating.
     * This method creates a link in sc-memory with the specified type and integer content.
     *
     * @param type    type of the link.
     * @param content integer content of the link.
     * @return Some implementation of ScLinkInteger, that is linked with the corresponding sc-memory.
     */
    public ScLinkInteger createIntegerLink(LinkType type, Integer content) {
        Optional<? extends ScLinkInteger> result;
        try {
            result = memory.createIntegerLinks(
                                   Stream.of(type),
                                   Stream.of(content))
                           .findFirst();
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result.get();
    }

    /**
     * Link with float content creating.
     * This method creates a link in sc-memory with the specified type and float content.
     *
     * @param type    type of the link.
     * @param content float content of the link.
     * @return Some implementation of ScLinkFloat, which is linked with the corresponding sc-memory.
     */
    public ScLinkFloat createFloatLink(LinkType type, Float content) {
        Optional<? extends ScLinkFloat> result;
        try {
            result = memory.createFloatLinks(
                                   Stream.of(type),
                                   Stream.of(content))
                           .findFirst();
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result.get();
    }

    /**
     * Link with string content creating.
     * This method creates a link in sc-memory with the specified type and string content.
     *
     * @param type    type of the link.
     * @param content string content of the link.
     * @return Some implementation of ScLinkString, which is linked with the corresponding sc-memory.
     */
    public ScLinkString createStringLink(LinkType type, String content) {
        Optional<? extends ScLinkString> result;
        try {
            result = memory.createStringLinks(
                                   Stream.of(type),
                                   Stream.of(content))
                           .findFirst();
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result.get();
    }

    /**
     * Element deleting
     * This method removes the sc-element from the sc-memory.
     *
     * @param element is the item you want to delete.
     * @return true when executed successfully
     */
    public Boolean deleteElement(ScElement element) {
        boolean result;
        try {
            result = memory.deleteElements(Stream.of(element));
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Elements deleting
     * This method remove the sc-elements from the sc-memory.
     *
     * @param elements stream of items to be deleted.
     * @return true when executed successfully.
     */
    public Boolean deleteElements(Stream<? extends ScElement> elements) {
        boolean result;
        try {
            result = memory.deleteElements(elements);
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Construction search.
     * This method searches for all 3-element constructions by pattern.
     * Also, you can use {@link #find(ScPattern)} if more complex or not standard pattens needed.
     *
     * @param pattern the pattern to be searched for
     * @return stream of found constructions.
     */
    public <t1 extends ScElement, t2, T3 extends ScElement> Stream<? extends ScConstruction3<t1, T3>> find(ScPattern3<t1, t2, T3> pattern) {
        Stream<? extends ScConstruction3<t1, T3>> result;
        try {
            result = memory.findByPattern3(pattern);
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Construction search.
     * This method searches for all 5-element constructions by pattern.
     * Also, you can use {@link #find(ScPattern)} if more complex or not standard pattens needed.
     *
     * @param pattern the pattern to be searched for
     * @return stream of found constructions.
     */
    public <t1 extends ScElement, t2, t3, T2 extends ScElement, T3 extends ScElement> Stream<? extends ScConstruction5<t1, T2, T3>> find(
            ScPattern5<t1, t2, t3, T2, T3> pattern) {
        Stream<? extends ScConstruction5<t1, T2, T3>> result;
        try {
            result = memory.findByPattern5(pattern);
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Construction search.
     * This method searches for all constructions by pattern.
     * This method is universal and can be used for all types of constructions.
     * But, abstract patterns are hard to create.
     * If you want
     * to use some common pattern, these methods can help you:
     * {@link  #find(ScPattern3)}, {@link #find(ScPattern5)}
     *
     * @param pattern the pattern to be searched for
     * @return stream of found constructions. Each inner stream represents one found construction.
     */
    public Stream<Stream<? extends ScElement>> find(ScPattern pattern) {
        Stream<Stream<? extends ScElement>> result;
        try {
            result = memory.find(pattern);
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Link integer content setting.
     * This method sets the content to sc-link.
     *
     * @param link    target link.
     * @param content integer content.
     * @return true when executed successfully.
     */
    public Boolean setIntegerLinkContent(ScLinkInteger link, Integer content) {
        Stream<Boolean> result;
        try {
            result = memory.setIntegerLinkContent(
                    Stream.of(link),
                    Stream.of(content));
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result.findFirst()
                     .get();
    }

    /**
     * Link float content setting.
     * This method sets the content to sc-link.
     *
     * @param link    target link.
     * @param content float content.
     * @return true when executed successfully.
     */
    public Boolean setFloatLinkContent(ScLinkFloat link, Float content) {
        Stream<Boolean> result;
        try {
            result = memory.setFloatLinkContent(
                    Stream.of(link),
                    Stream.of(content));
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result.findFirst()
                     .get();
    }

    /**
     * Link string content setting.
     * This method sets the content to sc-link.
     *
     * @param link    target link.
     * @param content string content.
     * @return true when executed successfully.
     */
    public Boolean setStringLinkContent(ScLinkString link, String content) {
        Stream<Boolean> result;
        try {
            result = memory.setStringLinkContent(
                    Stream.of(link),
                    Stream.of(content));
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result.findFirst()
                     .get();
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

    /**
     * Integer link content getter.
     * This method gets the link content from sc-memory.
     *
     * @param link target link.
     * @return link content
     */
    public Integer getIntegerLinkContent(ScLinkInteger link) {
        Stream<Integer> result;
        try {
            result = memory.getIntegerLinkContent(Stream.of(link));
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result.findFirst()
                     .get();
    }

    /**
     * Float link content getter.
     * This method gets the link content from sc-memory.
     *
     * @param link target link.
     * @return link content
     */
    public Float getFloatLinkContent(ScLinkFloat link) {
        Stream<Float> result;
        try {
            result = memory.getFloatLinkContent(Stream.of(link));
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result.findFirst()
                     .get();
    }

    /**
     * String link content getter.
     * This method gets the link content from sc-memory.
     *
     * @param link - target link.
     * @return link content
     */
    public String getStringLinkContent(ScLinkString link) {
        Stream<String> result;
        try {
            result = memory.getStringLinkContent(Stream.of(link));
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result.findFirst()
                     .get();
    }

    /**
     * Keynodes finder.
     * This method finds keynode with a specific identifier.
     *
     * @param idtf identifier of node.
     * @return Optional of node. If node with a passed identifier exists - optional will be present.
     */
    public Optional<? extends ScNode> findKeynode(String idtf) {
        Optional<? extends ScNode> result;
        try {
            result = memory.findKeynodes(Stream.of(idtf))
                           .findFirst()
                           .get();
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Keynodes resolver.
     * This method resolves keynode with a specific identifier.
     *
     * @param idtf identifier of node.
     * @param type type of node that will be created
     * @return resolved node. If node with identifier does not exist - a new node will be created.
     */
    public ScNode resolveKeynode(String idtf, NodeType type) {
        ScNode result;
        try {
            result = memory.resolveKeynodes(
                                   Stream.of(idtf),
                                   Stream.of(type))
                           .findFirst()
                           .get();
        } catch (ScMemoryException e) {
            logger.error(
                    "It's really bad",
                    e);
            throw new RuntimeException(e);
        }
        return result;
    }
}
