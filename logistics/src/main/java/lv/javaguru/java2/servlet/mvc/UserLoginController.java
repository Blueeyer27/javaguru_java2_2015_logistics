package lv.javaguru.java2.servlet.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lv.javaguru.java2.database.*;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.Vehicle;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by andre on 17.02.2015.
 */

@Controller
public class UserLoginController {

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibernateCargoDAO")
    private CargoDAO cargoDAO;

    @Autowired
    @Qualifier("HibVehicleDAO")
    private VehicleDAO vehicleDAO;

    @Autowired
    @Qualifier("HibValueDAO")
    private ValueDAO valueDAO;

    @RequestMapping(value = "userLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        HttpSession session = request.getSession(true);
        session.setAttribute("pageName", "UserLogin");
        String login = null;
        String password = null;
        User user = null;

        if ((session.getAttribute("user")) != null) {
            user = (User)session.getAttribute("user");
        } else {
            login = request.getParameter("login");
            password = request.getParameter("password");
            user = getUserIfExist(login, password);
        }

        if (user != null ) {
            setSessionAttributes(session, user);
            fillModelForUser(user, model);
        }
        else {
            model.setViewName("errorPage");
            model.addObject("model","Incorrect LOGIN '" +login+ "' or PASSWORD '"+password+"' entered. Sorry!");
        }
        return model;
    }

    private void fillModelForUser(User user, ModelAndView model) {
        String userType = user.getUserCompanyType();
        String transport = getCompanyTypeValue("transport");
        String cargo = getCompanyTypeValue("cargo");

        if (transport != null && cargo != null) {
            Map<String, Object> modelHashMap = new HashMap<String, Object>();
            modelHashMap.put("user", user);

            if (userType.equals(transport)) {
                List<Vehicle> vehicleList = getVehicleListFromDB(user);
                modelHashMap.put("vehicleList", vehicleList);
                model.setViewName("transportUserProfile");
                model.addObject("model",modelHashMap);
            } else if (userType.equals(cargo)) {
                List<Cargo> cargoList = getCargoListFromDB(user);
                modelHashMap.put("cargoList", cargoList);
                model.setViewName("cargoUserProfile");
                model.addObject("model",modelHashMap);
            } else {
                model.setViewName("errorPage");
                model.addObject("model","Unknown user type: " + userType);
            }
        } else {
            model.setViewName("errorPage");
            model.addObject("model","Needed company types is missing in database. Sorry!");
        }
    }

    private String getCompanyTypeValue(String type) {
        String value = null;
        try {
            value = valueDAO.lookupValue("Company Type", type);
        } catch (DBException e) {
            System.out.println("Exception while getting company type '" + type +
                    "' in UserLoginController.getCompanyTypeValue()");
            e.printStackTrace();
        }
        return value;
    }

    private void setSessionAttributes(HttpSession session, User user) {
        session.setAttribute("user", user);
        session.setAttribute("userType", user.getUserCompanyType());
    }

    protected List<Vehicle> getVehicleListFromDB(User user) {
        List<Vehicle> vehicleList = null;
        try {
            vehicleList = vehicleDAO.getUserVehiclesByStatus(user, valueDAO.lookupValue("Vehicle Status", "Available"));
        } catch (DBException e) {
            e.printStackTrace();
        }
        return vehicleList;
    }

    protected List<Cargo> getCargoListFromDB(User user) {
        List<Cargo> cargoList =null;
        try {
            cargoList = cargoDAO.getUserCargoesByStatus(user, valueDAO.lookupValue("Cargo Status", "Available"));
        } catch (DBException e) {
            e.printStackTrace();
        }
        return cargoList;
    }

    protected User getUserIfExist(String login, String password) {
        try {
            User user = userDAO.getByLogin(login);
            if (user != null && user.getLogin().equals(login) && BCrypt.checkpw(password, user.getPassword())) {
                return user;
            }
        } catch (DBException e) {
            System.out.println("Exception while getting user UserLoginController.getUserIfExist()");
            e.printStackTrace();
        }
        return null;
    }
}
