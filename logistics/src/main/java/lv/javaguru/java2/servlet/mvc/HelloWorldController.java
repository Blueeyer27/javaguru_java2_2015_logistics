/*package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.servlet.model.URL;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
@URL(value="/hello")

public class HelloWorldController implements MVCController {

    @Override
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        String message = "Hello from MVC!";
        return new MVCModel("/jsp/helloWorld.jsp", message);
    }
    
}
*/
