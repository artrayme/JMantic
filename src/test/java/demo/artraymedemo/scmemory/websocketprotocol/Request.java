package demo.artraymedemo.scmemory.websocketprotocol;

import demo.artraymedemo.scmemory.websocketprotocol.request.RequestType;

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
