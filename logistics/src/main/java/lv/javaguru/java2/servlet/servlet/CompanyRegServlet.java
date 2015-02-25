package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.servlet.model.RegistrationMethods;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CompanyRegServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String regNumber = request.getParameter("regNumber");
        String regAddress = request.getParameter("regAddress");
        String actualAddress = request.getParameter("actualAddress");
        String bank = request.getParameter("bank");
        String iban = request.getParameter("iban");
        String country = request.getParameter("country");
        String type = request.getParameter("type");

        List<String> parameters = new ArrayList<String>();
        parameters.add(name);
        parameters.add(regNumber);
        parameters.add(regAddress);
        parameters.add(actualAddress);
        parameters.add(bank);
        parameters.add(iban);
        parameters.add(country);
        parameters.add(type);

        RegistrationMethods reg = new RegistrationMethods();
        try {
            reg.companyCreate(parameters);
        } catch (DBException e) {
            e.printStackTrace();
        }

        request.setAttribute("name", name);
        request.setAttribute("regNumber", regNumber);
        request.setAttribute("regAddress", regAddress);
        request.setAttribute("actualAddress", actualAddress);
        request.setAttribute("bank", bank);
        request.setAttribute("iban", iban);
        request.setAttribute("country", country);
        request.setAttribute("type", type);

        RequestDispatcher view = request.getRequestDispatcher("/jsp/companyreg.jsp");
        view.forward(request, response);
    }
}
