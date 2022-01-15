package org.ostis.scmemory.model.pattern.element;


/**
 * This class represents one of the {@link ScPatternElement} classes.
 * You can use this class if you have sc-type and want to find all sc-elements of this type by a provided pattern.
 * Also, you can set alias for this element.
 * This feature helps you to use typed pattern-element as fixed.
 * For example, you want to find construction by pattern5:
 * <pre>
 *     {@code
 *                      ?edge2
 *         element1----------------------------->?element3
 *                                  ^
 *                                  |
 *                                  | ?edge4
 *                                  |
 *                                  |
 *                              element5
 *     }
 * </pre>
 * <p>
 * But how to connect target of edge4 and edge2 if they both are typed?
 * For that propose you can use the alias:
 * <pre>
 * {@code
 *         ScAliasedElement edge2Alias = new AliasPatternElement("edge_2");
 *         ScPatternTriplet triple = new BasicPatternTriple(
 *                 new FixedPatternElement(...),
 *                 new TypePatternElement<>(..., edge2Alias),
 *                 new TypePatternElement<>(..., new AliasPatternElement("element_3"))
 *         );
 *         ScPatternTriplet relTriple = new BasicPatternTriple(
 *                 new TypePatternElement<>(..., new AliasPatternElement("element_5"))
 *                 new TypePatternElement<>(..., new AliasPatternElement("edge_4")),
 *                 edge2Alias
 *         );
 * }
 * </pre>
 *
 * @author artrayme
 * @since 0.6.0
 */

public interface ScTypedElement<T> extends ScPatternElement {
    /**
     * @return searched type.
     * You can use {@link org.ostis.scmemory.model.element.node.NodeType},
     * {@link org.ostis.scmemory.model.element.edge.EdgeType},
     * {@link org.ostis.scmemory.model.element.link.LinkType},
     * {@link  org.ostis.scmemory.model.element.UnknownScElement}.
     */
    T getValue();

    /**
     * @return alias for this element.
     * @see ScAliasedElement
     */
    ScAliasedElement getAlias();

    @Override
    default PatternElement getType() {
        return PatternElement.TYPE;
    }
}
