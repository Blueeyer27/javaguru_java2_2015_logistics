package lv.javaguru.java2.domain;

public class Company {

    private long companyId;
    private String name;
    private String regNumber;
    private String regAddress;
    private String actualAddress;
    private String bank;
    private String iban;
    private long countryId;
    private String type;

    public Company() {
    }

    public Company(String name, String regNumber, String regAddress, String actualAddress,
                   String bank, String iban, long country, String type) {
        this.name = name;
        this.regNumber = regNumber;
        this.regAddress = regAddress;
        this.actualAddress = actualAddress;
        this.bank = bank;
        this.iban = iban;
        this.countryId = country;
        this.type = type;
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

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
