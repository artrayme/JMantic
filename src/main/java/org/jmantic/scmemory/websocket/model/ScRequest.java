package org.jmantic.scmemory.websocket.model;

import java.util.stream.Stream;

/**
 * The {@code ScRequest} class represents a common structure of the json-request to the sc-machine.
 * <p>
 * <p>
 * Request example:
 * <pre>{@code
 * {
 *   "id": 2,
 *   "type": "request type",
 *   "payload": {
 *     ...
 *   }
 * }
 * }</pre>
 * Where
 * <ul>
 *   <li> id - unique id of command. Used to identify responses;
 *   <li> type - type of the request. See {@link RequestType};
 *   <li> payload - command specified data.
 * </ul>
 *
 * @author artrayme
 * @since 0.0.1
 **/
public interface ScRequest {

    /**
     * @return the id of the current request
     */
    Long getId();

    /**
     * @return type of the current request. See {@link RequestType}
     */
    RequestType getType();

    /**
     * @return stream of the payload parts of the current request. See {@link PayloadPart}
     */
    Stream<PayloadPart> getPayload();

    /**
     * Append the {@link PayloadPart} to end of the payload request section.
     */
    boolean addPayloadPart(PayloadPart payloadPart);
}
