package org.jmantic.scmemory.model.pattern.factory;

import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.pattern.ScPattern5;
import org.jmantic.scmemory.model.pattern.pattern5.ScPattern5Impl;

public class DefaultScPattern5Factory {
    public static ScPattern5<ScNode, NodeType, ScNode, ScNode, ScNode> get(ScNode node1,
                                                                           EdgeType edgeType,
                                                                           NodeType nodeType,
                                                                           EdgeType relEdgeType,
                                                                           ScNode relNode) {
        return new ScPattern5Impl<>(node1, edgeType, nodeType, relEdgeType, relNode);
    }

    public static ScPattern5<ScNode, LinkType, ScNode, ScLink, ScNode> get(ScNode node1,
                                                                           EdgeType edgeType,
                                                                           LinkType linkType,
                                                                           EdgeType relEdgeType,
                                                                           ScNode relNode) {
        return new ScPattern5Impl<>(node1, edgeType, linkType, relEdgeType, relNode);
    }

    public static ScPattern5<ScNode, NodeType, ScLink, ScNode, ScLink> get(ScNode node1,
                                                                           EdgeType edgeType,
                                                                           NodeType nodeType,
                                                                           EdgeType relEdgeType,
                                                                           ScLink relLink) {
        return new ScPattern5Impl<>(node1, edgeType, nodeType, relEdgeType, relLink);
    }

    public static ScPattern5<ScNode, LinkType, ScLink, ScLink, ScLink> get(ScNode node1,
                                                                           EdgeType edgeType,
                                                                           LinkType linkType,
                                                                           EdgeType relEdgeType,
                                                                           ScLink relLink) {
        return new ScPattern5Impl<>(node1, edgeType, linkType, relEdgeType, relLink);
    }

    public static ScPattern5<ScNode, ScNode, NodeType, ScNode, ScNode> get(ScNode node1,
                                                                           EdgeType edgeType,
                                                                           ScNode nodeType,
                                                                           EdgeType relEdgeType,
                                                                           NodeType relNode) {
        return new ScPattern5Impl<>(node1, edgeType, nodeType, relEdgeType, relNode);
    }

    public static ScPattern5<ScNode, ScLink, NodeType, ScLink, ScNode> get(ScNode node1,
                                                                           EdgeType edgeType,
                                                                           ScLink linkType,
                                                                           EdgeType relEdgeType,
                                                                           NodeType relNode) {
        return new ScPattern5Impl<>(node1, edgeType, linkType, relEdgeType, relNode);
    }

    public static ScPattern5<ScNode, ScNode, LinkType, ScNode, ScLink> get(ScNode node1,
                                                                           EdgeType edgeType,
                                                                           ScNode nodeType,
                                                                           EdgeType relEdgeType,
                                                                           LinkType relLink) {
        return new ScPattern5Impl<>(node1, edgeType, nodeType, relEdgeType, relLink);
    }

    public static ScPattern5<ScNode, ScLink, LinkType, ScLink, ScLink> get(ScNode node1,
                                                                           EdgeType edgeType,
                                                                           ScLink linkType,
                                                                           EdgeType relEdgeType,
                                                                           LinkType relLink) {
        return new ScPattern5Impl<>(node1, edgeType, linkType, relEdgeType, relLink);
    }

    public static ScPattern5<ScLink, NodeType, ScNode, ScNode, ScNode> get(ScLink node1,
                                                                           EdgeType edgeType,
                                                                           NodeType nodeType,
                                                                           EdgeType relEdgeType,
                                                                           ScNode relNode) {
        return new ScPattern5Impl<>(node1, edgeType, nodeType, relEdgeType, relNode);
    }

    public static ScPattern5<ScLink, LinkType, ScNode, ScLink, ScNode> get(ScLink node1,
                                                                           EdgeType edgeType,
                                                                           LinkType linkType,
                                                                           EdgeType relEdgeType,
                                                                           ScNode relNode) {
        return new ScPattern5Impl<>(node1, edgeType, linkType, relEdgeType, relNode);
    }

    public static ScPattern5<ScLink, NodeType, ScLink, ScNode, ScLink> get(ScLink node1,
                                                                           EdgeType edgeType,
                                                                           NodeType nodeType,
                                                                           EdgeType relEdgeType,
                                                                           ScLink relLink) {
        return new ScPattern5Impl<>(node1, edgeType, nodeType, relEdgeType, relLink);
    }

    public static ScPattern5<ScLink, LinkType, ScLink, ScLink, ScLink> get(ScLink node1,
                                                                           EdgeType edgeType,
                                                                           LinkType linkType,
                                                                           EdgeType relEdgeType,
                                                                           ScLink relLink) {
        return new ScPattern5Impl<>(node1, edgeType, linkType, relEdgeType, relLink);
    }

    public static ScPattern5<ScLink, ScNode, NodeType, ScNode, ScNode> get(ScLink node1,
                                                                           EdgeType edgeType,
                                                                           ScNode nodeType,
                                                                           EdgeType relEdgeType,
                                                                           NodeType relNode) {
        return new ScPattern5Impl<>(node1, edgeType, nodeType, relEdgeType, relNode);
    }

    public static ScPattern5<ScLink, ScLink, NodeType, ScLink, ScNode> get(ScLink node1,
                                                                           EdgeType edgeType,
                                                                           ScLink linkType,
                                                                           EdgeType relEdgeType,
                                                                           NodeType relNode) {
        return new ScPattern5Impl<>(node1, edgeType, linkType, relEdgeType, relNode);
    }

    public static ScPattern5<ScLink, ScNode, LinkType, ScNode, ScLink> get(ScLink node1,
                                                                           EdgeType edgeType,
                                                                           ScNode nodeType,
                                                                           EdgeType relEdgeType,
                                                                           LinkType relLink) {
        return new ScPattern5Impl<>(node1, edgeType, nodeType, relEdgeType, relLink);
    }

    public static ScPattern5<ScLink, ScLink, LinkType, ScLink, ScLink> get(ScLink node1,
                                                                           EdgeType edgeType,
                                                                           ScLink linkType,
                                                                           EdgeType relEdgeType,
                                                                           LinkType relLink) {
        return new ScPattern5Impl<>(node1, edgeType, linkType, relEdgeType, relLink);
    }
}
