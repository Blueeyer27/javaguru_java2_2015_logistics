package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MVCController {

    ModelAndView processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException;
    
}
