package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 17.02.2015.
 */
@Component
@URL(value="/vehicleReg")
public class VehicleRegController implements MVCController {

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {

        List<User> userListFromDB = getUserListFromDatabase();

        return new MVCModel("/jsp/vehiclereg.jsp", userListFromDB);
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
}

