package mtemp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;

@JsonPropertyOrder({"el", "type", "content", "content_type", "addr"})
public abstract class ScElement {
    @JsonView(View.Request.class)
    @JsonProperty("el")
    private Element element;
    @JsonView(View.Request.class)
    @JsonProperty("type")
    private int code;
    @JsonView(View.Address.class)
    @JsonProperty("addr")
    private int scAddress;

    public ScElement(ScElement.Element elementType, int code) {
        this.element = elementType;
        this.code = code;
    }

    public int getScAddress() {
        return scAddress;
    }

    public void setScAddress(int scAddress) {
        this.scAddress = scAddress;
    }

    public Element getElement() {
        return element;
    }

    public int getCode() {
        return code;
    }

    public enum Element {
        NODE("node"),
        EDGE("edge"),
        LINK("link");
        private String type;

        Element(String type) {
            this.type = type;
        }

        @JsonValue
        public String getType() {
            return type;
        }
    }
}
