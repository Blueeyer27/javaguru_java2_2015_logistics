package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Value;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ValueDAOImplTest extends DAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private ValueDAOImpl valueDAO = new ValueDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {
        Value value = new Value("Country", "Japan");
        valueDAO.create(value);
        Value valueFromDB = valueDAO.getById((value.getValueId()));
        assertNotNull(valueFromDB);
        assertEquals(value.getValueId(), valueFromDB.getValueId());
        assertEquals(value.getType(), valueFromDB.getType());
        assertEquals(value.getValue(), valueFromDB.getValue());
    }

    @Test
    public void testGetByParameters() throws DBException {
        Value valueLatvia = new Value("Country", "Latvia");
        Value valueAustralia = new Value("Country", "Australia");
        Value valueJapan = new Value("Country", "Japan");
        Value valueUSA = new Value("Country", "USA");
        valueDAO.create(valueLatvia);
        valueDAO.create(valueAustralia);
        valueDAO.create(valueJapan);
        valueDAO.create(valueUSA);
        List<Value> countries = valueDAO.getLovByType("Country");
        assertEquals(4, countries.size());
    }
}
