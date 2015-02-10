package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Dinjvald on 07/02/15.
 */
public class CargoDAOImplTest extends DAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private CargoDAOImpl cargoDAO = new CargoDAOImpl();
    private UserDAOImpl userDAO = new UserDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {
        User user = createUser("Dinjvald", "qwerty", "Deniss", "Beskorovainijs", "qwerty@email.com",
                "+37126957815", 12345L);
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        Cargo cargo = createCargo(userFromDB.getUserId(), "ref", 21.5, "LV Maskavas", "RU Moscow",
                stringToDate("09/02/2015"), stringToDate("15/02/2015"), "ready");
        cargoDAO.create(cargo);
        Cargo cargoFromDB = cargoDAO.getById(cargo.getCargoId());
        assertNotNull(userFromDB);
        assertNotNull(cargoFromDB);
        assertEquals(cargo.getCargoId(), cargoFromDB.getCargoId());
        assertEquals(user.getUserId(), cargoFromDB.getUserId());
        assertEquals(cargo.getVehicleType(), cargoFromDB.getVehicleType());
        assertEquals(cargo.getWeight(), cargoFromDB.getWeight());
        assertEquals(cargo.getLoadAddress(), cargoFromDB.getLoadAddress());
        assertEquals(cargo.getUnloadAddress(), cargoFromDB.getUnloadAddress());
        assertEquals(cargo.getLoadDate(), cargoFromDB.getLoadDate());
        assertEquals(cargo.getUnloadDate(), cargoFromDB.getUnloadDate());
        assertEquals(cargo.getStatus(), cargoFromDB.getStatus());

    }

    @Test
    public void testDeleteAndGetAll() throws DBException {
        User user1 = createUser("Dinjvald", "qwerty", "Deniss", "Beskorovainijs", "qwerty@email.com",
                "+37126957815", 12345L);
        User user2 = createUser("dinjab", "ytrewq", "Sergejs", "Popovs", "ytrewq@email.com",
                "+37128453698", 54321L);
        userDAO.create(user1);
        userDAO.create(user2);
        Cargo cargo1 = createCargo(user1.getUserId(), "ref", 21.5, "LV Maskavas", "RU Moscow",
                stringToDate("09/02/2015"), stringToDate("15/02/2015"), "ready");
        Cargo cargo2 = createCargo(user2.getUserId(), "tilt", 19.4, "LV Kurzemes", "DE Rein",
                stringToDate("05/02/2015"), stringToDate("10/02/2015"), "ready");
        cargoDAO.create(cargo1);
        cargoDAO.create(cargo2);
        cargoDAO.delete(cargo1.getCargoId());
        List<Cargo> cargos = cargoDAO.getAll();
        assertEquals(1, cargos.size());
    }

    @Test
    public void testUpdate() throws DBException {
        User user1 = createUser("Dinjvald", "qwerty", "Deniss", "Beskorovainijs", "qwerty@email.com",
                "+37126957815", 12345L);
        userDAO.create(user1);
        Cargo cargo1 = createCargo(user1.getUserId(), "ref", 21.5, "LV Maskavas", "RU Moscow",
                stringToDate("09/02/2015"), stringToDate("15/02/2015"), "ready");
        cargoDAO.create(cargo1);
        Cargo cargoFromDB = cargoDAO.getById(cargo1.getCargoId());
        assertEquals(cargo1.getCargoId(), cargoFromDB.getCargoId());

        cargo1.setVehicleType("tilt");
        cargo1.setWeight(19.4);
        cargo1.setLoadAddress("LV Lurzemes");
        cargo1.setUnloadAddress("DE Rein");
        cargo1.setLoadDate(stringToDate("05/02/2015"));
        cargo1.setUnloadDate(stringToDate("10/02/2015"));
        cargo1.setStatus("pending");

        cargoDAO.update(cargo1);
        Cargo updatedCargoFromDB = cargoDAO.getById(cargo1.getCargoId());

        assertEquals(cargoFromDB.getCargoId(), updatedCargoFromDB.getCargoId());
        assertEquals(cargo1.getUserId(), updatedCargoFromDB.getUserId());
        assertEquals(cargo1.getVehicleType(), updatedCargoFromDB.getVehicleType());
        assertEquals(cargo1.getWeight(), updatedCargoFromDB.getWeight());
        assertEquals(cargo1.getLoadAddress(), updatedCargoFromDB.getLoadAddress());
        assertEquals(cargo1.getUnloadAddress(), updatedCargoFromDB.getUnloadAddress());
        assertEquals(cargo1.getLoadDate(), updatedCargoFromDB.getLoadDate());
        assertEquals(cargo1.getUnloadDate(), updatedCargoFromDB.getUnloadDate());
        assertEquals(cargo1.getStatus(), updatedCargoFromDB.getStatus());

    }
/*
    private User createUserObject(String login, String password, String firstName, String lastName,
                                  String eMail, String phoneNumber, Long companyId) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEMail(eMail);
        user.setPhoneNumber(phoneNumber);
        user.setCompanyId(companyId);
        return user;
    }

    private Cargo createCargoObject(long userId, String vehicleType, double weight, String loadAddress,
                                    String unloadAddress, Date loadDate, Date unloadDate, String status) {
        Cargo cargo = new Cargo();
        cargo.setUserId(userId);
        cargo.setVehicleType(vehicleType);
        cargo.setWeight(weight);
        cargo.setLoadAddress(loadAddress);
        cargo.setUnloadAddress(unloadAddress);
        cargo.setLoadDate(loadDate);
        cargo.setUnloadDate(unloadDate);
        cargo.setStatus(status);
        return cargo;
    }*/

    private Date stringToDate(String incomingDate) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = formater.parse(incomingDate);
            return date;
        } catch (ParseException ex) {
            System.out.println("Invalid Date format in method stringToDate(). Should be dd/MM/yyyy");
            ex.printStackTrace();
            return null;
        }
    }
}
