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

    Const(33),

    Var(65),

    Struct(257),

    Tuple(129),

    Role(513),

    NoRole(1025),

    Class(2049),

    Abstract(4097),

    Material(8193),

    ConstStruct(289),

    ConstTuple(161),

    ConstRole(545),

    ConstNoRole(1057),

    ConstClass(2081),

    ConstAbstract(4129),

    ConstMaterial(8225),

    VarStruct(321),

    VarTuple(193),

    VarRole(577),

    VarNoRole(1089),

    VarClass(2113),

    VarAbstract(4161),

    VarMaterial(8257);

    @JsonValue
    private final int code;

    NodeType(int code) {
        this.code = code;
    }
}
