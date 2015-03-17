package lv.javaguru.java2.servlet.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by user on 19.02.2015.
 */
@Controller
public class GetAllVehiclesController {

    @Autowired
    @Qualifier("HibVehicleDAO")
    private VehicleDAO vehicleDAO;

    @RequestMapping(value = "getallvehicles", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ModelAndView model = new ModelAndView();
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        try {
            vehicleList = vehicleDAO.getAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
        model.setViewName("getallvehicles");
        model.addObject("model",vehicleList);
        return model;
    }
}
