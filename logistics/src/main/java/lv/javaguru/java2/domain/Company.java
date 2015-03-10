package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition = "int(11)")
    private long companyId;

    @Column(name = "name", columnDefinition = "char(50)")
    private String name;

    @Column(name = "reg_number", columnDefinition = "char(50)")
    private String regNumber;

    @Column(name = "reg_address", columnDefinition = "char(100)")
    private String regAddress;

    @Column(name = "actual_address", columnDefinition = "char(100)")
    private String actualAddress;

    @Column(name = "bank", columnDefinition = "char(50)")
    private String bank;

    @Column(name = "iban", columnDefinition = "char(50)")
    private String iban;

    @Column(name = "country", columnDefinition = "char(50)")
    private String country;

    @Column(name = "type", columnDefinition = "char(30)")
    private String type;

    @OneToMany (fetch=FetchType.LAZY, mappedBy = "company")
    public List<User> userList = new ArrayList<User>();

    public Company() {
    }

    public Company(String name, String regNumber, String regAddress, String actualAddress,
                   String bank, String iban, String country, String type) {
        this.name = name;
        this.regNumber = regNumber;
        this.regAddress = regAddress;
        this.actualAddress = actualAddress;
        this.bank = bank;
        this.iban = iban;
        this.country = country;
        this.type = type;
    }

    public List getUserList() {
        return userList;
    }


    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
