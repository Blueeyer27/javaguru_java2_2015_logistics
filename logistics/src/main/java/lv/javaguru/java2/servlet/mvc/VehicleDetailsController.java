package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.domain.Vehicle;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 20.02.2015.
 */

@Controller
public class VehicleDetailsController {

    @Autowired
    @Qualifier("HibVehicleDAO")
    private VehicleDAO vehicleDAO;

    @RequestMapping(value = "showDetailsVehicle", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

       ModelAndView model = new ModelAndView();
       String id = request.getParameter("id");
       long vehicleId = Long.parseLong(id);
        Vehicle vehicle = null;

        try {
          vehicle = vehicleDAO.getById(vehicleId);
        } catch (DBException e) {
            e.printStackTrace();
        }
        model.setViewName("vehicleDetails");
        model.addObject("model",vehicle);

        return model;
    }
}
