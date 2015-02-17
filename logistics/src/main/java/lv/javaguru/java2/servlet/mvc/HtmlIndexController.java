package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by andre on 17.02.2015.
 */
public class HtmlIndexController implements MVCController {


    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        String message = "index";
        MVCModel model = new MVCModel("/index.html", message);
        return model;

    }

}
