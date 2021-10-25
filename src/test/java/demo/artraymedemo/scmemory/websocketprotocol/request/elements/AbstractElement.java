package demo.artraymedemo.scmemory.websocketprotocol.request.elements;

import demo.artraymedemo.scmemory.websocketprotocol.request.Element;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author artrayme
 * 10/22/21
 */
public abstract class AbstractElement {
    @JsonProperty("el")
    protected Element element;
}
