package artraymedemo.scmemory.websocketprotocol;

import artraymedemo.scmemory.websocketprotocol.request.RequestType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.stream.Stream;

/**
 * @author artrayme
 * 10/22/21
 */
public interface Request {
    Long getId();
    RequestType getType();
    Stream<String> payload();
}
