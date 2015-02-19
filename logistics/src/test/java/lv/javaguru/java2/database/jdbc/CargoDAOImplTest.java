package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;

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
                cargoDAO.stringToDate("09/02/2015"), cargoDAO.stringToDate("15/02/2015"), "ready");
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
                cargoDAO.stringToDate("09/02/2015"), cargoDAO.stringToDate("15/02/2015"), "ready");
        Cargo cargo2 = createCargo(user2.getUserId(), "tilt", 19.4, "LV Kurzemes", "DE Rein",
                cargoDAO.stringToDate("05/02/2015"), cargoDAO.stringToDate("10/02/2015"), "ready");
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
                cargoDAO.stringToDate("09/02/2015"), cargoDAO.stringToDate("15/02/2015"), "ready");
        cargoDAO.create(cargo1);
        Cargo cargoFromDB = cargoDAO.getById(cargo1.getCargoId());
        assertEquals(cargo1.getCargoId(), cargoFromDB.getCargoId());

        cargo1.setVehicleType("tilt");
        cargo1.setWeight(19.4);
        cargo1.setLoadAddress("LV Lurzemes");
        cargo1.setUnloadAddress("DE Rein");
        cargo1.setLoadDate(cargoDAO.stringToDate("05/02/2015"));
        cargo1.setUnloadDate(cargoDAO.stringToDate("10/02/2015"));
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

    @Test
    public void testGetByParameters() throws DBException {
        Cargo cargo1 = new Cargo(1111, "platform", 21.5, "LV Maskavas", "RU Moscow",
                cargoDAO.stringToDate("01/02/2015"), cargoDAO.stringToDate("15/03/2015"), "ready");
        Cargo cargo2 = new Cargo(1111, "platform", 19.4, "LV Kurzemes", "DE Rein",
                cargoDAO.stringToDate("12/02/2015"), cargoDAO.stringToDate("10/03/2015"), "ready");
        Cargo cargo3 = new Cargo(1111, "platform", 31.4, "LV AAA", "RU Moscow",
                cargoDAO.stringToDate("23/02/2015"), cargoDAO.stringToDate("25/03/2015"), "ready");
        Cargo cargo4 = new Cargo(1111, "platform", 9.8, "LV BBB", "DE Rein",
                cargoDAO.stringToDate("05/02/2015"), cargoDAO.stringToDate("13/03/2015"), "ready");
        cargoDAO.create(cargo1);
        cargoDAO.create(cargo2);
        cargoDAO.create(cargo3);
        cargoDAO.create(cargo4);
        List<Cargo> cargos = cargoDAO.getByParameters("platform", 15.0, 32.0, cargoDAO.stringToDate("04/02/2015"),
                cargoDAO.stringToDate("25/02/2015"), cargoDAO.stringToDate("10/03/2015"), cargoDAO.stringToDate("26/03/2015"));
        assertEquals(2, cargos.size());
        cargos = cargoDAO.getByParameters("platform", 15.0, 32.0, cargoDAO.stringToDate("01/02/2015"),
                cargoDAO.stringToDate("25/02/2015"), cargoDAO.stringToDate("10/03/2015"), cargoDAO.stringToDate("26/03/2015"));
        assertEquals(3, cargos.size());
    }
}
