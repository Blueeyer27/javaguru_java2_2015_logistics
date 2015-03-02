package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.VehicleDAO;
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

    @InjectMocks
    private VehicleSearchResultController controller = new VehicleSearchResultController();

    @Test
    public void getVehiclesByParameters() throws DBException{
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Vehicle(11L, "Name1", "Type", "plate", "trailer", 23.0, "Status"));
        vehicles.add(new Vehicle(11L, "Name2", "Type", "plate", "trailer", 13.0, "Status"));
        vehicles.add(new Vehicle(11L, "Name3", "Type", "plate", "trailer", 33.0, "Status"));

        Mockito.doReturn(vehicles).when(vehicleDAO).getByParameters("Type", 11.0, 35.0);

        List<Vehicle> vehiclesFromDB = controller.getVehiclesByParameters("Type", 11.0, 35.0);
        assertTrue(vehicles == vehiclesFromDB);
    }
}
