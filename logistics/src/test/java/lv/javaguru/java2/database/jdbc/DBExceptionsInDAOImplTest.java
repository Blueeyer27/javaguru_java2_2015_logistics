package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Vehicle;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DBExceptionsInDAOImplTest extends DAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private CompanyDAOImpl companyDAO = new CompanyDAOImpl();

    private CargoDAOImpl cargoDAO = new CargoDAOImpl();

    private VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();



    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetDBExceptionInObjectCreation() throws DBException {
        Company company = new Company(null, "111", "Riga, registred",
                "Riga, actual 1", "FIGBANK", "BLABLA100500", "Latvia", "Transporter");
        exception.expect(DBException.class);
        companyDAO.create(company);
    }

    @Test
    public void testGetDBExceptionInObjectUpdate() throws DBException {
        Company company = new Company("FirstCompany", "111", "Riga, registred",
                "Riga, actual 1", "FIGBANK", "BLABLA100500", "Latvia", "Transporter");
        companyDAO.create(company);
        Company companySpy = Mockito.spy(company);
        Mockito.when(companySpy.getName()).thenReturn(null);
        exception.expect(DBException.class);
        companyDAO.update(companySpy);
    }

    @Test
     public void testGetDBExceptionInObjectDelete() throws DBException {
        CompanyDAOImpl companyDAOSpy = Mockito.spy(companyDAO);
        Mockito.when(companyDAOSpy.getTableName()).thenReturn("fooBarTable");
        exception.expect(DBException.class);
        companyDAOSpy.delete(1001L);
    }

    @Test
    public void testGetDBExceptionInGetById() throws DBException {
        CompanyDAOImpl companyDAOSpy = Mockito.spy(companyDAO);
        Mockito.when(companyDAOSpy.getTableName()).thenReturn("fooBarTable");
        exception.expect(DBException.class);
        companyDAOSpy.getById(1001L);
    }

    @Test
     public void testGetDBExceptionInGetAll() throws DBException {
        CompanyDAOImpl companyDAOSpy = Mockito.spy(companyDAO);
        Mockito.when(companyDAOSpy.getTableName()).thenReturn("fooBarTable");
        exception.expect(DBException.class);
        companyDAOSpy.getById(1001L);
    }

    @Test
    public void testGetDBExceptionInGetCargoByParameters() throws DBException {
        exception.expect(DBException.class);
        cargoDAO.getByParameters(null, null, null, null, null, null, null);
    }

    @Test
    public void testGetDBExceptionInGetVehicleByParameters() throws DBException {
        exception.expect(DBException.class);
        vehicleDAO.getByParameters(null, null, null);
    }
}
