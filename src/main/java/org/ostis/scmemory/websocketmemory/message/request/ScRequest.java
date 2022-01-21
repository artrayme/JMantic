package org.ostis.scmemory.websocketmemory.message.request;

/**
 * An interface that is a generic class of system requests.
 * <p>
 * Each request has a common structure:
 * <pre>
 * {
 * "id": 2,
 * "type": "request type",
 * "payload": {...}
 * }
 * </pre>
 * Different requests have different payload structures
 *
 * @author Michael
 * @since 0.0.1
 */
public interface ScRequest {
}
