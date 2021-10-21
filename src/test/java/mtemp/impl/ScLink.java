package mtemp.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import mtemp.ScElement;

public class ScLink extends ScElement {
    @JsonProperty("content_type")
    private ContentType contentType;
    private Object content;

    public ScLink(LinkType type) {
        super(Element.LINK, type.getDecimalCode());
    }

    //todo custom exception
    public void changeContent(ContentType type, Object content) throws IllegalArgumentException {
        String incomingType = content.getClass().getSimpleName();
        incomingType = incomingType.toLowerCase();
        if (!incomingType.equals(type.getType())) {
            throw new IllegalArgumentException("invalid content");
        }
        this.contentType = type;
        this.content = content;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public Object getContent() {
        return content;
    }

    public enum LinkType {
        LINK(2);
        //todo more types
        private int decimalCode;

        LinkType(int decimalCode) {
            this.decimalCode = decimalCode;
        }

        public int getDecimalCode() {
            return decimalCode;
        }
    }

    public enum ContentType {
        FLOAT("float"),
        INT("int"),
        STRING("string"),
        BINARY("binary");
        private String type;

        ContentType(String type) {
            this.type = type;
        }

        @JsonValue
        public String getType() {
            return type;
        }
    }
}
