module JMantic.main {
    requires com.fasterxml.jackson.annotation;
    requires org.slf4j;
    requires com.fasterxml.jackson.databind;

    exports org.jmantic.api.context;
    exports org.jmantic.scmemory.websocketmemory.sync;

    exports org.jmantic.scmemory.model;
    exports org.jmantic.scmemory.model.exception;
    exports org.jmantic.scmemory.model.event;
    exports org.jmantic.scmemory.model.element;
    exports org.jmantic.scmemory.model.element.node;
    exports org.jmantic.scmemory.model.element.link;
    exports org.jmantic.scmemory.model.element.edge;
    exports org.jmantic.scmemory.model.pattern;
    exports org.jmantic.scmemory.model.pattern.factory;
    exports org.jmantic.scmemory.model.pattern.pattern3;
    exports org.jmantic.scmemory.model.pattern.pattern5;

}