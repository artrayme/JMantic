package org.ostis.scmemory.model.element.link;

import org.ostis.scmemory.model.element.ScElement;

/**
 * This class describes any sc-link in the sc-machine.
 * ScLink is designed to store not-sc information in sc-machine,
 * like numbers and strings. At current moment you can create 4 sc-links with different content type:
 * <ul>
 *     <li>integer -- {@link ScLinkInteger}</li>
 *     <li>float -- {@link ScLinkFloat}</li>
 *     <li>string -- {@link ScLinkString}</li>
 *     <li>binary -- {@link ScLinkBinary}</li>
 * </ul>
 * <p>
 *     Also, like other sc-elements, sc-link has an address and a sc-type.
 * </p>
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface ScLink extends ScElement {
    LinkType getType();

    LinkContentType getContentType();
}
