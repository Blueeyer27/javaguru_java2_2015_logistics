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
 * Created by user on 11.02.2015.
 */
public class VehicleRegServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");


        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String plateNumber = request.getParameter("platenumber");
        String trailerNumber = request.getParameter("trailernumber");
        String capacity = request.getParameter("capacity");

        //эту хрень тупо захардкодил
        String userid = "13";
        String status = "pending";


        List<String> parameters = new ArrayList<String>();

        parameters.add(userid);
        parameters.add(name);
        parameters.add(type);
        parameters.add(plateNumber);
        parameters.add(trailerNumber);
        parameters.add(capacity);
        parameters.add(status);


        RegistrationMethods reg = new RegistrationMethods();

        try {
            reg.vehicleCreate(parameters);
        } catch (DBException e) {
            e.printStackTrace();
        }

        //request.setAttribute("params", parameters);

        request.setAttribute("name", name);
        request.setAttribute("type", type);
        request.setAttribute("platenumber", plateNumber);
        request.setAttribute("trailernumber", trailerNumber);
        request.setAttribute("capacity", capacity);

        RequestDispatcher view = request.getRequestDispatcher("/jsp/vehiclereg.jsp");
        view.forward(request, response);

       /* ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/vehiclereg.jsp");
        requestDispatcher.forward(request, response);*/


    }
}
