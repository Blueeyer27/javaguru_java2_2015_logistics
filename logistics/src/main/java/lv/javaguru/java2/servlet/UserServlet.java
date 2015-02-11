package lv.javaguru.java2.servlet;


import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.database.jdbc.VehicleDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Vehicle;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;




/**
 * Created by andre on 10.02.2015.
 */

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        // Set response content type
        resp.setContentType("text/html");

        // Prepare output html
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + "New User from DB:" + "</h1>");
        out.println("<h1>" + "=" + "</h1>");


        UserDAOImpl userDAO = new UserDAOImpl();
        //constructor in UserDOA class
        User userNew = new User("login1", "pass1", "FoAA", "Barsky", "fb33@email.com", "+371234567890", 11111L);
        try {
            userDAO.create(userNew);
        } catch (DBException e) {
            e.printStackTrace();
        }

        User userNewFromDB = null;

        try {
            userNewFromDB = userDAO.getById(userNew.getUserId());
        } catch (DBException e) {
            e.printStackTrace();
        }

        out.println("<h1>" + userNewFromDB.getUserId() + " " + userNewFromDB.getFirstName() + " " + userNewFromDB.getLastName() + " " + " " + userNewFromDB.getEMail() +  "</h1>");


        out.println("<h1>" + "." + "</h1>");
        out.println("<h1>" + "All Users from DB:" + "</h1>");
        out.println("<h1>" + "=" + "</h1>");


        List<User> users = null;
        try {
            users = new UserDAOImpl().getAll();
        } catch (DBException e) {
            e.printStackTrace();
        }

        for (User user : users) {
            out.println("<h1>" + user.getUserId() + " " + user.getFirstName() + " " + user.getLastName() + " " + " " + user.getEMail() + " " + users.size() + "</h1>");
            //System.out.println(user.getFirstName());
        }


    }

/*
    private User createUser(String login, String password, String firstName, String lastName,
                              String eMail, String phoneNumber, Long companyId) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEMail(eMail);
        user.setPhoneNumber(phoneNumber);
        user.setCompanyId(companyId);
        return user;
    }
*/
}
