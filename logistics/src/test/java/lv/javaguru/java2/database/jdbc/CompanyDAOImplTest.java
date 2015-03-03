package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Company;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CompanyDAOImplTest extends DAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private CompanyDAOImpl companyDAO = new CompanyDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
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
    public void testUpdate() throws DBException {
        Company company = new Company("TestCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvia", "Transporter");
        companyDAO.create(company);
        Company companyFromDB = companyDAO.getById((company.getCompanyId()));
        assertEquals(company.getName(), companyFromDB.getName());
        company.setName("UpdatedName");
        companyDAO.update(company);
        Company updatedCompanyFromDB = companyDAO.getById((company.getCompanyId()));
        assertNotEquals(company.getName(), companyFromDB.getName());
        assertEquals(company.getName(), updatedCompanyFromDB.getName());
    }

    @Test
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

    @Test
    public void testCreateNullCbject() throws DBException {
        Company company = null;
        companyDAO.create(company);
        assertNull(company);
    }

    @Test
    public void testUpdateNullCbject() throws DBException {
        Company company = null;
        companyDAO.update(company);
        assertNull(company);
    }
}
