package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.User;
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
        companies.add(createCompany("A company", "123"));
        companies.add(createCompany("B company", "123"));
        companies.add(createCompany("C company", "123"));

        Mockito.doReturn(companies).when(companyDAO).getAll();

        List<Company> companyFromDB = controller.getCompanyListFromDB();
        assertEquals(companies == companyFromDB, true);
    }

    private Company createCompany(String name, String regnumber) {
        Company companyMock = Mockito.mock(Company.class);
        Mockito.doReturn(name).when(companyMock).getName();
        Mockito.doReturn(regnumber).when(companyMock).getRegNumber();
        return companyMock;
    }

}
