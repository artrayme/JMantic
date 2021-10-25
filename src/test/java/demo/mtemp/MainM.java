package demo.mtemp;

import com.fasterxml.jackson.core.JsonProcessingException;
import demo.mtemp.impl.CreateElementRequest;
import demo.mtemp.impl.ScLink;
import demo.mtemp.impl.ScNode;

public class MainM {
    public static void main(String[] args) throws JsonProcessingException {

        ScNode node = new ScNode(ScNode.NodeType.NODE);
        ScLink link = new ScLink(ScLink.LinkType.LINK);
        String hello = "hello";
        link.changeContent(ScLink.ContentType.STRING, hello);

        ScRequest req1 = new CreateElementRequest();
        req1.addScElementToRequest(node);
        req1.addScElementToRequest(link);

        ScNode node2 = new ScNode(ScNode.NodeType.NODE);
        ScNode node3 = new ScNode(ScNode.NodeType.NODE);
        ScRequest req2 = new CreateElementRequest();
        req2.addScElementToRequest(node2);
        req2.addScElementToRequest(node3);

        String json1 = req1.parsToJsonRequest();
        String json2 = req2.parsToJsonRequest();
        System.out.println(json1);
        System.out.println(json2);

        //sudo docker run -it -p 8000:8000 -p 8090:8090 ostis/ostis:0.6.0 sh ostis --all

    }
}
