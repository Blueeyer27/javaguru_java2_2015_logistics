package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;

import lv.javaguru.java2.database.jdbc.VehicleDAOImpl;
import lv.javaguru.java2.domain.Vehicle;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VehicleSearchResultController implements MVCController {

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();

        VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
        String type = request.getParameter("type");
        Double capacityFrom = Double.parseDouble(request.getParameter("capacityFrom"));
        Double capacityTo = Double.parseDouble(request.getParameter("capacityTo"));

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        List<Vehicle> vehicles = null;

        try {
            vehicles = vehicleDAO.getByParameters(type, capacityFrom, capacityTo);
            } catch (DBException e) {
            e.printStackTrace();
        }

        //WHY ??? andrey
/*
        if (vehicles.size() > 0) {
            for (int i = 0; i < vehicles.size(); i++) {
                vehicleList.add(vehicles.get(i));
            }
        }
*/

        MVCModel model = new MVCModel("/jsp/vehicleSearchResult.jsp", vehicles);
        return model;
    }

}
