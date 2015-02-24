package lv.javaguru.java2.servlet.mvc;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.VehicleDAOImpl;
import lv.javaguru.java2.domain.Vehicle;

@Component
@URL(value="/vehicleSearchResult")
public class VehicleSearchResultController implements MVCController {

    public static final Double MIN_WEIGHT = 0.0;
    public static final Double MAX_WEIGHT = 99.99;


    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {

        VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
        List<Vehicle> vehicles = null;

        String errorMessage = "";
        String type = request.getParameter("type");
        String capacityFrom = request.getParameter("capacityFrom");
        String capacityTo = request.getParameter("capacityTo");


        // fill empty fields with default values
        Double capacityFromDouble = MIN_WEIGHT;
        Double capacityToDouble = MAX_WEIGHT;
        try {
            if (!capacityFrom.isEmpty() && capacityFrom != null)
                capacityFromDouble = Double.parseDouble(capacityFrom);
            if (!capacityTo.isEmpty() && capacityTo != null)
                capacityToDouble = Double.parseDouble(capacityTo);
        } catch (Exception e) {
            errorMessage += "BLIN! Please enter correct capacity values!<br/>";
        }


        // data validation
        if (capacityFromDouble > MAX_WEIGHT || capacityToDouble > MAX_WEIGHT ||
                capacityFromDouble < MIN_WEIGHT || capacityToDouble < MIN_WEIGHT)
            errorMessage += "BLIN! Capacity: The weight entered is invalid<br/>";
        else if (capacityFromDouble > capacityToDouble)
            errorMessage += "BLIN! Capacity: second number can't be less than first!<br/>";

        if(!errorMessage.isEmpty())
            return new MVCModel("/jsp/errorPage.jsp", errorMessage);




        try {
            vehicles = vehicleDAO.getByParameters(type, capacityFromDouble, capacityToDouble);
            } catch (DBException e) {
            e.printStackTrace();
        }


        MVCModel model = new MVCModel("/jsp/vehicleSearchResult.jsp", vehicles);
        return model;
    }

}
