package lv.javaguru.java2.servlet.mvc;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lv.javaguru.java2.database.VehicleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import lv.javaguru.java2.servlet.model.URL;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VehicleSearchResultController {

    public static final Double MIN_WEIGHT = 0.0;
    public static final Double MAX_WEIGHT = 99.99;
    private String errorMessage;

    @Autowired
    @Qualifier("HibVehicleDAO")
    private VehicleDAO vehicleDAO;

    @RequestMapping(value = "vehicleSearchResult", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("pageName", "VehicleSearch");
        ModelAndView model = new ModelAndView();

        List<Vehicle> vehicles = null;

        errorMessage = "";
        String type = request.getParameter("type");
        String capacityFrom = request.getParameter("capacityFrom");
        String capacityTo = request.getParameter("capacityTo");

        // fill empty fields with default values
        Double capacityFromDouble = MIN_WEIGHT;
        Double capacityToDouble = MAX_WEIGHT;

        try {
            if (isNotEmptyOrNull(capacityFrom))
                capacityFromDouble = Double.parseDouble(capacityFrom);
            if (isNotEmptyOrNull(capacityTo))
                capacityToDouble = Double.parseDouble(capacityTo);
        } catch (Exception e) {
            errorMessage += "Error: Please enter correct capacity values!<br/>";
        }

        validateCapacityValues(capacityFromDouble, capacityToDouble);

        if(!errorMessage.isEmpty()) {

            model.setViewName("errorPage");
            model.addObject("model",errorMessage);
            return model;
        }

        vehicles = getVehiclesByParameters(type, capacityFromDouble, capacityToDouble);
        model.setViewName("vehicleSearchResult");
        model.addObject("model",vehicles);
        return model;
    }

    private Boolean isNotEmptyOrNull(String string) {
        return !string.isEmpty() && string != null;
    }

    private void validateCapacityValues(Double capacityFromDouble, Double capacityToDouble) {
        if (capacityFromDouble > MAX_WEIGHT || capacityToDouble > MAX_WEIGHT ||
                capacityFromDouble < MIN_WEIGHT || capacityToDouble < MIN_WEIGHT)
            errorMessage += "Error: Capacity: The weight entered is invalid<br/>";
        else if (capacityFromDouble > capacityToDouble)
            errorMessage += "Error: Capacity: second number can't be less than first!<br/>";
    }

    protected List<Vehicle> getVehiclesByParameters(String type, Double capacityFromDouble,
                                                  Double capacityToDouble) {
        try {
            return vehicleDAO.getByParameters(type, capacityFromDouble, capacityToDouble);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
