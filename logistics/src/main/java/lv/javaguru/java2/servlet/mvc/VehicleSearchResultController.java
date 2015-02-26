package lv.javaguru.java2.servlet.mvc;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.VehicleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.javaguru.java2.servlet.model.URL;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Vehicle;

@Component
@URL(value="/vehicleSearchResult")
public class VehicleSearchResultController implements MVCController {

    public static final Double MIN_WEIGHT = 0.0;
    public static final Double MAX_WEIGHT = 99.99;
    private String errorMessage;

    @Autowired
    private VehicleDAO vehicleDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
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

        if(!errorMessage.isEmpty())
            return new MVCModel("/jsp/errorPage.jsp", errorMessage);

        vehicles = getVehiclesByParameters(type, capacityFromDouble, capacityToDouble);

        return new MVCModel("/jsp/vehicleSearchResult.jsp", vehicles);
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

    private List<Vehicle> getVehiclesByParameters(String type, Double capacityFromDouble,
                                                  Double capacityToDouble) {
        try {
            return vehicleDAO.getByParameters(type, capacityFromDouble, capacityToDouble);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
