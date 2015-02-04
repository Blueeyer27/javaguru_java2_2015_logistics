package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;


public class UserDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

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
        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
    }

    @Test
    public void testMultipleUserCreation() throws DBException {
        User user1 = createUser("F1", "L1");
        User user2 = createUser("F2", "L2");
        userDAO.create(user1);
        userDAO.create(user2);
        List<User> users = userDAO.getAll();
        assertEquals(2, users.size());
    }

    @Test
    public void testDeleteUser() throws DBException {
        User user = createUser("Frank", "Lewis");
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        userDAO.delete(user.getUserId());
        User userFromDB2 = userDAO.getById(user.getUserId());
        assertNull(userFromDB2);
    }

    @Test
    public void testDeleteMultipleUser() throws DBException {
        User user1 = createUser("Del1", "Lastname1");
        User user2 = createUser("Del2", "Lastname2");
        User user3 = createUser("Del3", "Lastname3");
        userDAO.create(user1);
        userDAO.create(user2);
        userDAO.create(user3);
        assertEquals(3, userDAO.getAll().size());
        userDAO.delete(user1.getUserId());
        assertEquals(2, userDAO.getAll().size());
        userDAO.delete(user2.getUserId());
        assertEquals(1, userDAO.getAll().size());
        userDAO.delete(user3.getUserId());
        assertEquals(0, userDAO.getAll().size());
    }

    @Test
    public void testUpdateUser() throws DBException {
        User user = createUser("Foo", "Bar");
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
        user.setFirstName("John");
        user.setLastName("Barson");
        userDAO.update(user);
        User userFromDbUpdated = userDAO.getById(user.getUserId());
        assertNotEquals(user.getFirstName(), userFromDB.getFirstName());
        assertNotEquals(user.getLastName(), userFromDB.getLastName());
        assertEquals(user.getFirstName(), userFromDbUpdated.getFirstName());
        assertEquals(user.getLastName(), userFromDbUpdated.getLastName());
    }

    private User createUser(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

}