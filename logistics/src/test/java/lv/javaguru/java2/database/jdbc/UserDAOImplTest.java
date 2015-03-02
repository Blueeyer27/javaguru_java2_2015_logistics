package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Company;

public class UserDAOImplTest extends DAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private UserDAOImpl userDAO = new UserDAOImpl();

    private CompanyDAOImpl companyDAO = new CompanyDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {
        User user = new User("login1", "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", 11111L);
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getLogin(), userFromDB.getLogin());
        assertEquals(user.getPassword(), userFromDB.getPassword());
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
        assertEquals(user.getEMail(), userFromDB.getEMail());
        assertEquals(user.getPhoneNumber(), userFromDB.getPhoneNumber());
        assertEquals(user.getCompanyId(), userFromDB.getCompanyId());
    }

    @Test
    public void testMultipleUserCreation() throws DBException {
        User user1 = new User("login1", "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", 11111L);
        User user2 = new User("login2", "pass2", "Steve", "Surname", "steve@email.com", "+37124324890", 22222L);
        userDAO.create(user1);
        userDAO.create(user2);
        List<User> users = userDAO.getAll();
        assertEquals(2, users.size());
    }

    @Test
    public void testUpdateUser() throws DBException {
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", 33333L);
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
        user.setFirstName("NewName");
        user.setEMail("updated@email.com");
        userDAO.update(user);
        User updatedUserFromDB = userDAO.getById(user.getUserId());
        assertEquals(user.getUserId(), updatedUserFromDB.getUserId());
        assertEquals(user.getFirstName(), updatedUserFromDB.getFirstName());
        assertEquals(user.getEMail(), updatedUserFromDB.getEMail());
        assertNotEquals(user.getFirstName(), userFromDB.getFirstName());
        assertNotEquals(user.getEMail(), userFromDB.getEMail());
    }

    @Test
    public void testUserDeletion() throws DBException {
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", 33333L);
        userDAO.create(user);
        int size = userDAO.getAll().size();
        userDAO.delete(user.getUserId());
        assertEquals(null, userDAO.getById(user.getUserId()));
        assertEquals(size - 1, userDAO.getAll().size());
    }

    @Test
    public void testMultipleUserDeletion() throws DBException {
        User user1 = new User("login1", "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", 11111L);
        User user2 = new User("login2", "pass2", "Steve", "Surname", "steve@email.com", "+37124324890", 22222L);
        User user3 = new User("login3", "pass3", "Janis", "Berzins", "j.berzins@email.com", "+371234000", 11111L);
        userDAO.create(user1);
        userDAO.create(user2);
        userDAO.create(user3);
        int size = userDAO.getAll().size();

        userDAO.delete(user1.getUserId());
        assertEquals(null, userDAO.getById(user1.getUserId()));
        assertEquals(size - 1, userDAO.getAll().size());

        userDAO.delete(user2.getUserId());
        assertEquals(null, userDAO.getById(user2.getUserId()));
        assertEquals(size - 2, userDAO.getAll().size());

        userDAO.delete(user3.getUserId());
        assertEquals(null, userDAO.getById(user3.getUserId()));
        assertEquals(size - 3, userDAO.getAll().size());
    }

    @Test
    public void testGetUserCompanyType() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company.getCompanyId());
        userDAO.create(user);
        String userCompanyType = user.getUserCompanyType();
        assertEquals(userCompanyType, company.getType());
    }
}
