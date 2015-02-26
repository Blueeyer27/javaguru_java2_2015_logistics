package lv.javaguru.java2.servlet.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.VehicleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.javaguru.java2.servlet.model.URL;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Vehicle;
import lv.javaguru.java2.servlet.model.RegistrationMethods;

/**
 * Created by user on 17.02.2015.
 */
@Component
@URL(value="/vehicleregresult")
public class VehicleRegResultController implements MVCController {

    @Autowired
    private VehicleDAO vehicleDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String plateNumber = request.getParameter("platenumber");
        String trailerNumber = request.getParameter("trailernumber");
        String capacity = request.getParameter("capacity");

        //эту хрень тупо захардкодил покачто
        String userid = "13";
        String status = "new";

        //parsing
        long realUserid = Integer.parseInt(userid);
        double realcapacity = Double.parseDouble(capacity);


        long vehicleId = createVehicleInDatabase(new Vehicle(realUserid, name, type, plateNumber, trailerNumber, realcapacity, status));

        Vehicle vehicleFromDb = getCreatedVehicleFromDatabase(vehicleId);

        MVCModel model = new MVCModel("/jsp/vehicleregresult.jsp", vehicleFromDb);

        return model;
    }

    protected long createVehicleInDatabase(Vehicle vehicle){
        Vehicle newVehicle = vehicle;
        long vehicleId;
        try {
            vehicleDAO.create(newVehicle);
        } catch (DBException e) {
            e.printStackTrace();
        }
        vehicleId = newVehicle.getVehicleId();
        return vehicleId;
    }

    protected Vehicle getCreatedVehicleFromDatabase(long vehicleId){
        Vehicle vehicleFromDb = null;
        try {
           vehicleFromDb = vehicleDAO.getById(vehicleId);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return vehicleFromDb;
    }
}
