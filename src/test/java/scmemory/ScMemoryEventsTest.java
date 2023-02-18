package scmemory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.event.EventType;
import org.ostis.scmemory.model.event.OnAddIngoingEdgeEvent;
import org.ostis.scmemory.model.event.OnAddOutgoingEdgeEvent;
import org.ostis.scmemory.model.event.OnDeleteEvent;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ScMemoryEventsTest {

    private ScMemory scMemory;

    @BeforeEach
    public void init() throws Exception {
        scMemory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
        scMemory.open();
    }

    @AfterEach
    public void shutdown() throws Exception {
        scMemory.close();
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void onAddOutgoingEdgeEvent() throws Exception {
        var node1 = scMemory.createNodes(Stream.of(NodeType.NODE))
                            .findFirst()
                            .get();
        var event = new DummyOnAddOutgoingEdgeEvent();
        scMemory.subscribeOnEvent(
                node1,
                event);

        var node2 = scMemory.createNodes(Stream.of(NodeType.NODE))
                            .findFirst()
                            .get();
        var edge = scMemory.createEdges(
                                   Stream.of(EdgeType.ACCESS),
                                   Stream.of(node1),
                                   Stream.of(node2))
                           .findFirst()
                           .get();

        Thread.sleep(1000);

        assertEquals(
                node1.getAddress(),
                event.mainElement.getAddress());
        assertEquals(
                edge.getAddress(),
                event.edge.getAddress());
        assertEquals(
                node2.getAddress(),
                event.edgeTarget.getAddress());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void onAddOutgoingEdgeEventFailed() throws Exception {
        var node1 = scMemory.createNodes(Stream.of(NodeType.NODE))
                            .findFirst()
                            .get();
        var event = new DummyOnAddOutgoingEdgeEvent();
        scMemory.subscribeOnEvent(
                node1,
                event);

        var node2 = scMemory.createNodes(Stream.of(NodeType.NODE))
                            .findFirst()
                            .get();
        var edge = scMemory.createEdges(
                                   Stream.of(EdgeType.ACCESS),
                                   Stream.of(node2),
                                   Stream.of(node1))
                           .findFirst()
                           .get();

        Thread.sleep(1000);

        assertNull(event.mainElement);
        assertNull(event.edge);
        assertNull(event.edgeTarget);
    }


    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void onAddIngoingEdgeEvent() throws Exception {
        var node1 = scMemory.createNodes(Stream.of(NodeType.NODE))
                            .findFirst()
                            .get();
        var event = new DummyOnAddIngoingEdgeEvent();
        scMemory.subscribeOnEvent(
                node1,
                event);

        var node2 = scMemory.createNodes(Stream.of(NodeType.NODE))
                            .findFirst()
                            .get();
        var edge = scMemory.createEdges(
                                   Stream.of(EdgeType.ACCESS),
                                   Stream.of(node2),
                                   Stream.of(node1))
                           .findFirst()
                           .get();

        Thread.sleep(1000);

        assertEquals(
                node1.getAddress(),
                event.mainElement.getAddress());
        assertEquals(
                edge.getAddress(),
                event.edge.getAddress());
        assertEquals(
                node2.getAddress(),
                event.edgeTarget.getAddress());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void onAddIngoingEdgeEventFail() throws Exception {
        var node1 = scMemory.createNodes(Stream.of(NodeType.NODE))
                            .findFirst()
                            .get();
        var event = new DummyOnAddIngoingEdgeEvent();
        scMemory.subscribeOnEvent(
                node1,
                event);

        var node2 = scMemory.createNodes(Stream.of(NodeType.NODE))
                            .findFirst()
                            .get();
        var edge = scMemory.createEdges(
                                   Stream.of(EdgeType.ACCESS),
                                   Stream.of(node1),
                                   Stream.of(node2))
                           .findFirst()
                           .get();

        Thread.sleep(1000);

        assertNull(event.mainElement);
        assertNull(event.edge);
        assertNull(event.edgeTarget);
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void onDeleteEvent() throws Exception {
        var node1 = scMemory.createNodes(Stream.of(NodeType.NODE))
                            .findFirst()
                            .get();
        var event = new DummyOnDeleteEvent();
        scMemory.subscribeOnEvent(
                node1,
                event);
        scMemory.deleteElements(Stream.of(node1));

        Thread.sleep(1000);

        assertEquals(
                node1.getAddress(),
                event.deletedElement.getAddress());
    }

    private static class DummyOnAddOutgoingEdgeEvent implements OnAddOutgoingEdgeEvent {
        ScElement mainElement;
        ScEdge edge;
        ScElement edgeTarget;

        @Override
        public void onEvent(ScElement mainElement, ScEdge edge, ScElement edgeTarget) {
            this.mainElement = mainElement;
            this.edge = edge;
            this.edgeTarget = edgeTarget;
            assertEquals(
                    this.getEventType(),
                    EventType.ON_ADD_OUTGOING_EDGE);
        }
    }

    private static class DummyOnAddIngoingEdgeEvent implements OnAddIngoingEdgeEvent {
        ScElement mainElement;
        ScEdge edge;
        ScElement edgeTarget;

        @Override
        public void onEvent(ScElement mainElement, ScEdge edge, ScElement edgeTarget) {
            this.mainElement = mainElement;
            this.edge = edge;
            this.edgeTarget = edgeTarget;
            assertEquals(
                    this.getEventType(),
                    EventType.ON_ADD_INGOING_EDGE);
        }
    }

    private static class DummyOnDeleteEvent implements OnDeleteEvent {
        ScElement deletedElement;

        @Override
        public void onEvent(ScElement element) {
            deletedElement = element;
            assertEquals(
                    this.getEventType(),
                    EventType.ON_DELETE_ELEMENT);
        }
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void unsubscribeEvent() throws Exception {
        var node1 = scMemory.createNodes(Stream.of(NodeType.NODE))
                            .findFirst()
                            .get();
        var event = new DummyOnAddOutgoingEdgeEvent();
        var eventId = scMemory.subscribeOnEvent(
                node1,
                event);
        Thread.sleep(1000);
        scMemory.unsubscribeEvent(eventId.stream());

        var node2 = scMemory.createNodes(Stream.of(NodeType.NODE))
                            .findFirst()
                            .get();
        var edge = scMemory.createEdges(
                                   Stream.of(EdgeType.ACCESS),
                                   Stream.of(node1),
                                   Stream.of(node2))
                           .findFirst()
                           .get();


        assertNull(event.mainElement);
        assertNull(event.edge);
        assertNull(event.edgeTarget);
    }

    /*
     @Test
     @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
     void onRemoveOutgoingEdgeEvent() throws Exception {
     var node1 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
     var event = new DummyOnRemoveOutgoingEdgeEvent();
     var x = scMemory.subscribeOnEvent(node1, event);
     var node2 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
     var edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(node1), Stream.of(node2)).findFirst().get();
     scMemory.deleteElements(Stream.of(edge));
     Thread.sleep(1000);

     assertEquals(node1.getAddress(), event.mainElement.getAddress());
     assertEquals(edge.getAddress(), event.edge.getAddress());
     assertEquals(node2.getAddress(), event.edgeTarget.getAddress());
     }

     @Test
     @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
     void onRemoveIngoingEdgeEvent() throws Exception {
     var node1 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
     var node2 = scMemory.createNodes(Stream.of(NodeType.NODE)).findFirst().get();
     var edge = scMemory.createEdges(Stream.of(EdgeType.ACCESS), Stream.of(node2), Stream.of(node1)).findFirst().get();
     var event = new DummyOnRemoveIngoingEdgeEvent();
     scMemory.subscribeOnEvent(node1, event);
     scMemory.deleteElements(Stream.of(edge));
     Thread.sleep(1000);

     assertEquals(node1.getAddress(), event.mainElement.getAddress());
     assertEquals(edge.getAddress(), event.edge.getAddress());
     assertEquals(node2.getAddress(), event.edgeTarget.getAddress());
     }
     private static class DummyOnRemoveOutgoingEdgeEvent implements OnRemoveOutgoingEdgeEvent {
     ScElement mainElement;
     ScEdge edge;
     ScElement edgeTarget;

     @Override public void onEvent(ScElement mainElement, ScEdge edge, ScElement edgeTarget) {
     this.mainElement = mainElement;
     this.edge = edge;
     this.edgeTarget = edgeTarget;
     assertEquals(this.getEventType(), EventType.ON_REMOVE_OUTGOING_EDGE);
     }
     }

     private static class DummyOnRemoveIngoingEdgeEvent implements OnRemoveIngoingEdgeEvent {
     ScElement mainElement;
     ScEdge edge;
     ScElement edgeTarget;

     @Override public void onEvent(ScElement mainElement, ScEdge edge, ScElement edgeTarget) {
     this.mainElement = mainElement;
     this.edge = edge;
     this.edgeTarget = edgeTarget;
     assertEquals(this.getEventType(), EventType.ON_REMOVE_INGOING_EDGE);
     }
     }
     */


}
