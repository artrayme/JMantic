package org.jmantic.scmemory.model.pattern.factory;

import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.pattern.ScPattern3;
import org.jmantic.scmemory.model.pattern.pattern3.ScPattern3Impl;

public class DefaultScPattern3Factory {
    public static ScPattern3<ScNode, NodeType, ScNode> get(ScNode node1, EdgeType edgeType, NodeType nodeType) {
        return new ScPattern3Impl<>(node1, edgeType, nodeType);
    }

    public static ScPattern3<ScNode, LinkType, ScLink> get(ScNode node1, EdgeType edgeType, LinkType linkType) {
        return new ScPattern3Impl<>(node1, edgeType, linkType);
    }

    public static ScPattern3<ScLink, NodeType, ScNode> get(ScLink link1, EdgeType edgeType, NodeType nodeType) {
        return new ScPattern3Impl<>(link1, edgeType, nodeType);
    }

    public static ScPattern3<ScLink, LinkType, ScLink> get(ScLink link1, EdgeType edgeType, LinkType linkType) {
        return new ScPattern3Impl<>(link1, edgeType, linkType);
    }

    public static ScPattern3<ScNode, ScNode, ScNode> get(ScNode node1, EdgeType edgeType, ScNode node3) {
        return new ScPattern3Impl<>(node1, edgeType, node3);
    }

    public static ScPattern3<ScNode, ScLink, ScLink> get(ScNode node1, EdgeType edgeType, ScLink link3) {
        return new ScPattern3Impl<>(node1, edgeType, link3);
    }

    public static ScPattern3<ScLink, ScNode, ScNode> get(ScLink link1, EdgeType edgeType, ScNode node3) {
        return new ScPattern3Impl<>(link1, edgeType, node3);
    }

    public static ScPattern3<ScLink, ScLink, ScLink> get(ScLink link1, EdgeType edgeType, ScLink link3) {
        return new ScPattern3Impl<>(link1, edgeType, link3);
    }

}
