package lv.javaguru.java2.servlet.mvc;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.database.jdbc.CargoDAOImpl;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Cargo;

/**
 * Created by andre on 20.02.2015.
 */
@Component
@URL(value="/cargoRegResult")
public class CargoRegResultController implements MVCController {

    public static final String PENDING = "PENDING";

    @Autowired
    private CargoDAO cargoDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {

        Long userid = Long.parseLong(request.getParameter("userid"));
        String type = request.getParameter("type");
        Double weight = Double.parseDouble(request.getParameter("weight"));
        String loadAddress = request.getParameter("loadaddress");
        String unloadAddress = request.getParameter("unloadaddress");
        Date loadDate = cargoDAO.stringToDate2(request.getParameter("loaddate"), 2);
        Date unloaDdate = cargoDAO.stringToDate2(request.getParameter("unloaddate"), 2);
        String status = PENDING;

        Long cargoId = createCargoInDatabase(userid, type, weight, loadAddress,
                unloadAddress, loadDate, unloaDdate, status);

        Cargo cargo = getCreatedCargoFromDB(cargoId);

        MVCModel model = new MVCModel("/jsp/cargoRegResult.jsp", cargo);
        return model;
    }

    private Long createCargoInDatabase(Long userid, String type, Double weight,
                                        String loadAddress, String unloadAddress,
                                        Date loadDate, Date unloaDdate, String status) {
        Long id = null;
        Cargo cargoNew = new Cargo(userid, type, weight, loadAddress,
                unloadAddress, loadDate, unloaDdate, status);
        try {
            cargoDAO.create(cargoNew);
            id = cargoNew.getCargoId();
        } catch (DBException e) {
            System.out.println("Exception while creating new cargo in CargoRegResultController");
            e.printStackTrace();
        }
        return id;
    }

    private Cargo getCreatedCargoFromDB(Long cargoId) {
        Cargo cargoFromDB = null;
        try {
            cargoFromDB = cargoDAO.getById(cargoId);
        } catch (DBException e) {
            System.out.println("Exception while getting new cargo from DB in CargoRegResultController");
            e.printStackTrace();
        }
        return cargoFromDB;
    }
}

