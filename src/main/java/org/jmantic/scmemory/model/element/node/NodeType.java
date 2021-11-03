package org.jmantic.scmemory.model.element.node;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum of available {@link ScNode} types.
 * <p>
 * Each type has a unique code.
 * But this code only for internal use,
 * and you must not use it in your programs.
 *
 * @author artrayme
 * @since 0.0.1
 */
public enum NodeType {
    NODE(1),

    CONST(33),

    VAR(65),

    STRUCT(257),

    TUPLE(129),

    ROLE(513),

    NO_ROLE(1025),

    CLASS(2049),

    ABSTRACT(4097),

    MATERIAL(8193),

    CONST_STRUCT(289),

    CONST_TUPLE(161),

    CONST_ROLE(545),

    CONST_NO_ROLE(1057),

    CONST_CLASS(2081),

    CONST_ABSTRACT(4129),

    CONST_MATERIAL(8225),

    VAR_STRUCT(321),

    VAR_TUPLE(193),

    VAR_ROLE(577),

    VAR_NO_ROLE(1089),

    VAR_CLASS(2113),

    VAR_ABSTRACT(4161),

    VAR_MATERIAL(8257);

    @JsonValue
    private final int code;

    NodeType(int code) {
        this.code = code;
    }
}
