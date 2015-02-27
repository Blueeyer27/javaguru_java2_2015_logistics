package lv.javaguru.java2.domain;

public class Value {

    private long lovId;
    private String type;
    private String value;

    public Value(){

    }

    public Value(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public long getValueId() {
        return lovId;
    }

    public void setValueId(long lovId) {
        this.lovId = lovId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
