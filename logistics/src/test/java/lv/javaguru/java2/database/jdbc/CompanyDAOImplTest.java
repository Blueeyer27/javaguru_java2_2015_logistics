package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

public class CompanyDAOImplTest extends DAOImplTest {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testGetCompanyesWithUsers() throws DBException {

        TransactionTemplate tt = new TransactionTemplate(transactionManager);
        final AtomicLong companyId = new AtomicLong();

        tt.execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus paramTransactionStatus) {
                Company company1 = new Company("CompanyCargo", "123", "Reg address 1", "Actual address 2", "Hansabank", "HABA21", "Latvia", "cargo");
                try {
                    companyDAO.create(company1);
                    companyId.set(company1.getCompanyId());
                } catch (DBException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        tt.execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus paramTransactionStatus) {
                try {
                    Company company = companyDAO.getById((companyId.get()));
                    User user1 = new User("user1", "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", company);
                    User user2 = new User("user2", "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", company);
                    User user3 = new User("user3", "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", company);
                    userDAO.create(user1);
                    userDAO.create(user2);
                    userDAO.create(user3);
                } catch (DBException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        tt.execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus paramTransactionStatus) {
                try {
                    Company company = companyDAO.getById((companyId.get()));
                    List<User> userList = company.getUserList();
                    int size = userDAO.getAll().size();

                    assertEquals(size, userList.size());
                } catch (DBException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Test
    @Transactional
    public void testCreate() throws DBException {
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        Company companyFromDB = companyDAO.getById((company.getCompanyId()));
        assertNotNull(companyFromDB);
        assertEquals(company.getCompanyId(), companyFromDB.getCompanyId());
        assertEquals(company.getName(), companyFromDB.getName());
        assertEquals(company.getRegNumber(), companyFromDB.getRegNumber());
        assertEquals(company.getRegAddress(), companyFromDB.getRegAddress());
        assertEquals(company.getActualAddress(), companyFromDB.getActualAddress());
        assertEquals(company.getBank(), companyFromDB.getBank());
        assertEquals(company.getIban(), companyFromDB.getIban());
        assertEquals(company.getCountry(), companyFromDB.getCountry());
        assertEquals(company.getType(), companyFromDB.getType());
    }

    @Test
    @Transactional
    public void testMultipleCompanyCreation() throws DBException {
        List<Company> companies = companyDAO.getAll();
        Company company1 = new Company("FirstCompany", "111", "Riga, registred",
                "Riga, actual 1", "FIGBANK", "BLABLA100500", "Latvia", "Transporter");
        Company company2 = new Company("SecondCompany", "222", "Barcelona, registred",
                "Riga, actual 2", "ABC", "666", "Spain", "Transporter");
        companyDAO.create(company1);
        companyDAO.create(company2);
        assertEquals(companyDAO.getAll().size(), companies.size() + 2);
    }

    @Test
    @Transactional
    public void testUpdate() throws DBException {
        Company company = new Company("TestCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvia", "Transporter");
        companyDAO.create(company);
        Company companyFromDB = companyDAO.getById((company.getCompanyId()));
        assertEquals(company.getName(), companyFromDB.getName());
        company.setName("UpdatedName");
        companyDAO.update(company);
        Company updatedCompanyFromDB = companyDAO.getById((company.getCompanyId()));
    //    assertNotEquals(company.getName(), companyFromDB.getName());
        assertEquals(company.getName(), updatedCompanyFromDB.getName());
    }

    @Test
    @Transactional
    public void testMultipleCompanyDeletion() throws DBException {
        Company company1 = new Company("FirstCompany", "111", "Riga, registred",
                "Riga, actual 1", "FIGBANK", "BLABLA100500", "Latvia", "Transporter");
        Company company2 = new Company("SecondCompany", "222", "Barcelona, registred",
                "Riga, actual 2", "ABC", "666", "Spain", "Transporter");
        Company company3 = new Company("ThirdCompany", "333", "Moscow, registred",
                "Saint-P., actual 3", "Sberbank", "SBER100500", "Russia", "Transporter");
        companyDAO.create(company1);
        companyDAO.create(company2);
        companyDAO.create(company3);
        int size = companyDAO.getAll().size();

        companyDAO.delete(company1.getCompanyId());
        assertEquals(null, companyDAO.getById(company1.getCompanyId()));
        assertEquals(size - 1, companyDAO.getAll().size());

        companyDAO.delete(company2.getCompanyId());
        assertEquals(null, companyDAO.getById(company2.getCompanyId()));
        assertEquals(size - 2, companyDAO.getAll().size());

        companyDAO.delete(company3.getCompanyId());
        assertEquals(null, companyDAO.getById(company3.getCompanyId()));
        assertEquals(size - 3, companyDAO.getAll().size());
    }

    /*
    @Test
    @Transactional
    public void testCreateNullObject() throws DBException {
        Company company = null;
        companyDAO.create(company);
        assertNull(company);
    }

    @Test
    @Transactional
    public void testUpdateNullObject() throws DBException {
        Company company = null;
        companyDAO.update(company);
        assertNull(company);
    }
    */
}
