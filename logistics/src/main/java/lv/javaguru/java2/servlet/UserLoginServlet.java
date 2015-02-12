package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.servlet.model.UserLogin;
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


public class UserLoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Prepare output html
        PrintWriter out = resp.getWriter();
        // Set response content type
        resp.setContentType("text/html");


        String login = req.getParameter("login");
        String password = req.getParameter("password");

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


        ServletContext servletContext = getServletContext();
        if (exist){
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/userlogin.jsp");
            requestDispatcher.forward(req, resp);
        }
        else {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/userloginnot.jsp");
            requestDispatcher.forward(req, resp);
        }



    }

}
