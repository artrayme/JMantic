package org.ostis.scmemory.websocketmemory.util.api;

import org.ostis.scmemory.model.element.UnknownScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;
import org.ostis.scmemory.websocketmemory.memory.core.OstisClientSync;
import org.ostis.scmemory.websocketmemory.memory.message.request.FindByPatternRequestImpl;
import org.ostis.scmemory.websocketmemory.memory.message.request.GetLinkContentRequestImpl;
import org.ostis.scmemory.websocketmemory.memory.pattern.SearchingPatternTriple;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.AliasPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.FixedPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.TypePatternElement;
import org.ostis.scmemory.websocketmemory.memory.sender.RequestSenderImpl;
import org.ostis.scmemory.websocketmemory.message.request.FindByPatternRequest;
import org.ostis.scmemory.websocketmemory.message.request.GetLinkContentRequest;
import org.ostis.scmemory.websocketmemory.message.response.FindByPatternResponse;
import org.ostis.scmemory.websocketmemory.message.response.GetLinkContentResponse;
import org.ostis.scmemory.websocketmemory.sender.RequestSender;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author artrayme
 * @since 0.6.1
 */
public class IdtfUtils {

    private IdtfUtils() {
    }

    public static List<String> getAllIdtfFast(SyncOstisScMemory memory) throws Exception {
        OstisClientSync client = new OstisClientSync(memory.getURI(),
                                                     e->{},
                                                     "Client for idtfs");
        RequestSender sender = new RequestSenderImpl(client);
        client.open();

        ScNode first = memory.findKeynodes(Stream.of("nrel_main_idtf"))
                             .findFirst()
                             .get()
                             .get();
        AliasPatternElement edge2Alias = new AliasPatternElement("edge_2");
        FindByPatternRequest request = new FindByPatternRequestImpl();
        request.addComponent(new SearchingPatternTriple(
                new TypePatternElement<>(
                        UnknownScElement.ELEMENT,
                        new AliasPatternElement("element_1")),
                new TypePatternElement<>(
                        EdgeType.ACCESS,
                        edge2Alias),
                new TypePatternElement<>(
                        UnknownScElement.ELEMENT,
                        new AliasPatternElement("element_3"))));

        request.addComponent(new SearchingPatternTriple(
                new FixedPatternElement(first),
                new TypePatternElement<>(
                        EdgeType.ACCESS,
                        new AliasPatternElement("rel_edge")),
                edge2Alias));

        FindByPatternResponse response = sender.sendFindByPatternRequest(request);
        GetLinkContentRequest getLinkContentRequest = new GetLinkContentRequestImpl();
        getLinkContentRequest.addToRequest(response.getFoundAddresses()
                                                   .map(e -> e.toList()
                                                              .get(2))
                                                   .toList());
        GetLinkContentResponse getLinkContentResponse = sender.sendGetLinkContentRequest(getLinkContentRequest);
        client.close();

        return getLinkContentResponse.getContent()
                                     .stream()
                                     .map(e -> (String) e)
                                     .toList();
    }
}
