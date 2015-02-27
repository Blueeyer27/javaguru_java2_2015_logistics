package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.servlet.model.URL;
import org.springframework.stereotype.Component;

/**
 * Created by andre on 17.02.2015.
 */
@Component
@URL(value="/userReg")
public class UserRegController implements MVCController {

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {
        return new MVCModel("/jsp/userreg.jsp");
    }
}

