package org.ostis.scmemory.websocketmemory.util;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.node.NodeType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum ScTypesMap {
    INSTANCE;

    private final Map<Integer, Object> types = new HashMap<>();

    ScTypesMap() {
        Arrays.stream(EdgeType.values()).forEach(e -> {
            types.put(e.getCode(), e);
        });
        Arrays.stream(EdgeType.values()).forEach(e -> {
            types.put(e.getCode(), e);
        });
        Arrays.stream(LinkType.values()).forEach(e -> {
            types.put(e.getCode(), e);
        });
    }

    public Map<Integer, Object> getTypes() {
        return types;
    }
}
