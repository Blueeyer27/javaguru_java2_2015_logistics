package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.model.RegistrationMethods;




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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Prepare output html
        PrintWriter out = response.getWriter();
        // Set response content type
        response.setContentType("text/html");
        out.println("<h1>" + "New User" + "</h1>");


        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        //хардкод
        String companyid = "911";

        out.println("<h1>" + login + "</h1>");
        out.println("<h1>" + password + "</h1>");
        out.println("<h1>" + firstname + "</h1>");
        out.println("<h1>" + lastname + "</h1>");
        out.println("<h1>" + email + "</h1>");
        out.println("<h1>" + phone + "</h1>");


        List<String> parameters = new ArrayList<String>();

        parameters.add(login);
        parameters.add(password);
        parameters.add(firstname);
        parameters.add(lastname);
        parameters.add(email);
        parameters.add(phone);
        parameters.add(companyid);

        RegistrationMethods reg = new RegistrationMethods();

        try {
            reg.userCreate(parameters);
        } catch (DBException e) {
            e.printStackTrace();
        }


/*
        UserDAOImpl userDAO = new UserDAOImpl();
        //constructor in UserDOA class
        //User userNew = new User("andrey55", "password", "firstname", "lastname", "email", "phone", 123);
        User userNew = new User(login, password, firstname, lastname, email, phone, Integer.parseInt(companyid));
        try {
            userDAO.create(userNew);
        } catch (DBException e) {
            e.printStackTrace();
        }
*/


        request.setAttribute("login", login);
        request.setAttribute("password", password);
        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname", lastname);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);


        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/userreg.jsp");
        requestDispatcher.forward(request, response);


    }

}
