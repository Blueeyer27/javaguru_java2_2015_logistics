package lv.javaguru.java2.servlet.mvc;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.model.URL;

/**
 * Created by andre on 17.02.2015.
 */

@Component
@URL(value="/userLogin")
public class UserLoginController implements MVCController {

    @Autowired
    private UserDAO userDAO;


    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {
        printLogInfo(request);

        String login = request.getParameter("login");
        String password = request.getParameter("password");


        List<User> users = getUserListFromDatabase();

        User user = getUserIfExist(users, login, password);

        MVCModel model = null;

        if (user != null)
            model = new MVCModel("/jsp/userlogin.jsp", user);
        else
            model = new MVCModel("/jsp/userloginnot.jsp", login);

        return model;
    }




    protected User getUserIfExist(List<User> users,
                                  String login,
                                  String password) {
        for (User user : users) {
            if (user.getLogin().equals(login)
                    && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }




    protected List<User> getUserListFromDatabase() {
        List<User> users = null;
        try {
            users = userDAO.getAll();
        } catch (DBException e) {
            System.out.println("Exception while getting all users UserLoginController");
            e.printStackTrace();
        }
        return users;
    }

    protected void printLogInfo(HttpServletRequest request) {
        if (request.getMethod().equals("POST"))
            System.out.println("Metod POST ispolzuetsa");
        else
        if (request.getMethod().equals("GET"))
            System.out.println("Metod GET ispolzuetsa");

    }

}
