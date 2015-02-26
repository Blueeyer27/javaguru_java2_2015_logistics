package lv.javaguru.java2.servlet.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import lv.javaguru.java2.servlet.model.URL;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Vehicle;
import lv.javaguru.java2.servlet.model.RegistrationMethods;

/**
 * Created by user on 17.02.2015.
 */
@SuppressWarnings("UnnecessaryLocalVariable")
@Component
@URL(value="/vehicleregresult")
public class VehicleRegResultController implements MVCController {
    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String plateNumber = request.getParameter("platenumber");
        String trailerNumber = request.getParameter("trailernumber");
        String capacity = request.getParameter("capacity");

        //эту хрень тупо захардкодил
        String userid = "13";
        String status = "new";


        List<String> parameters = new ArrayList<String>();

        parameters.add(userid);
        parameters.add(name);
        parameters.add(type);
        parameters.add(plateNumber);
        parameters.add(trailerNumber);
        parameters.add(capacity);
        parameters.add(status);

        RegistrationMethods reg = new RegistrationMethods();

        try {
            reg.vehicleCreate(parameters);
        } catch (DBException e) {
            e.printStackTrace();
        }

        long realUserid = Integer.parseInt(userid);
        double realcapacity = Double.parseDouble(capacity);

        Vehicle vehicle = new Vehicle(realUserid,name,type,plateNumber,trailerNumber,realcapacity,status);

        MVCModel model = new MVCModel("/jsp/vehicleregresult.jsp", vehicle);

        return model;
    }
}
