package org.jmantic.scmemory.websocket.model;


/**
 * This class store elementary parts of the {@link PayloadPart}.
 * Each BasicConstruction represents one sc-element.
 * Examples:
 * <pre>{@code
 *    {
 *       "el": "node",
 *       "type": 1
 *     },
 *     {
 *       "el": "link",
 *       "type": 2,
 *       "content": 45.4
 *     },
 *     {
 *       "type": "type",
 *       "value": 32,
 *       "alias": "_edge1"
 *     },
 * }</pre>
 *
 * @author artrayme
 * @since 0.0.1
 */
public interface BasicConstruction {
    String getJson();
}
