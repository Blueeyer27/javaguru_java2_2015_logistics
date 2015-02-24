package lv.javaguru.java2.servlet.mvc;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;

/**
 * Created by andre on 17.02.2015.
 */

@Component
public class UserLoginController implements MVCController {

    @Autowired
    private UserDAO userDAO;

    
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

//        MVCModel model = new MVCModel("/jsp/userLoginNot.jsp", login);


        return model;

    }
}

