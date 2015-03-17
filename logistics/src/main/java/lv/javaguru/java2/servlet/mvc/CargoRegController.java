package lv.javaguru.java2.servlet.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by andre on 17.02.2015.
 */
@Controller
public class CargoRegController {

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @RequestMapping(value = "cargoReg", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        List<User> userListFromDB = getUserListFromDatabase();

        model.setViewName("cargoreg");
        model.addObject("model",userListFromDB);
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

