package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CargoDAOImpl;
import lv.javaguru.java2.domain.Cargo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargoSearchResultController implements MVCController {

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        List<Cargo> cargoList = new ArrayList<Cargo>();

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
            for (int i = 0; i < cargoes.size(); i++) {
                cargoList.add(cargoes.get(i));
            }
        }
        MVCModel model = new MVCModel("/jsp/cargoSearchResult.jsp", cargoList);
        return model;
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
