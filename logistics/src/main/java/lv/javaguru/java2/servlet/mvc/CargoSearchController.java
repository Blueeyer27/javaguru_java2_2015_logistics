package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CargoSearchController implements MVCController {


    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {
        MVCModel model = new MVCModel("/jsp/cargoSearch.jsp", null);
        return model;
    }
}
