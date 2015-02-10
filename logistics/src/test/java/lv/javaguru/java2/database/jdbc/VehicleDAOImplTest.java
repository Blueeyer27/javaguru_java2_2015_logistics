package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Vehicle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class VehicleDAOImplTest extends DAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();

    private UserDAOImpl userDAO = new UserDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {
        User user = createUser("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", 33333L);
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        Vehicle vehicle = createVehicle(userFromDB.getUserId(), "MAN" , "Reefer", "GG4107", "DZ855", 18.5, "PENDING");
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

    }

    @Test
    public void testGetAll() throws DBException{
        User user1 = createUser("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", 33333L);
        User user2 = createUser("login2", "pass2", "Steve", "Surname", "steve@email.com", "+37124324890", 22222L);
        userDAO.create(user1);
        userDAO.create(user2);
        User user1FromDB = userDAO.getById(user1.getUserId());
        User user2FromDB = userDAO.getById(user2.getUserId());

        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Vehicle vehicle1 = createVehicle(user1FromDB.getUserId(), "MAN" , "Reefer", "GG4107", "DZ855", 18.5, "PENDING");
        Vehicle vehicle2 = createVehicle(user2FromDB.getUserId(), "VOLVO" , "Container", "DF3908", "UF440", 14.9, "PENDING");
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicleDAO.create(vehicle1);
        vehicleDAO.create(vehicle2);
        Vehicle vehicle1FromDB = vehicleDAO.getById(vehicle1.getVehicleId());
        Vehicle vehicle2FromDB = vehicleDAO.getById(vehicle2.getVehicleId());
        List<Vehicle> vehiclesFromDB = vehicleDAO.getAll();
        assertEquals(vehicles.size(), vehiclesFromDB.size());


    }

    @Test
    public void testDeleteVehicle() throws DBException {
        User user1 = createUser("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", 33333L);
        User user2 = createUser("login2", "pass2", "Steve", "Surname", "steve@email.com", "+37124324890", 22222L);
        userDAO.create(user1);
        userDAO.create(user2);
        User user1FromDB = userDAO.getById(user1.getUserId());
        User user2FromDB = userDAO.getById(user2.getUserId());

        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Vehicle vehicle1 = createVehicle(user1FromDB.getUserId(), "MAN" , "Reefer", "GG4107", "DZ855", 13.8, "PENDING");
        Vehicle vehicle2 = createVehicle(user2FromDB.getUserId(), "VOLVO" , "Container", "DF3908", "UF440", 15.1, "PENDING");
        vehicleDAO.create(vehicle1);
        vehicleDAO.create(vehicle2);
        Vehicle vehicle1FromDB = vehicleDAO.getById(vehicle1.getVehicleId());
        Vehicle vehicle2FromDB = vehicleDAO.getById(vehicle2.getVehicleId());
        vehicleDAO.delete(vehicle1.getVehicleId());
        List<Vehicle> vehiclesFromDB = vehicleDAO.getAll();
        assertEquals(1, vehiclesFromDB.size());

    }

    @Test
    public void testUpdateVehicle() throws DBException {
        User user = createUser("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", 33333L);
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        Vehicle vehicle = createVehicle(userFromDB.getUserId(), "MAN" , "Reefer", "GG4107", "DZ855", 14.8, "PENDING");
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

}
