package artraymedemo.scmemory.websocketprotocol.request;

import artraymedemo.scmemory.websocketprotocol.request.elements.AbstractElement;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author artrayme
 * 10/22/21
 */
public class CreateElementPayload implements PayloadPart{

    @JsonProperty("el")
    private AbstractElement element;


}
