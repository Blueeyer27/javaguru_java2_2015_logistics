package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.model.UserLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 17.02.2015.
 */
public class UserLoginController implements MVCController {


    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {



        if (request.getMethod().equals("POST"))
            System.out.println("Metod POST ispolzuetsa");
        else
            if (request.getMethod().equals("GET"))
                System.out.println("Metod GET ispolzuetsa");




        String login = request.getParameter("login");
        String password = request.getParameter("password");

        boolean exist = false;

        UserDAOImpl userDAO = new UserDAOImpl();

        List<User> users = null;
        try {
            users = userDAO.getAll();
        } catch (DBException e) {
            System.out.println("Exception while getting all users UserLoginController");
            e.printStackTrace();
        }

        User user = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(login) && users.get(i).getPassword().equals(password)) {
                user = users.get(i);
                exist = true;
                break;
            }
        }

        MVCModel model = new MVCModel("");

        if (exist)
            model = new MVCModel("/jsp/userlogin.jsp", user);
        else
            model = new MVCModel("/jsp/userloginnot.jsp", login);


        return model;

    }
}

