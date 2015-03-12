package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import lv.javaguru.java2.database.UserDAO;
import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;

public class UserDAOImplTest extends DAOImplTest {

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;


    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();


    private CompanyDAOImpl companyDAO = new CompanyDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }


    @Test
    @Transactional
    public void testCreate() throws DBException {
        Company company = new Company("TestCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvia", "Transporter");
        companyDAO.create(company);
        User user = new User("login1", "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", company);
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
    @Transactional
    public void testMultipleUserCreation() throws DBException {
        Company company = new Company("TestCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvia", "Transporter");
        companyDAO.create(company);
        User user1 = new User("login1", "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", company);
        User user2 = new User("login2", "pass2", "Steve", "Surname", "steve@email.com", "+37124324890", company);
        userDAO.create(user1);
        userDAO.create(user2);
        List<User> users = userDAO.getAll();
        assertEquals(2, users.size());
    }

    @Test
    @Transactional
    public void testUpdateUser() throws DBException {
        Company company = new Company("TestCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvia", "Transporter");
        companyDAO.create(company);
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
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
//        assertNotEquals(user.getFirstName(), userFromDB.getFirstName());
//        assertNotEquals(user.getEMail(), userFromDB.getEMail());
    }

    @Test
    @Transactional
    public void testUserDeletion() throws DBException {
        Company company = new Company("TestCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvia", "Transporter");
        companyDAO.create(company);
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
        userDAO.create(user);
        int size = userDAO.getAll().size();
        userDAO.delete(user.getUserId());
        assertEquals(null, userDAO.getById(user.getUserId()));
        assertEquals(size - 1, userDAO.getAll().size());
    }

    @Test
    @Transactional
    public void testMultipleUserDeletion() throws DBException {

        Company company = new Company("TestCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvia", "Transporter");
        companyDAO.create(company);
        User user1 = new User("login1", "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", company);
        User user2 = new User("login2", "pass2", "Steve", "Surname", "steve@email.com", "+37124324890", company);
        User user3 = new User("login3", "pass3", "Janis", "Berzins", "j.berzins@email.com", "+371234000", company);
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
    @Transactional
    public void testGetUserCompanyType() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "transport");
        companyDAO.create(company);
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
        userDAO.create(user);
        String userCompanyType = user.getUserCompanyType();
        assertEquals(userCompanyType, company.getType());
    }

    @Test
    @Transactional
    public void testGetUserByLogin() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user1 = new User("login1", "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", company);
        User user2 = new User("login2", "pass2", "Steve", "Surname", "steve@email.com", "+37124324890", company);
        userDAO.create(user1);
        userDAO.create(user2);
        User userFromDB1 = userDAO.getByLogin("login1");
        assertEquals(userFromDB1.getEMail(), "fb@email.com");
        User userFromDB2 = userDAO.getByLogin("login2");
        assertEquals(userFromDB2.getPhoneNumber(), "+37124324890");
    }
}
