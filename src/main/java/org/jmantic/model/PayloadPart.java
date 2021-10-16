package org.jmantic.model;

import java.util.stream.Stream;

/**
 * The {@code PayloadPart} class represents the structure of payloads.
 * {@code PayloadPart} is a part of request. Each request can be consisted of many {@code PayloadParts}.
 * {@code PayloadPart} describe sequence of basic functions for sc-machine.
 * PayloadPart content depends on {@link RequestType}.
 *
 * Examples:
 * <pre>{@code
 *  "payload": [
 *     [
 *       {
 *         "type": "addr",
 *         "value": 23123
 *       },
 *       {
 *         "type": "type",
 *         "value": 32,
 *         "alias": "_edge1"
 *       },
 *       {
 *         "type": "type",
 *         "value": 2,
 *         "alias": "_trg"
 *       }
 *     ],
 *     [
 *       {
 *         "type": "addr",
 *         "value": 231342
 *       },
 *       {
 *         "type": "type",
 *         "value": 2000,
 *         "alias": "_edge2"
 *       },
 *       {
 *         "type": "alias",
 *         "value": "_edge1"
 *       },
 *       {
 *         "is_required": false
 *       }
 *     ],
 *   ]
 *
 *   "payload": {
 *     "templ": "person _-> .._p (* _=> nrel_email:: _[test@email.com] *);;",
 *      "params":{
 *          ".._p":5314
 *       }
 *   }
 *
 *  * }</pre>
 *
 * @author artrayme
 * @since 0.0.1
 */

public interface PayloadPart {
    Stream<BasicConstruction> getConstructions();
    boolean addConstruction(BasicConstruction construction);
}
