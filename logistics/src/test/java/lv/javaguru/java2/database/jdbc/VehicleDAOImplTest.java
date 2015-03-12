package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class VehicleDAOImplTest extends DAOImplTest {

    @Autowired
    @Qualifier("HibVehicleDAO")
    private VehicleDAO vehicleDAO;

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void testCreate() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        Vehicle vehicle = new Vehicle(userFromDB, "MAN" , "tilt", "GG4107", "DZ855", 18.5, "PENDING");
        vehicleDAO.create(vehicle);
        Vehicle vehicleFromDB = vehicleDAO.getById(vehicle.getVehicleId());
        assertNotNull(userFromDB);
        assertNotNull(vehicleFromDB);
        assertEquals(vehicle.getVehicleId(), vehicleFromDB.getVehicleId());
        assertEquals(vehicle.getUser().getUserId(), vehicleFromDB.getUser().getUserId());
        assertEquals(vehicle.getName(), vehicleFromDB.getName());
        assertEquals(vehicle.getplateNumber(), vehicleFromDB.getplateNumber());
        assertEquals(vehicle.getType(), vehicleFromDB.getType());
        assertEquals(vehicle.gettrailerNumber(), vehicleFromDB.gettrailerNumber());
        assertEquals(vehicle.getCapacity(), vehicleFromDB.getCapacity());
        assertEquals(vehicle.getStatus(), vehicleFromDB.getStatus());
    }

    @Test
    @Transactional
    public void testGetAll() throws DBException{
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "transport");
        companyDAO.create(company);
        User user1 = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
        User user2 = new User("login2", "pass2", "Steve", "Surname", "steve@email.com", "+37124324890", company);
        userDAO.create(user1);
        userDAO.create(user2);
        User user1FromDB = userDAO.getById(user1.getUserId());
        User user2FromDB = userDAO.getById(user2.getUserId());

        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Vehicle vehicle1 = new Vehicle(user1FromDB, "MAN" , "tilt", "GG4107", "DZ855", 18.5, "PENDING");
        Vehicle vehicle2 = new Vehicle(user2FromDB, "VOLVO" , "platform", "DF3908", "UF440", 14.9, "PENDING");
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicleDAO.create(vehicle1);
        vehicleDAO.create(vehicle2);
        List<Vehicle> vehiclesFromDB = vehicleDAO.getAll();
        assertEquals(vehicles.size(), vehiclesFromDB.size());
    }

    @Test
    @Transactional
    public void testDeleteVehicle() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user1 = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
        User user2 = new User("login2", "pass2", "Steve", "Surname", "steve@email.com", "+37124324890", company);
        userDAO.create(user1);
        userDAO.create(user2);
        User user1FromDB = userDAO.getById(user1.getUserId());
        User user2FromDB = userDAO.getById(user2.getUserId());
        Vehicle vehicle1 = new Vehicle(user1FromDB, "MAN" , "tilt", "GG4107", "DZ855", 13.8, "PENDING");
        Vehicle vehicle2 = new Vehicle(user2FromDB, "VOLVO" , "platform", "DF3908", "UF440", 15.1, "PENDING");
        vehicleDAO.create(vehicle1);
        vehicleDAO.create(vehicle2);
        vehicleDAO.delete(vehicle1.getVehicleId());
        List<Vehicle> vehiclesFromDB = vehicleDAO.getAll();
        assertEquals(1, vehiclesFromDB.size());
    }

    @Test
    @Transactional
    public void testUpdateVehicle() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        Vehicle vehicle = new Vehicle(userFromDB, "MAN" , "Reefer", "GG4107", "DZ855", 14.8, "PENDING");
        vehicleDAO.create(vehicle);
        Vehicle vehicleFromDB = vehicleDAO.getById(vehicle.getVehicleId());
        assertNotNull(userFromDB);
        assertNotNull(vehicleFromDB);
        assertEquals(vehicle.getVehicleId(), vehicleFromDB.getVehicleId());
        assertEquals(vehicle.getUserId(), vehicleFromDB.getUserId());
        assertEquals(vehicle.getName(), vehicleFromDB.getName());
        assertEquals(vehicle.getplateNumber(), vehicleFromDB.getplateNumber());
        assertEquals(vehicle.getType(), vehicleFromDB.getType());
        assertEquals(vehicle.gettrailerNumber(), vehicleFromDB.gettrailerNumber());
        assertEquals(vehicle.getCapacity(), vehicleFromDB.getCapacity());
        assertEquals(vehicle.getStatus(), vehicleFromDB.getStatus());

        vehicle.setName("KAMAZ");
        vehicle.setType("Bulker");
        vehicle.setplateNumber("AA777");
        vehicle.settrailerNumber("BB666");
        vehicle.setCapacity(15.8);
        vehicle.setStatus("AWAY");
        vehicleDAO.update(vehicle);

        Vehicle vehicleFromDBUpdated = vehicleDAO.getById(vehicle.getVehicleId());
        assertEquals(vehicle.getVehicleId(), vehicleFromDBUpdated.getVehicleId());
        assertEquals(vehicle.getUserId(), vehicleFromDBUpdated.getUserId());
        assertEquals(vehicle.getName(), vehicleFromDBUpdated.getName());
        assertEquals(vehicle.getplateNumber(), vehicleFromDBUpdated.getplateNumber());
        assertEquals(vehicle.getType(),vehicleFromDBUpdated.getType());
        assertEquals(vehicle.gettrailerNumber(), vehicleFromDBUpdated.gettrailerNumber());
        assertEquals(vehicle.getCapacity(), vehicleFromDBUpdated.getCapacity());
        assertEquals(vehicle.getStatus(), vehicleFromDBUpdated.getStatus());
    }


    @Test
    @Transactional
    public void testGetByParameters() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user1 = new User("username1", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
        User user2 = new User("username2", "pass2", "Steve", "Surname", "steve@email.com", "+37124324890", company);
        User user3 = new User("username3", "pass3", "Nikolajs", "Petrovs", "petr@email.com", "+37125551111", company);
        userDAO.create(user1);
        userDAO.create(user2);
        userDAO.create(user3);
        User user1FromDB = userDAO.getById(user1.getUserId());
        User user2FromDB = userDAO.getById(user2.getUserId());
        User user3FromDB = userDAO.getById(user3.getUserId());

        Vehicle vehicle1 = new Vehicle(user1FromDB, "KAMAZ" , "tilt", "GG4107", "DZ855", 12.5, "PENDING");
        Vehicle vehicle2 = new Vehicle(user2FromDB, "URAL" , "platform", "DF3908", "UF440", 18.5, "PENDING");
        Vehicle vehicle3 = new Vehicle(user3FromDB, "ZIL" , "platform", "DF3908", "UF440", 8.0, "PENDING");
        vehicleDAO.create(vehicle1);
        vehicleDAO.create(vehicle2);
        vehicleDAO.create(vehicle3);

        List<Vehicle> vehicles = vehicleDAO.getByParameters("platform", 5.0, 22.0);
        assertEquals(2, vehicles.size());

        vehicles = vehicleDAO.getByParameters("tilt", 5.0, 22.0);
        assertEquals(1, vehicles.size());
    }

    @Test
    @Transactional
    public void testGetNullByNonExistentId() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        Vehicle vehicle = new Vehicle(userFromDB, "MAN" , "tilt", "GG4107", "DZ855", 18.5, "PENDING");
        vehicleDAO.create(vehicle);

        Vehicle vehicleFromDB;
        if(vehicle.getVehicleId() == 111L)
            vehicleFromDB = vehicleDAO.getById(222L);
        else
            vehicleFromDB = vehicleDAO.getById(111L);
        assertNull(vehicleFromDB);
    }
}
