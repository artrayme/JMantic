package org.jmantic.scmemory.model.pattern.pattern3;

import org.jmantic.scmemory.model.element.edge.EdgeType;
import org.jmantic.scmemory.model.element.link.LinkType;
import org.jmantic.scmemory.model.element.link.ScLink;
import org.jmantic.scmemory.model.element.node.NodeType;
import org.jmantic.scmemory.model.element.node.ScNode;
import org.jmantic.scmemory.model.pattern.ScPattern3;

public class ScPattern3Factory {

    public static ScPattern3<ScNode, NodeType, ScNode> getFAA(ScNode node1, EdgeType edgeType, NodeType nodeType) {
        return new ScPattern3Impl<>(node1, edgeType, nodeType);
    }

    public static ScPattern3<ScNode, LinkType, ScLink> getFAA(ScNode node1, EdgeType edgeType, LinkType linkType) {
        return new ScPattern3Impl<>(node1, edgeType, linkType);
    }

    public static ScPattern3<ScLink, NodeType, ScNode> getFAA(ScLink link1, EdgeType edgeType, NodeType nodeType) {
        return new ScPattern3Impl<>(link1, edgeType, nodeType);
    }

    public static ScPattern3<ScLink, LinkType, ScLink> getFAA(ScLink link1, EdgeType edgeType, LinkType linkType) {
        return new ScPattern3Impl<>(link1, edgeType, linkType);
    }

    public static ScPattern3<ScNode, ScNode, ScNode> getFAF(ScNode node1, EdgeType edgeType, ScNode node3) {
        return new ScPattern3Impl<>(node1, edgeType, node3);
    }

    public static ScPattern3<ScNode, ScLink, ScLink> getFAF(ScNode node1, EdgeType edgeType, ScLink link3) {
        return new ScPattern3Impl<>(node1, edgeType, link3);
    }

    public static ScPattern3<ScLink, ScNode, ScNode> getFAF(ScLink link1, EdgeType edgeType, ScNode node3) {
        return new ScPattern3Impl<>(link1, edgeType, node3);
    }

    public static ScPattern3<ScLink, ScLink, ScLink> getFAF(ScLink link1, EdgeType edgeType, ScLink link3) {
        return new ScPattern3Impl<>(link1, edgeType, link3);
    }

    public static ScPattern3<ScNode, NodeType, ScNode> getFNodeAEdgeANodePattern(ScNode node1, EdgeType edgeType, NodeType nodeType) {
        return new ScPattern3Impl<>(node1, edgeType, nodeType);
    }

    public static ScPattern3<ScNode, ScNode, ScNode> getFNodeAEdgeFNodePattern(ScNode node1, EdgeType edgeType, ScNode node3) {
        return new ScPattern3Impl<>(node1, edgeType, node3);
    }

    public static ScPattern3<ScNode, LinkType, ScLink> getFNodeAEdgeALinkPattern(ScNode node1, EdgeType edgeType, LinkType linkType) {
        return new ScPattern3Impl<>(node1, edgeType, linkType);
    }

    public static ScPattern3<ScNode, ScLink, ScLink> getFNodeAEdgeFLinkPattern(ScNode node1, EdgeType edgeType, ScLink link3) {
        return new ScPattern3Impl<>(node1, edgeType, link3);
    }

    public static ScPattern3<ScLink, NodeType, ScNode> getFLinkAEdgeANodePattern(ScLink link1, EdgeType edgeType, NodeType nodeType) {
        return new ScPattern3Impl<>(link1, edgeType, nodeType);
    }

    public static ScPattern3<ScLink, ScNode, ScNode> getFLinkAEdgeFNodePattern(ScLink link1, EdgeType edgeType, ScNode node3) {
        return new ScPattern3Impl<>(link1, edgeType, node3);
    }

    public static ScPattern3<ScLink, LinkType, ScLink> getFLinkAEdgeALinkPattern(ScLink link1, EdgeType edgeType, LinkType linkType) {
        return new ScPattern3Impl<>(link1, edgeType, linkType);
    }

    public static ScPattern3<ScLink, ScLink, ScLink> getFLinkAEdgeFLinkPattern(ScLink link1, EdgeType edgeType, ScLink link3) {
        return new ScPattern3Impl<>(link1, edgeType, link3);
    }

}
