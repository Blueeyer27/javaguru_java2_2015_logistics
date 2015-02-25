package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import lv.javaguru.java2.servlet.model.URL;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

/**
 * Created by andre on 17.02.2015.
 */
@Component
@URL(value="/userRegResult")
public class UserRegResultController implements MVCController {


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

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int companyid = Integer.parseInt(request.getParameter("companyid"));

        UserDAOImpl userDAO = new UserDAOImpl();
        User userNew = new User(login, password, firstname, lastname, email, phone, companyid);

        try {
            userDAO.create(userNew);
        } catch (DBException e) {
            System.out.println("Exception while creating new user UserRegResultController");
            e.printStackTrace();
        }




//        String message = "New User -" + login + "- created! :)";
//        MVCModel model = new MVCModel("/jsp/userreg.jsp", message);
        MVCModel model = new MVCModel("/jsp/userRegResult.jsp", userNew);
        return model;

    }
}

