package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ValueDAO;
import lv.javaguru.java2.domain.Value;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import static org.junit.Assert.*;

public class ValueDAOImplTest extends DAOImplTest {

//   @Autowired
//   @Qualifier("HibValueDAO")
//    private ValueDAO valueDAO;

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private ValueDAOImpl valueDAO = new ValueDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {
        Value value = new Value("Country", "Japan");
        System.out.println("valueDAO=============" + valueDAO);

        valueDAO.create(value);
        Value valueFromDB = valueDAO.getById((value.getValueId()));
        assertNotNull(valueFromDB);
        assertEquals(value.getValueId(), valueFromDB.getValueId());
        assertEquals(value.getType(), valueFromDB.getType());
        assertEquals(value.getValue(), valueFromDB.getValue());
    }

    @Test
    public void testGetByType() throws DBException {
        Value valueLatvia = new Value("Country", "Latvia");
        Value valueAustralia = new Value("Country", "Australia");
        Value valueJapan = new Value("Country", "Japan");
        Value valueUSA = new Value("Country", "USA");
        Value statusNew = new Value("Agreement status", "New");
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
    public void testUpdate() throws DBException {
        Value valueLatvia = new Value("Country", "Latvia");
        valueDAO.create(valueLatvia);
        Value valueFromDB = valueDAO.getById((valueLatvia.getValueId()));
        assertEquals(valueLatvia.getValue(), valueFromDB.getValue());
        valueLatvia.setValue("Latvijas Republika");
        valueDAO.update(valueLatvia);
        Value updatedValueFromDB = valueDAO.getById((valueLatvia.getValueId()));
        assertNotEquals(valueLatvia.getValue(), valueFromDB.getValue());
        assertEquals(valueLatvia.getValue(), updatedValueFromDB.getValue());
    }

    @Test
    public void testMultipleValuesDeletion() throws DBException {
        Value valueLatvia = new Value("Country", "Latvia");
        Value valueAustralia = new Value("Country", "Australia");
        Value valueJapan = new Value("Country", "Japan");
        Value valueUSA = new Value("Country", "USA");
        Value statusNew = new Value("Agreement status", "New");
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
