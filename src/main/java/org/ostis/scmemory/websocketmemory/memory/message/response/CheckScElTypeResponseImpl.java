package org.ostis.scmemory.websocketmemory.memory.message.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ostis.scmemory.websocketmemory.message.response.CheckScElTypeResponse;
import org.ostis.scmemory.websocketmemory.util.internal.ScTypesMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * @author artrayme
 * @since 0.6.0
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CheckScElTypeResponseImpl extends AbstractScResponse implements CheckScElTypeResponse {
    @JsonProperty("payload")
    private List<Integer> foundTypes;

    @JsonIgnore
    @Override
    public Stream<Object> getTypes() {
        List<Object> result = new ArrayList<>(foundTypes.size());
        foundTypes.forEach(e -> {
            result.add(ScTypesMap.INSTANCE.getTypes()
                                          .get(e));
        });
        return result.stream();
    }
}
