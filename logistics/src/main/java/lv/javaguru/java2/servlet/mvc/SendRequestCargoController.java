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
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SendRequestCargoController {

    @Autowired
    @Qualifier("HibernateCargoDAO")
    private CargoDAO cargoDAO;

    @Autowired
    @Qualifier("HibVehicleDAO")
    private VehicleDAO vehicleDAO;

    @RequestMapping(value = "sendRequestCargo", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
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

        model.setViewName("sendRequestCargo");
        model.addObject("model",modelMap);
        return model;
    }
}
