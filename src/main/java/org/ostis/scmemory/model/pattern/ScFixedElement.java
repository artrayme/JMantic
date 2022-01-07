package org.ostis.scmemory.model.pattern;

import org.ostis.scmemory.model.element.ScElement;

public interface ScFixedElement extends ScPatternElement{
    ScElement getElement();

    @Override
    default PatternElement getType(){
        return PatternElement.ADDR;
    }
}
