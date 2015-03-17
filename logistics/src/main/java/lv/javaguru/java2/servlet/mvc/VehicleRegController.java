package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.ValueDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Value;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VehicleRegController {

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibValueDAO")
    private ValueDAO valueDAO;

    @RequestMapping(value = "vehicleReg", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        List<User> userListFromDB = getUserListFromDatabase();
        List<Value> vehicleTypes = getVehicleTypes();
        Map<String, Object> modelHashMap = putItemToModelHasMap(userListFromDB, vehicleTypes);

        model.setViewName("vehiclereg");
        model.addObject("model",modelHashMap);
        return model;
    }

    protected Map<String, Object> putItemToModelHasMap(List<User> users, List<Value> vehicleTypes) {
        Map<String, Object> modelHashMap = new HashMap<String, Object>();
        modelHashMap.put("users", users);
        modelHashMap.put("vehicleTypes", vehicleTypes);
        return modelHashMap;
    }

    protected List<User> getUserListFromDatabase() {
        List<User> userList = new ArrayList<User>();

        try {
            userList = userDAO.getAll();
        } catch (DBException e) {
            System.out.println("Exception while getting user list VehicleRegController");
            e.printStackTrace();
        }
        return userList;
    }

    protected List<Value> getVehicleTypes() {
        List<Value> vahicleTypes = new ArrayList<Value>();

        try {
            vahicleTypes = valueDAO.getLovByType("Vehicle Type");
        } catch (DBException e) {
            System.out.println("Exception in getVehicleTypes()");
            e.printStackTrace();
        }
        return vahicleTypes;
    }
}

