package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.javaguru.java2.servlet.model.URL;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
//import lv.javaguru.java2.PasswordHash;



/**
 * Created by andre on 17.02.2015.
 */
@Component
@URL(value="/userRegResult")
public class UserRegResultController implements MVCController {

    @Autowired
    private UserDAO userDAO;


    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {


        String login = request.getParameter("login");
        String password = request.getParameter("password");
//        password = PasswordHash.createHash(password));
        //user.setPassword(PasswordHash.createHash(user.getPassword()));

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int companyid = Integer.parseInt(request.getParameter("companyid"));

        User userNew = createNewUserInDB(login, password, firstname, lastname, email, phone, companyid);



//        String message = "New User -" + login + "- created! :)";
//        MVCModel model = new MVCModel("/jsp/userreg.jsp", message);
        MVCModel model = new MVCModel("/jsp/userRegResult.jsp", userNew);
        return model;
    }


    protected User createNewUserInDB(String login, String password, String firstname, String lastname, String email, String phone, int companyid) {
        User userNew = new User(login, password, firstname, lastname, email, phone, companyid);
        try {
            userDAO.create(userNew);
        } catch (DBException e) {
            System.out.println("Exception while creating new user UserRegResultController");
            e.printStackTrace();
        }
        return userNew;
    }


}

