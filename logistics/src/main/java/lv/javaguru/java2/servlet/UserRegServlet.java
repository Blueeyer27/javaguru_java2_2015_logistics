package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.model.UserReg;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by andre on 10.02.2015.
 */


public class UserRegServlet extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Prepare output html
        PrintWriter out = resp.getWriter();
        // Set response content type
        resp.setContentType("text/html");
        out.println("<h1>" + "New User" + "</h1>");


        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        //хардкод
        String companyid = "911";

/*
        List<String> parameters = new ArrayList<String>();

        parameters.add(login);
        parameters.add(password);
        parameters.add(firstname);
        parameters.add(lastname);
        parameters.add(email);
        parameters.add(phone);
        parameters.add(companyid);

        UserReg userReg = new UserReg();

        try {
            userReg.userCreate(parameters);
        } catch (DBException e) {
            e.printStackTrace();
        }
*/


        UserDAOImpl userDAO = new UserDAOImpl();
        //constructor in UserDOA class
        //User userNew = new User("andrey55", "password", "firstname", "lastname", "email", "phone", 123);
        User userNew = new User(login, password, firstname, lastname, email, phone, Integer.parseInt(companyid));
        try {
            userDAO.create(userNew);
        } catch (DBException e) {
            e.printStackTrace();
        }


        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/userreg.jsp");
        requestDispatcher.forward(req, resp);


    }

}
