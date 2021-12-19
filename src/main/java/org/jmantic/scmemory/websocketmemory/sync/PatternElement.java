package org.jmantic.scmemory.websocketmemory.sync;

/**
 * @author artrayme
 * @since 0.3.2
 */
sealed interface PatternElement permits FixedPatternElement, AliasPatternElement, TypePatternElement {}
