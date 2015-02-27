package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CountryDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Country;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Vehicle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by user on 26.02.2015.
 */
public class CountryDAOImplTest extends DAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private CountryDAO countryDAO = new CountryDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {

        Country countryFromDB = null;
        List<Country> countries = new ArrayList<Country>();

        countries.add(createCountry("Albania"));
        countries.add(createCountry("Russia"));
        countries.add(createCountry("Latvia"));
        countries.add(createCountry("Germany"));
        countries.add(createCountry("Poland"));
        countries.add(createCountry("Lithuania"));
        countries.add(createCountry("France"));
        countries.add(createCountry("Holland"));
        countries.add(createCountry("Denmark"));
        countries.add(createCountry("Spain"));
        countries.add(createCountry("Italy"));
        countries.add(createCountry("Finland"));

        for(Country country : countries){
            countryDAO.create(country);

        }

    }

}
