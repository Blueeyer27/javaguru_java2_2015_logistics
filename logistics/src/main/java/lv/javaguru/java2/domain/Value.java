package lv.javaguru.java2.domain;

import javax.persistence.*;

@Entity
@Table(name = "value")
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition = "int(11)")
    private long valueId;

    @Column(name = "type", columnDefinition = "char(50)")
    private String type;

    @Column(name = "code", columnDefinition = "char(50)")
    private String code;

    @Column(name = "value", columnDefinition = "char(50)")
    private String value;

    public Value(){

    }

    public Value(String type, String code, String value) {
        this.type = type;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
