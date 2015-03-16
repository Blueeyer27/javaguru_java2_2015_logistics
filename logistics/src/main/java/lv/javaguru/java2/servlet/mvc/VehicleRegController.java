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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@URL(value="/vehicleReg")
public class VehicleRegController implements MVCController {

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibValueDAO")
    private ValueDAO valueDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {

        List<User> userListFromDB = getUserListFromDatabase();

        List<Value> vehicleTypes = getVehicleTypes();

        Map<String, Object> modelHashMap = putItemToModelHasMap(userListFromDB, vehicleTypes);

        return new MVCModel("/jsp/vehiclereg.jsp", modelHashMap);
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

