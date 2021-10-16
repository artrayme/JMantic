package org.jmantic.model;

import java.util.stream.Stream;

/**
 * The {@code PayloadPart} class represents the structure of payloads.
 * {@code PayloadPart} is a part of request. Each request can be consisted of many {@code PayloadParts}.
 * {@code PayloadPart} describe sequence of basic tasks for sc-machine.
 * Example:
 *  * <pre>{@code
 *  *
 *  * }</pre>
 *
 * @author artrayme
 * @since 0.0.1
 */

public interface PayloadPart {
    Stream<BasicConstruction> getConstructions();
}
