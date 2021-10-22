package artraymedemo.context;

import artraymedemo.scmemory.ScMemory;
import artraymedemo.scmemory.sctypes.ScNode;
import artraymedemo.scmemory.sctypes.ScNodeType;

/**
 * @author artrayme
 * 10/22/21
 */
public class JManticContext {
    private final ScMemory memory;

    public JManticContext(ScMemory memory) {
        this.memory = memory;
    }

    public ScNode createNode(ScNodeType type){
//        session.save(new ScNode());
        return null;
    }

}
