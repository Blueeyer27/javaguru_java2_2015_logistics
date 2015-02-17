package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MVCController {

    MVCModel processRequest(HttpServletRequest request,
                            HttpServletResponse response) throws IOException;
    
}
