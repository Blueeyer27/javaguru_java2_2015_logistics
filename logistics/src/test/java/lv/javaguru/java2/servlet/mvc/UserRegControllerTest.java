package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class UserRegControllerTest {

    @Mock
    private CompanyDAO companyDAO;

    @InjectMocks
    private UserRegController controller = new UserRegController();

    @Test
    public void shouldReturnCompanyListFromDatabase() throws DBException {
        List<Company> companies = new ArrayList<Company>();
        companies.add(createCompany("A company", "123", "cargo"));
        companies.add(createCompany("B company", "123", "transport"));
        companies.add(createCompany("C company", "123", "transport"));

        Mockito.doReturn(companies).when(companyDAO).getAllClientCompanies();
        List<Company> companiesFromDB = controller.getCompanyListFromDB();
        assertEquals(companies == companiesFromDB, true);
    }

    private Company createCompany(String name, String regnumber, String type) {
        Company companyMock = Mockito.mock(Company.class);
        Mockito.doReturn(name).when(companyMock).getName();
        Mockito.doReturn(regnumber).when(companyMock).getRegNumber();
        Mockito.doReturn(type).when(companyMock).getType();
        return companyMock;
    }
}
