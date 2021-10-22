package artraymedemo.scmemory;

/**
 * @author artrayme
 * 10/22/21
 */
public class ScMemoryConfiguration {
    public ScMemoryConfiguration() {

    }

    public ScMemory getScMemory(){
        return new DefaultScMemoryImpl();
    }
}
