package lv.javaguru.java2.domain;

/**
 * Created by user on 26.02.2015.
 */
public class Country {

    private long countryId;
    private String name;

    public Country(){

    }

    public Country(long countryId, String name){
        this.countryId = countryId;
        this.name = name;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
