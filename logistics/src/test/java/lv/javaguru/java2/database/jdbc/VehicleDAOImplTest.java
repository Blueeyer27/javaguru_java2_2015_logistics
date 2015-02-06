package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Vehicle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class VehicleDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();

    private UserDAOImpl userDAO = new UserDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {
        User user = createUser("F", "L");
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        Vehicle vehicle = createVehicle(userFromDB.getUserId(), "MAN" , "Reefer", "GG4107", "DZ855", 302, "PENDING");
        vehicleDAO.create(vehicle,userFromDB);
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
        User user1 = createUser("F1", "L1");
        User user2 = createUser("F2", "L2");
        userDAO.create(user1);
        userDAO.create(user2);
        User user1FromDB = userDAO.getById(user1.getUserId());
        User user2FromDB = userDAO.getById(user2.getUserId());

        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Vehicle vehicle1 = createVehicle(user1FromDB.getUserId(), "MAN" , "Reefer", "GG4107", "DZ855", 302, "PENDING");
        Vehicle vehicle2 = createVehicle(user2FromDB.getUserId(), "VOLVO" , "Container", "DF3908", "UF440", 500, "PENDING");
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicleDAO.create(vehicle1, user1FromDB);
        vehicleDAO.create(vehicle2, user2FromDB);
        Vehicle vehicle1FromDB = vehicleDAO.getById(vehicle1.getVehicleId());
        Vehicle vehicle2FromDB = vehicleDAO.getById(vehicle2.getVehicleId());
        List<Vehicle> vehiclesFromDB = vehicleDAO.getAll();
        assertEquals(vehicles.size(), vehiclesFromDB.size());


    }

    @Test
    public void testDeleteVehicle() throws DBException {
        User user1 = createUser("F1", "L1");
        User user2 = createUser("F2", "L2");
        userDAO.create(user1);
        userDAO.create(user2);
        User user1FromDB = userDAO.getById(user1.getUserId());
        User user2FromDB = userDAO.getById(user2.getUserId());

        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Vehicle vehicle1 = createVehicle(user1FromDB.getUserId(), "MAN" , "Reefer", "GG4107", "DZ855", 302, "PENDING");
        Vehicle vehicle2 = createVehicle(user2FromDB.getUserId(), "VOLVO" , "Container", "DF3908", "UF440", 500, "PENDING");
        vehicleDAO.create(vehicle1, user1FromDB);
        vehicleDAO.create(vehicle2, user2FromDB);
        Vehicle vehicle1FromDB = vehicleDAO.getById(vehicle1.getVehicleId());
        Vehicle vehicle2FromDB = vehicleDAO.getById(vehicle2.getVehicleId());
        vehicleDAO.delete(vehicle1.getVehicleId());
        List<Vehicle> vehiclesFromDB = vehicleDAO.getAll();
        assertEquals(1, vehiclesFromDB.size());

    }

    @Test
    public void testUpdateVehicle() throws DBException {
        User user = createUser("FF", "L");
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        Vehicle vehicle = createVehicle(userFromDB.getUserId(), "MAN" , "Reefer", "GG4107", "DZ855", 302, "PENDING");
        vehicleDAO.create(vehicle,userFromDB);
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
        vehicle.setCapacity((double)400);
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


    private User createUser(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }



    private Vehicle createVehicle(long userId, String name, String plateNumber, String type, String trailerNumber, double capacity, String status){
        Vehicle vehicle = new Vehicle();
        vehicle.setUserId(userId);
        vehicle.setName(name);
        vehicle.setplateNumber(plateNumber);
        vehicle.setType(type);
        vehicle.settrailerNumber(trailerNumber);
        vehicle.setCapacity(capacity);
        vehicle.setStatus(status);
        return vehicle;
    }

}
