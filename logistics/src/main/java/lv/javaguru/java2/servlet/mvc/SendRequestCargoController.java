package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CargoDAOImpl;
import lv.javaguru.java2.database.jdbc.VehicleDAOImpl;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.Vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendRequestCargoController implements MVCController {

    @Override
    public MVCModel processRequest(HttpServletRequest request, 
                                   HttpServletResponse response) {
        Long cargoId = Long.parseLong(request.getParameter("id"));
        Map<String, Object> modelMap = new HashMap<String, Object> ();
        CargoDAOImpl cargoDAO = new CargoDAOImpl();
        VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
        Cargo cargo = null;
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();

        try {
            cargo = cargoDAO.getById(cargoId);
            vehicleList = vehicleDAO.getAll();
        } catch (DBException e) {
            e.printStackTrace();
        }

        modelMap.put("cargo", cargo);
        modelMap.put("vehicleList", vehicleList);

        MVCModel model = new MVCModel("/jsp/sendRequestCargo.jsp", modelMap);
        return model;
    }
}
