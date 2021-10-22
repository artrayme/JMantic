package artraymedemo;

import artraymedemo.context.JManticContext;
import artraymedemo.scmemory.ScElement;
import artraymedemo.scmemory.ScMemoryConfiguration;
import artraymedemo.scmemory.sctypes.ScNodeType;
import artraymedemo.scmemory.websocketprotocol.request.AbstractRequest;
import artraymedemo.scmemory.websocketprotocol.request.CreateNodeRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @author artrayme
 * 10/22/21
 */
public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        JManticContext context = new JManticContext(new ScMemoryConfiguration().getScMemory());
        ScElement element = context.createNode(ScNodeType.CONST);

        ObjectMapper objectMapper = new ObjectMapper();
        AbstractRequest emp = objectMapper.readValue("{  \"id\": 2,  \"type\": \"create_elements\",  \"payload\": [    {      \"el\": \"node\",      \"type\": 1    },    {      \"el\": \"link\",      \"type\": 2,      \"content\": 45.4    }  ]}", CreateNodeRequest.class);
        System.out.println(emp);
//        logger.info(emp.toString());

    }

//    {  "id": 2,  "type": "create_elements",  "payload": [    {      "el": "node",      "type": 1    },    {      "el": "link",      "type": 2,      "content": 45.4    },    {      "el": "edge",      "src": {        "type": "ref",        "value": 0      },      "trg": {        "type": "ref",        "value": 1      },      "type": 32    }  ]}

}
