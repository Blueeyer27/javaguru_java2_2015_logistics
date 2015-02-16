package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CargoDAOImpl;
import lv.javaguru.java2.domain.Cargo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CargoSearchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CargoDAOImpl cargoDAO = new CargoDAOImpl();
        String type = request.getParameter("type");
        Double weightFrom = Double.parseDouble(request.getParameter("weightFrom"));
        Double weightTo = Double.parseDouble(request.getParameter("weightTo"));
        String loadDateFrom = request.getParameter("loadDateFrom");
        String loadDateTo = request.getParameter("loadDateTo");
        String unloadDateFrom = request.getParameter("unloadDateFrom");
        String unloadDateTo = request.getParameter("unloadDateTo");

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        List<Cargo> cargoes = null;

        try {
            cargoes = cargoDAO.getByParameters(type, weightFrom, weightTo, stringToDate(loadDateFrom),
                    stringToDate(loadDateTo), stringToDate(unloadDateFrom), stringToDate(unloadDateTo));
        } catch (DBException e) {
            e.printStackTrace();
        }

        if (cargoes.size() > 0) {
            out.println("<h1>" + "Search result (total found:" + cargoes.size() + ")</h1>");
            for (int i = 0; i < cargoes.size(); i++) {
                out.print("<h3>");
                out.print("No#" + i + ": ");
                out.print("id = " + cargoes.get(i).getCargoId());
                out.print("; vehicle type = " + cargoes.get(i).getVehicleType());
                out.print("; weight = " + cargoes.get(i).getWeight());
                out.print("; load address = " + cargoes.get(i).getLoadAddress());
                out.print("; unload address = " + cargoes.get(i).getUnloadAddress());
                out.print("; load date = " + cargoes.get(i).getLoadDate());
                out.print("; unload date = " + cargoes.get(i).getUnloadDate());
                out.println("</h3>");
            }
        } else {
            out.println("<h1>" + "No cargoes matches Your parameters!" + "</h1>");
        }
    }

    // TEMPORARY here (we need to do create easier way to work with date formats! )
    public java.util.Date stringToDate(String incomingDate) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = formater.parse(incomingDate);
            return date;
        } catch (ParseException e) {
            System.out.println("Invalid Date format in method stringToDate(). Should be yyyy-MM-dd");
            e.printStackTrace();
            return null;
        }
    }
}
