package org.jmantic.scmemory.model.element.link;

/**
 * Enum of available {@link ScLink} types.
 * <p>
 * Each type has a unique code.
 * But this code only for internal use,
 * and you must not use it in your programs.
 *
 * @author artrayme
 * @since 0.0.1
 */
public enum LinkType {
    LINK(2),

    LINK_CONST(34),

    LINK_VAR(66);

    private final int code;

    LinkType(int code) {
        this.code = code;
    }

    /**
     * DO NOT USE IT
     */
    public int getCode() {
        return code;
    }
}
