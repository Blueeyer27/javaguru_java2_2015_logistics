package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class VehicleSearchResultControllerTest {

    @Mock
    private VehicleDAO vehicleDAO;

    @Mock
    private CompanyDAO companyDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private VehicleSearchResultController controller = new VehicleSearchResultController();

    @Test
    public void getVehiclesByParameters() throws DBException{
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "transport");
        companyDAO.create(company);
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
        userDAO.create(user);

        vehicles.add(new Vehicle(user, "Name1", "Type", "plate", "trailer", 23.0, "Status"));
        vehicles.add(new Vehicle(user, "Name2", "Type", "plate", "trailer", 13.0, "Status"));
        vehicles.add(new Vehicle(user, "Name3", "Type", "plate", "trailer", 33.0, "Status"));

        Mockito.doReturn(vehicles).when(vehicleDAO).getByParameters("Type", 11.0, 35.0);

        List<Vehicle> vehiclesFromDB = controller.getVehiclesByParameters("Type", 11.0, 35.0);
        assertTrue(vehicles == vehiclesFromDB);
    }
}
