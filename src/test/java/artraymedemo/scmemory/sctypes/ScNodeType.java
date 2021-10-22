package artraymedemo.scmemory.sctypes;

/**
 * @author artrayme
 * 10/22/21
 */
public enum ScNodeType {
    CONST(0),
    VAR(1);


    private final int code;
    ScNodeType(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
