package mtemp.impl;

import mtemp.ScElement;


public class ScNode extends ScElement {

    public ScNode(NodeType type) {
        super(Element.NODE, type.getDecimalCode());
    }

    public enum NodeType {
        NODE(1);
        //todo more types
        private int decimalCode;

        NodeType(int decimalCode) {
            this.decimalCode = decimalCode;
        }

        public int getDecimalCode() {
            return decimalCode;
        }
    }
}
