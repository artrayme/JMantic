package mtemp.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mtemp.ScRequest;
import mtemp.View;

public class CreateElementRequest extends ScRequest {
    private static final String type = "create_elements";

    public CreateElementRequest() {
        super(type);
    }

    @Override
    public String parsToJsonRequest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithView(View.Request.class).writeValueAsString(this);
    }
}
