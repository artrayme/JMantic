package org.jmantic.model;

/**
 * Enum which represents the type a request may be
 *
 * @author artrayme
 * @since 0.0.1
 */

//ToDO documentation
public enum RequestType {
    /**
     * With this command you can create a batch of elements.
     */
    CREATE_ELEMENTS,

    /**
     * With this command you can check if specified elements exist.
     */
    CHECK_ELEMENTS,

    /**
     * With this command you can delete elements.
     */
    DELETE_ELEMENTS,

    /**
     * With this command you can search elements by specified template.
     */
    SEARCH_ELEMENTS,

    /**
     * With this command you can create elements by specified template.
     */
    GENERATE_TEMPLATE,

    /**
     * With this command you can subscribe to sc-machine events.
     */
    EVENTS,

    /**
     * With this command you can find/resolve keynodes.
     */
    KEYNODES,

    /**
     * With this command you can work with sc-link content.
     */
    CONTENT
}
