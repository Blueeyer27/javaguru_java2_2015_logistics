package lv.javaguru.java2.servlet.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;

/**
 * Created by andre on 17.02.2015.
 */
@Component
@URL(value="/cargoReg")
public class CargoRegController implements MVCController {

    @Autowired
    private UserDAO userDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {

        List<User> userListFromDB = getUserListFromDatabase();

        MVCModel model = new MVCModel("/jsp/cargoreg.jsp", userListFromDB);
        return model;
    }



    protected List<User> getUserListFromDatabase() {
        List<User> userList = new ArrayList<User>();

        try {
            userList = userDAO.getAll();
        } catch (DBException e) {
            System.out.println("Exception while getting user list CargoRegController");
            e.printStackTrace();
        }
        return userList;
    }
}

