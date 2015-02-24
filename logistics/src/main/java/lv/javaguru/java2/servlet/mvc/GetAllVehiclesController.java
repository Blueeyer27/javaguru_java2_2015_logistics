package lv.javaguru.java2.servlet.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.VehicleDAOImpl;
import lv.javaguru.java2.domain.Vehicle;

/**
 * Created by user on 19.02.2015.
 */
@Component
public class GetAllVehiclesController implements MVCController {

    private VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();

    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        try {
            vehicleList = vehicleDAO.getAll();
        } catch (DBException e) {
            e.printStackTrace();
        }

        MVCModel model = new MVCModel("/jsp/getallvehicles.jsp", vehicleList);
        return model;
    }
}
