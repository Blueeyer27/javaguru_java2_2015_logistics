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

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 26.02.2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class VehicleRegResultControllerTest {

    @Mock
    private VehicleDAO vehicleDao;

    @InjectMocks
    private VehicleRegResultController controller = new VehicleRegResultController();

    @Test
    public void shouldCreateVehicleInDb() throws DBException{

    Vehicle vehicle = createVehicle("A", "B", "C", "D", 13.3);

    Mockito.doNothing().when(vehicleDao).create(vehicle);

}

    @Test
    public void shouldReturnVehicleFromDb() throws DBException{
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(createVehicle("A", "B", "C", "D", 13.3));
        vehicles.add(createVehicle("AA", "BB", "CC", "DD", 14.3));
        vehicles.add(createVehicle("AAA", "BBB", "CCC", "DDD", 15.3));

        Mockito.doReturn(vehicles).when(vehicleDao).getAll();

        List<Vehicle> vehiclesFromDb = controller.getAllVehiclesFromDb();
        assertEquals(vehicles == vehiclesFromDb, true);

    }

    private Vehicle createVehicle(String name, String type,String plateNumber, String trailerNumber, double capacity) {
        Vehicle vehicleMock = Mockito.mock(Vehicle.class);
        Mockito.doNothing().when(vehicleMock).setName(name);
        Mockito.doNothing().when(vehicleMock).setType(type);
        Mockito.doNothing().when(vehicleMock).setplateNumber(plateNumber);
        Mockito.doNothing().when(vehicleMock).settrailerNumber(trailerNumber);
        Mockito.doNothing().when(vehicleMock).setCapacity(capacity);

        return vehicleMock;
    }

}
