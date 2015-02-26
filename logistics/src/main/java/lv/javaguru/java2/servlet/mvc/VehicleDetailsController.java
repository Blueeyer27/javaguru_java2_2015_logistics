package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.domain.Vehicle;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 20.02.2015.
 */

@Component
@URL(value="/showDetailsVehicle")
public class VehicleDetailsController implements MVCController {

    @Autowired
    private VehicleDAO vehicleDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

       String id = request.getParameter("id");
       long vehicleId = Long.parseLong(id);
        Vehicle vehicle = null;

        try {
          vehicle = vehicleDAO.getById(vehicleId);
        } catch (DBException e) {
            e.printStackTrace();
        }


        MVCModel model = new MVCModel("/jsp/vehicleDetails.jsp", vehicle);
        return model;
    }
}
