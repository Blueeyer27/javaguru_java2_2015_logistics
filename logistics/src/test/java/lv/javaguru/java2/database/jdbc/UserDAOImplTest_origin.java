package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;


public class UserDAOImplTest_origin {

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
    public void testDelete() throws DBException{
        User user1 = createUser("F1", "L1");
        User user2 = createUser("F2", "L2");
        userDAO.create(user1);
        userDAO.create(user2);
        userDAO.delete(user1.getUserId());
        List<User> users = userDAO.getAll();
        assertEquals(1, users.size());

    }

    @Test
    public void testUpdate() throws DBException{
        User user1 = createUser("F1", "L1");
        userDAO.create(user1);
        changeUserName(user1, "F2", "L2");
        userDAO.update(user1);
        User userFromDb = userDAO.getById(user1.getUserId());
        assertEquals(user1.getFirstName() , userFromDb.getFirstName());
        assertEquals(user1.getLastName(), userFromDb.getLastName());


    }

    @Test
    public void testGetById()throws DBException{
        User user1 = createUser("F1", "L1");
        userDAO.create(user1);
        User userFromDb = userDAO.getById(user1.getUserId());
        assertEquals(user1.getUserId(),userFromDb.getUserId());

    }

    @Test
    public void testGetAll() throws DBException{
        List<User> users = new ArrayList<User>();
        User user1 = createUser("F1", "L1");
        User user2 = createUser("F2", "L2");
        users.add(user1);
        users.add(user2);
        userDAO.create(user1);
        userDAO.create(user2);
        List<User> usersFromDb = userDAO.getAll();
        assertEquals(users.size(), usersFromDb.size());

    }

    private User createUser(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

    private void changeUserName(User user, String firstName, String lastName){
      user.setFirstName(firstName);
        user.setLastName(lastName);

    }



}