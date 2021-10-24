package org.jmantic.scmemory.model.element.edge;

/**
 * Enum of available {@link ScEdge} types.
 * <p>
 * Each type has a unique code.
 * But this code only for internal use,
 * and you must not use it in your programs.
 *
 * @author artrayme
 * @since 0.0.1
 */
public enum EdgeType {

    U_COMMON(4),

    D_COMMON(8),

    U_COMMON_CONST(36),

    D_COMMON_CONST(40),

    U_COMMON_VAR(68),

    D_COMMON_VAR(72),

    ACCESS(16),

    ACCESS_CONST_POS_PERM(2224),

    ACCESS_CONST_NEG_PERM(2352),

    ACCESS_CONST_FUZ_PERM(2608),

    ACCESS_CONST_POS_TEMP(1200),

    ACCESS_CONST_NEG_TEMP(1328),

    ACCESS_CONST_FUZ_TEMP(1584),

    ACCESS_VAR_POS_PERM(2256),

    ACCESS_VAR_NEG_PERM(2384),

    ACCESS_VAR_FUZ_PERM(2640),

    ACCESS_VAR_POS_TEMP(1232),

    ACCESS_VAR_NEG_TEMP(1360),

    ACCESS_VAR_FUZ_TEMP(1616);

    private final int code;

    EdgeType(int code) {
        this.code = code;
    }

    /**
     * DO NOT USE IT
     */
    public int getCode() {
        return code;
    }
}
