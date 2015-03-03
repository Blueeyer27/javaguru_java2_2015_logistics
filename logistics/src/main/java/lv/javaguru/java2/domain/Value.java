package lv.javaguru.java2.domain;

import javax.persistence.*;

@Entity
@Table(name = "value")
public class Value {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long valueId;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;

    public Value(){

    }

    public Value(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public long getValueId() {
        return valueId;
    }

    public void setValueId(long lovId) {
        this.valueId = lovId;
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
