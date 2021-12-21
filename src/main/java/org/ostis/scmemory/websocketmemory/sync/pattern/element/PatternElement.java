package org.ostis.scmemory.websocketmemory.sync.pattern.element;

/**
 * @author artrayme
 * @since 0.3.2
 */
public sealed interface PatternElement permits FixedPatternElement, AliasPatternElement, TypePatternElement {}
