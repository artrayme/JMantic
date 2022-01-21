package org.ostis.scmemory.model.pattern.factory;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLink;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.pattern.pattern3.ScPattern3;
import org.ostis.scmemory.model.pattern.pattern3.ScPattern3Impl;

/**
 * This factory helps to create search templates for 3-elements sc-constructions.
 * <p></p>
 * Common structure of request is:
 * <pre>
 *     {@code
 *                          edge2
 *         element1-----------------------element3
 *     }
 * </pre>
 * Where:
 * <ul>
 *     <li>
 *         element1 has type of {@link org.ostis.scmemory.model.element.node.ScNode}
 *         or {@link org.ostis.scmemory.model.element.link.ScLink}
 *     </li>
 *     <li>
 *         edge2 has type {@link org.ostis.scmemory.model.element.edge.EdgeType}
 *     </li>
 *     <li>
 *         element3 can be {@link org.ostis.scmemory.model.element.node.ScNode},
 *         {@link org.ostis.scmemory.model.element.link.ScLink},
 *         {@link org.ostis.scmemory.model.element.node.NodeType}
 *         or {@link org.ostis.scmemory.model.element.link.LinkType}
 *     </li>
 * </ul>
 *
 * @author artrayme
 * @since 0.3.2
 */

public class ScPattern3FactoryWithNames {

    private ScPattern3FactoryWithNames() {
    }

    public static ScPattern3<ScNode, NodeType, ScNode> getFNodeAEdgeANodePattern(ScNode node1,
                                                                                 EdgeType edgeType,
                                                                                 NodeType nodeType) {
        return new ScPattern3Impl<>(
                node1,
                edgeType,
                nodeType);
    }

    public static ScPattern3<ScNode, ScNode, ScNode> getFNodeAEdgeFNodePattern(ScNode node1,
                                                                               EdgeType edgeType,
                                                                               ScNode node3) {
        return new ScPattern3Impl<>(
                node1,
                edgeType,
                node3);
    }

    public static ScPattern3<ScNode, LinkType, ScLink> getFNodeAEdgeALinkPattern(ScNode node1,
                                                                                 EdgeType edgeType,
                                                                                 LinkType linkType) {
        return new ScPattern3Impl<>(
                node1,
                edgeType,
                linkType);
    }

    public static ScPattern3<ScNode, ScLink, ScLink> getFNodeAEdgeFLinkPattern(ScNode node1,
                                                                               EdgeType edgeType,
                                                                               ScLink link3) {
        return new ScPattern3Impl<>(
                node1,
                edgeType,
                link3);
    }

    public static ScPattern3<ScLink, NodeType, ScNode> getFLinkAEdgeANodePattern(ScLink link1,
                                                                                 EdgeType edgeType,
                                                                                 NodeType nodeType) {
        return new ScPattern3Impl<>(
                link1,
                edgeType,
                nodeType);
    }

    public static ScPattern3<ScLink, ScNode, ScNode> getFLinkAEdgeFNodePattern(ScLink link1,
                                                                               EdgeType edgeType,
                                                                               ScNode node3) {
        return new ScPattern3Impl<>(
                link1,
                edgeType,
                node3);
    }

    public static ScPattern3<ScLink, LinkType, ScLink> getFLinkAEdgeALinkPattern(ScLink link1,
                                                                                 EdgeType edgeType,
                                                                                 LinkType linkType) {
        return new ScPattern3Impl<>(
                link1,
                edgeType,
                linkType);
    }

    public static ScPattern3<ScLink, ScLink, ScLink> getFLinkAEdgeFLinkPattern(ScLink link1,
                                                                               EdgeType edgeType,
                                                                               ScLink link3) {
        return new ScPattern3Impl<>(
                link1,
                edgeType,
                link3);
    }

}
