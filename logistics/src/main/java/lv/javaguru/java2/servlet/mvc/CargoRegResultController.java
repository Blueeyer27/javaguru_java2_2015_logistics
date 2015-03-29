package lv.javaguru.java2.servlet.mvc;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.ValueDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Cargo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by andre on 20.02.2015.
 */

@Controller
public class CargoRegResultController {

    @Autowired
    @Qualifier("HibernateCargoDAO")
    private CargoDAO cargoDAO;

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibValueDAO")
    private ValueDAO valueDAO;

    @RequestMapping(value = "cargoRegResult", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();

        Long userid = Long.parseLong(request.getParameter("userid"));
        String type = request.getParameter("type");
        Double weight = Double.parseDouble(request.getParameter("weight"));
        String loadAddress = request.getParameter("loadaddress");
        String unloadAddress = request.getParameter("unloadaddress");
        Date loadDate = cargoDAO.stringToDate2(request.getParameter("loaddate"), 2);
        Date unloaDdate = cargoDAO.stringToDate2(request.getParameter("unloaddate"), 2);
        String status = null;

        try {
            status = valueDAO.lookupValue("Cargo Status", "Available");
        } catch (DBException e) {
            e.printStackTrace();
        }


        User user = null;
        try {
            user = userDAO.getById(userid);
        } catch (DBException e) {
            System.out.println("Exception while getting user from DB CargoRegResult");
            e.printStackTrace();
        }

        Long cargoId = createCargoInDatabase(user, type, weight, loadAddress,
                unloadAddress, loadDate, unloaDdate, status);

        Cargo cargo = getCreatedCargoFromDB(cargoId);

        model.setViewName("cargoRegResult");
        model.addObject("model",cargo);
        return model;
    }

    private Long createCargoInDatabase(User user, String type, Double weight,
                                        String loadAddress, String unloadAddress,
                                        Date loadDate, Date unloaDdate, String status) {
        Long id = null;
        Cargo cargoNew = new Cargo(user, type, weight, loadAddress,
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

