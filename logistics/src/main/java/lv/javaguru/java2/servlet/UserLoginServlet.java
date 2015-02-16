package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.model.UserLogin;


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

public class UserLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Prepare output html
        PrintWriter out = response.getWriter();
        // Set response content type
        response.setContentType("text/html");


        String login = request.getParameter("login");
        String password = request.getParameter("password");

        out.println("<h1>" + login + "</h1>");
        out.println("<h1>" + password + "</h1>");

        List<String> parameters = new ArrayList<String>();

        parameters.add(login);
        parameters.add(password);

        UserLogin userLogin = new UserLogin();

        boolean exist = false;
        try {
            exist = userLogin.userLoginCheck(parameters);
        } catch (DBException e) {
            e.printStackTrace();
        }
        out.println("<h1>" + "exist=" + String.valueOf(exist) + "</h1>");

        request.setAttribute("login", login);
        request.setAttribute("password", password);

        ServletContext servletContext = getServletContext();
        if (exist){
            User user = null;
            try {
                user = userLogin.getUserByLogin(login);
            } catch (DBException e) {
                e.printStackTrace();
            }
            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("lastName", user.getLastName());
            request.setAttribute("eMail", user.getEMail());
            request.setAttribute("company", user.getCompanyId());
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/userlogin.jsp");
            requestDispatcher.forward(request, response);
        }
        else {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/userloginnot.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
