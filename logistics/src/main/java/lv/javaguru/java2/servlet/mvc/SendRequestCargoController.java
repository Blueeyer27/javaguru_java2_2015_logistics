package lv.javaguru.java2.servlet.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CargoDAOImpl;
import lv.javaguru.java2.database.jdbc.VehicleDAOImpl;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.Vehicle;

@Component
@URL(value="/sendRequestCargo")
public class SendRequestCargoController implements MVCController {

    @Autowired
    @Qualifier("HibernateCargoDAO")
    private CargoDAO cargoDAO;

    @Autowired
    @Qualifier("HibVehicleDAO")
    private VehicleDAO vehicleDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request, 
                                   HttpServletResponse response) {
        Long cargoId = Long.parseLong(request.getParameter("id"));
        Map<String, Object> modelMap = new HashMap<String, Object> ();
        Cargo cargo = null;
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();

        try {
            cargo = cargoDAO.getById(cargoId);
            vehicleList = vehicleDAO.getByParameters(cargo.getVehicleType(),
                    cargo.getWeight(), cargo.MAX_WEIGHT);
        } catch (DBException e) {
            e.printStackTrace();
        }

        modelMap.put("cargo", cargo);
        modelMap.put("vehicleList", vehicleList);

        return new MVCModel("/jsp/sendRequestCargo.jsp", modelMap);
    }
}
