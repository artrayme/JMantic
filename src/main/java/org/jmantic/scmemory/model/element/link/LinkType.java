package org.jmantic.scmemory.model.element.link;

/**
 * @author artrayme
 * @since 0.0.1
 */
public enum LinkType {
    Link ( 2 ),

    LinkConst ( 34 ),

    LinkVar ( 66 );

    private final int code;
    LinkType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
