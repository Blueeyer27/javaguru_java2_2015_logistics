package lv.javaguru.java2.servlet.mvc;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.servlet.model.URL;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CargoDAOImpl;
import lv.javaguru.java2.domain.Cargo;

@Component
@URL(value="/cargoSearchResult")
public class CargoSearchResultController implements MVCController {

    public static final Double MIN_WEIGHT = 0.0;
    public static final Double MAX_WEIGHT = 99.99;
    public static final String MIN_DATE = "1000-01-01";
    public static final String MAX_DATE = "9999-12-31";

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        String errorMessage = "";
        List<Cargo> cargoList = new ArrayList<Cargo>();
        CargoDAOImpl cargoDAO = new CargoDAOImpl();
        String type = request.getParameter("type");
        String weightFrom = request.getParameter("weightFrom");
        String weightTo = request.getParameter("weightTo");
        String loadDateFrom = request.getParameter("loadDateFrom");
        String loadDateTo = request.getParameter("loadDateTo");
        String unloadDateFrom = request.getParameter("unloadDateFrom");
        String unloadDateTo = request.getParameter("unloadDateTo");

        // fill empty fields with default values
        Double weightFromDouble = MIN_WEIGHT;
        Double weightToDouble = MAX_WEIGHT;
        try {
            if (!weightFrom.isEmpty() && weightFrom != null)
                weightFromDouble = Double.parseDouble(weightFrom);
            if (!weightTo.isEmpty() && weightTo != null)
                weightToDouble = Double.parseDouble(weightTo);
        } catch (Exception e) {
            errorMessage += "Please enter correct weight values!<br/>";
        }
        loadDateFrom = (!loadDateFrom.isEmpty() && loadDateFrom != null) ? loadDateFrom : MIN_DATE;
        loadDateTo = (!loadDateTo.isEmpty() && loadDateTo != null) ? loadDateTo : MAX_DATE;
        unloadDateFrom = (!unloadDateFrom.isEmpty() && unloadDateFrom != null) ? unloadDateFrom : MIN_DATE;
        unloadDateTo = (!unloadDateTo.isEmpty() && unloadDateTo != null) ? unloadDateTo : MAX_DATE;

        // data validation
        if (weightFromDouble > MAX_WEIGHT || weightToDouble > MAX_WEIGHT ||
                weightFromDouble < MIN_WEIGHT || weightToDouble < MIN_WEIGHT)
            errorMessage += "Weight: The weight entered is invalid<br/>";
        else if (weightFromDouble > weightToDouble)
            errorMessage += "Weight: second number can't be less than first!<br/>";

        if (stringToDate(loadDateFrom).before(stringToDate(MIN_DATE))
                || stringToDate(loadDateFrom).after(stringToDate(MAX_DATE))
                || stringToDate(loadDateTo).before(stringToDate(MIN_DATE))
                || stringToDate(loadDateTo).after(stringToDate(MAX_DATE)))
            errorMessage += "Date: The Load date entered is invalid<br/>";
        else if (stringToDate(loadDateFrom).after(stringToDate(loadDateTo)))
            errorMessage += "Load date: second date can't be before first!<br/>";

        if (stringToDate(unloadDateFrom).before(stringToDate(MIN_DATE))
                || stringToDate(unloadDateFrom).after(stringToDate(MAX_DATE))
                || stringToDate(unloadDateTo).before(stringToDate(MIN_DATE))
                || stringToDate(unloadDateTo).after(stringToDate(MAX_DATE)))
            errorMessage += "Date: The Unload date entered is invalid<br/>";
        else if (stringToDate(unloadDateFrom).after(stringToDate(unloadDateTo)))
            errorMessage += "Unload date: second date can't be before first!<br/>";

        if(!errorMessage.isEmpty())
            return new MVCModel("/jsp/errorPage.jsp", errorMessage);

        List<Cargo> cargoes = null;
        try {
            cargoes = cargoDAO.getByParameters(type, weightFromDouble, weightToDouble, stringToDate(loadDateFrom),
                    stringToDate(loadDateTo), stringToDate(unloadDateFrom), stringToDate(unloadDateTo));
        } catch (DBException e) {
            e.printStackTrace();
        }

        if (cargoes != null) {
            for (int i = 0; i < cargoes.size(); i++) {
                cargoList.add(cargoes.get(i));
            }
        }
        return new MVCModel("/jsp/cargoSearchResult.jsp", cargoList);
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
