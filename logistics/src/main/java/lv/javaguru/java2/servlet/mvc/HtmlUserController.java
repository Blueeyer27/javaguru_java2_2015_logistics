package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by andre on 17.02.2015.
 */
public class HtmlUserController implements MVCController {


    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        String message = "user";
        MVCModel model = new MVCModel("/html/userreg.html", message);
        return model;

    }

}
