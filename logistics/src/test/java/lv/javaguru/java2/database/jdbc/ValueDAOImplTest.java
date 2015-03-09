package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ValueDAO;
import lv.javaguru.java2.domain.Value;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

public class ValueDAOImplTest extends DAOImplTest {

    @Autowired
    @Qualifier("HibValueDAO")
    private ValueDAO valueDAO;

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void testCreate() throws DBException {
        Value value = new Value("Country", "Japan", "Japan");
        valueDAO.create(value);
        Value valueFromDB = valueDAO.getById((value.getValueId()));
        assertNotNull(valueFromDB);
        assertEquals(value.getValueId(), valueFromDB.getValueId());
        assertEquals(value.getType(), valueFromDB.getType());
        assertEquals(value.getValue(), valueFromDB.getValue());
    }

    @Test
    @Transactional
    public void testLookupValue() throws DBException {
        Value value = new Value("Country", "Japan", "Japan");
        valueDAO.create(value);
        String valueJapan = valueDAO.lookupValue("Country", "Japan");
        assertEquals(value.getValue(), valueJapan);
        value.setValue("JPN");
        valueDAO.update(value);
        String valueJPN = valueDAO.lookupValue("Country", "Japan");
        assertEquals(value.getValue(), valueJPN);
    }

    @Test
    @Transactional
    public void getLovByType() throws DBException {
        Value valueLatvia = new Value("Country", "Latvia", "Latvia");
        Value valueAustralia = new Value("Country", "Australia", "Australia");
        Value valueJapan = new Value("Country", "Japan", "Japan");
        Value valueUSA = new Value("Country", "USA", "USA");
        Value statusNew = new Value("Agreement status", "New", "New");
        valueDAO.create(valueLatvia);
        valueDAO.create(valueAustralia);
        valueDAO.create(valueJapan);
        valueDAO.create(valueUSA);
        valueDAO.create(statusNew);
        List<Value> countries = valueDAO.getLovByType("Country");
        assertEquals(4, countries.size());
        List<Value> statuses = valueDAO.getLovByType("Agreement status");
        assertEquals(1, statuses.size());
        List<Value> foo = valueDAO.getLovByType("foo");
        assertEquals(0, foo.size());
    }

    @Test
    @Transactional
    public void testUpdate() throws DBException {
        Value valueLatvia = new Value("Country", "Latvia", "Latvia");
        valueDAO.create(valueLatvia);
        Value valueFromDB = valueDAO.getById((valueLatvia.getValueId()));
        assertEquals(valueLatvia.getValue(), valueFromDB.getValue());
        valueLatvia.setValue("Latvijas Republika");
        valueDAO.update(valueLatvia);
        Value updatedValueFromDB = valueDAO.getById((valueLatvia.getValueId()));
    //    assertNotEquals(valueLatvia.getValue(), valueFromDB.getValue()); //РАЗОБРАТЬСЯ ПОЗЖЕ
        assertEquals(valueLatvia.getValue(), updatedValueFromDB.getValue());
    }

    @Test
    @Transactional
    public void testMultipleValuesDeletion() throws DBException {
        Value valueLatvia = new Value("Country", "Latvia", "Latvia");
        Value valueAustralia = new Value("Country", "Australia", "Australia");
        Value valueJapan = new Value("Country", "Japan", "Japan");
        Value valueUSA = new Value("Country", "USA", "USA");
        Value statusNew = new Value("Agreement status", "New", "New");
        valueDAO.create(valueLatvia);
        valueDAO.create(valueAustralia);
        valueDAO.create(valueJapan);
        valueDAO.create(valueUSA);
        valueDAO.create(statusNew);
        int size = valueDAO.getAll().size();
        assertEquals(size, 5);

        valueDAO.delete(valueLatvia.getValueId());
        assertEquals(null, valueDAO.getById(valueLatvia.getValueId()));
        assertEquals(size - 1, valueDAO.getAll().size());

        valueDAO.delete(valueAustralia.getValueId());
        assertEquals(null, valueDAO.getById(valueAustralia.getValueId()));
        assertEquals(size - 2, valueDAO.getAll().size());

        valueDAO.delete(valueJapan.getValueId());
        assertEquals(null, valueDAO.getById(valueJapan.getValueId()));
        assertEquals(size - 3, valueDAO.getAll().size());
    }
}
