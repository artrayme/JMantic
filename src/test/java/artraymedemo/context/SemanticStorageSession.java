package artraymedemo.context;


import artraymedemo.scmemory.ScElement;

/**
 * @author artrayme
 * 10/22/21
 */
public class SemanticStorageSession {

    public boolean open() {
        return false;
    }

    public ScElement save(ScElement element){
        return null;
    }

    public ScElement getElementByAddress(){
        return null;
    }


    public boolean close(){
        return false;
    }

}
