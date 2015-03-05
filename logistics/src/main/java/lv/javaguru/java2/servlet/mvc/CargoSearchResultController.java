package lv.javaguru.java2.servlet.mvc;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Cargo;

@Component
@URL(value="/cargoSearchResult")
public class CargoSearchResultController implements MVCController {

    public static final Double MIN_WEIGHT = 0.0;
    public static final Double MAX_WEIGHT = 99.99;
    public static final String MIN_DATE = "1000-01-01";
    public static final String MAX_DATE = "9999-12-31";
    private String errorMessage;

    @Autowired
    @Qualifier("HibernateCargoDAO")
    private CargoDAO cargoDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("pageName", "CargoSearch");

        errorMessage = "";
        String type = request.getParameter("type");
        String weightFrom = request.getParameter("weightFrom");
        String weightTo = request.getParameter("weightTo");
        String loadDateFrom = request.getParameter("loadDateFrom");
        String loadDateTo = request.getParameter("loadDateTo");
        String unloadDateFrom = request.getParameter("unloadDateFrom");
        String unloadDateTo = request.getParameter("unloadDateTo");

        Double weightFromDouble = parseWeightValue(weightFrom);
        Double weightToDouble = parseWeightValue(weightTo);

        if(weightFromDouble == null) weightFromDouble = MIN_WEIGHT;
        if(weightToDouble == null) weightToDouble = MAX_WEIGHT;

        loadDateFrom = (isNotEmptyOrNull(loadDateFrom)) ? loadDateFrom : MIN_DATE;
        loadDateTo = (isNotEmptyOrNull(loadDateTo)) ? loadDateTo : MAX_DATE;
        unloadDateFrom = (isNotEmptyOrNull(unloadDateFrom)) ? unloadDateFrom : MIN_DATE;
        unloadDateTo = (isNotEmptyOrNull(unloadDateTo)) ? unloadDateTo : MAX_DATE;

        validateEnteredWeights(weightFromDouble, weightToDouble);
        validateEnteredDates(loadDateFrom, loadDateTo, "Load date");
        validateEnteredDates(unloadDateFrom, unloadDateTo, "Unload date");

        if(!errorMessage.isEmpty())
            return new MVCModel("/jsp/errorPage.jsp", errorMessage);

        List<Cargo> cargoList = getCargoesByParameters(type, weightFromDouble,
                weightToDouble, loadDateFrom, loadDateTo, unloadDateFrom, unloadDateTo);

        return new MVCModel("/jsp/cargoSearchResult.jsp", cargoList);
    }

    private Double parseWeightValue (String weight) {
        try {
            if (isNotEmptyOrNull(weight))
                return Double.parseDouble(weight);
        } catch (Exception e) {
            errorMessage += "Please enter correct weight value!<br/>";
        }
        return null;
    }

    private Boolean isNotEmptyOrNull(String string) {
        return !string.isEmpty() && string != null;
    }

    private void validateEnteredDates(String dateFrom, String dateTo, String fieldName) {
        if (stringToDate(dateFrom).before(stringToDate(MIN_DATE))
                || stringToDate(dateFrom).after(stringToDate(MAX_DATE))
                || stringToDate(dateTo).before(stringToDate(MIN_DATE))
                || stringToDate(dateTo).after(stringToDate(MAX_DATE)))
            errorMessage += "Date: "+ fieldName +" entered is invalid<br/>";
        else if (stringToDate(dateFrom).after(stringToDate(dateTo)))
            errorMessage += fieldName + ": second date can't be before first!<br/>";
    }

    private void validateEnteredWeights(Double weightFromDouble, Double weightToDouble) {
        if (weightFromDouble > MAX_WEIGHT || weightToDouble > MAX_WEIGHT ||
                weightFromDouble < MIN_WEIGHT || weightToDouble < MIN_WEIGHT)
            errorMessage += "Weight: The weight entered is invalid<br/>";
        else if (weightFromDouble > weightToDouble)
            errorMessage += "Weight: second number can't be less than first!<br/>";
    }

    private List<Cargo> getCargoesByParameters(String type, Double weightFromDouble, Double weightToDouble,
                                               String loadDateFrom, String loadDateTo, String unloadDateFrom, String unloadDateTo) {
        List<Cargo> cargoList = new ArrayList<Cargo>();
        try {
            cargoList = cargoDAO.getByParameters(type, weightFromDouble, weightToDouble, stringToDate(loadDateFrom),
                    stringToDate(loadDateTo), stringToDate(unloadDateFrom), stringToDate(unloadDateTo));
        } catch (DBException e) {
            errorMessage += "Database error!<br/>";
            e.printStackTrace();
        }
        return cargoList;
    }

    // TEMPORARY here (we need to do create easier way to work with date formats! )
    public java.util.Date stringToDate(String incomingDate) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formater.parse(incomingDate);
        } catch (ParseException e) {
            System.out.println("Invalid Date format in method stringToDate(). Should be yyyy-MM-dd");
            e.printStackTrace();
            return null;
        }
    }
}
