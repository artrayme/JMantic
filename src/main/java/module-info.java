module JMantic.main {
    requires com.fasterxml.jackson.annotation;
    requires org.slf4j;
    requires com.fasterxml.jackson.databind;

    //    exporting usable public api
    exports org.ostis.api.context;

    //    exporting main interfaces and abstractions
    exports org.ostis.scmemory.model;
    exports org.ostis.scmemory.model.exception;
    exports org.ostis.scmemory.model.event;
    exports org.ostis.scmemory.model.element;
    exports org.ostis.scmemory.model.element.node;
    exports org.ostis.scmemory.model.element.link;
    exports org.ostis.scmemory.model.element.edge;
    exports org.ostis.scmemory.model.pattern;
    exports org.ostis.scmemory.model.pattern.factory;
    exports org.ostis.scmemory.model.pattern.pattern3;
    exports org.ostis.scmemory.model.pattern.pattern5;

    //    exporting sync sc-memory implementation
    exports org.ostis.scmemory.websocketmemory.memory;
    exports org.ostis.scmemory.websocketmemory.memory.pattern;
    exports org.ostis.scmemory.websocketmemory.memory.pattern.element;

}