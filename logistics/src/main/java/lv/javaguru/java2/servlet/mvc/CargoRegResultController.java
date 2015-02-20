package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CargoDAOImpl;
import lv.javaguru.java2.domain.Cargo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by andre on 20.02.2015.
 */
public class CargoRegResultController implements MVCController {


    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {



        if (request.getMethod().equals("POST"))
            System.out.println("Metod POST ispolzuetsa");
        else
            if (request.getMethod().equals("GET"))
                System.out.println("Metod GET ispolzuetsa");



        CargoDAOImpl cargoDAO = new CargoDAOImpl();


        int userid = Integer.parseInt(request.getParameter("userid"));
        String type = request.getParameter("type");
        Double weight = Double.parseDouble(request.getParameter("weight"));
        String loadaddress = request.getParameter("loadaddress");
        String unloadaddress = request.getParameter("unloadaddress");
        Date loaddate = cargoDAO.stringToDate2(request.getParameter("loaddate"), 2);
        Date unloaddate = cargoDAO.stringToDate2(request.getParameter("unloaddate"), 2);
        String status = "PENDING";


        Cargo cargoNew = new Cargo(userid, type, weight, loadaddress, unloadaddress, loaddate, unloaddate, status);

        try {
            cargoDAO.create(cargoNew);
        } catch (DBException e) {
            System.out.println("Exception while creating new cargo CargoRegResultController");
            e.printStackTrace();
        }


//        String message = "New User -" + login + "- created! :)";
//        MVCModel model = new MVCModel("/jsp/userreg.jsp", message);
        MVCModel model = new MVCModel("/jsp/cargoRegResult.jsp", cargoNew);
        return model;

    }
}

