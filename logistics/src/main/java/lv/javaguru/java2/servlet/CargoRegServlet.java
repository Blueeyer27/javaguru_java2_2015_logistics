package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
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


public class CargoRegServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Prepare output html
        PrintWriter out = response.getWriter();
        // Set response content type
        response.setContentType("text/html");
        out.println("<h1>" + "New Cargo" + "</h1>");


        String type = request.getParameter("type");
        String weight = request.getParameter("weight");
        String loadaddress = request.getParameter("loadaddress");
        String unloadaddress = request.getParameter("unloadaddress");
        String loaddate = request.getParameter("loaddate");
        String unloaddate = request.getParameter("unloaddate");

        //хардкод
        String userid = "333";
        String status = "pending";

        out.println("<h1>" + type + "</h1>");
        out.println("<h1>" + weight + "</h1>");
        out.println("<h1>" + loadaddress + "</h1>");
        out.println("<h1>" + unloadaddress + "</h1>");
        out.println("<h1>" + loaddate + "</h1>");
        out.println("<h1>" + unloaddate + "</h1>");


        List<String> parameters = new ArrayList<String>();

        parameters.add(userid);
        parameters.add(type);
        parameters.add(weight);
        parameters.add(loadaddress);
        parameters.add(unloadaddress);
        parameters.add(loaddate);
        parameters.add(unloaddate);
        parameters.add(status);

        RegistrationMethods reg = new RegistrationMethods();

        try {
            reg.cargoCreate(parameters);
        } catch (DBException e) {
            e.printStackTrace();
        }




        request.setAttribute("type", type);
        request.setAttribute("weight", weight);
        request.setAttribute("loadaddress", loadaddress);
        request.setAttribute("unloadaddress", unloadaddress);
        request.setAttribute("loaddate", loaddate);
        request.setAttribute("unloaddate", unloaddate);


        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/cargoreg.jsp");
        requestDispatcher.forward(request, response);


    }

}
