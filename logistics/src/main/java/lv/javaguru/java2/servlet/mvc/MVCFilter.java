package lv.javaguru.java2.servlet.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MVCFilter implements Filter {

    private Map<String, MVCController> controllerMapping;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        controllerMapping = new HashMap<String, MVCController>();
        controllerMapping.put("/hello", new HelloWorldController());
        controllerMapping.put("/cargoSearch", new CargoSearchController());
        controllerMapping.put("/cargoSearchResult", new CargoSearchResultController());
    }

    @Override
    public void doFilter(ServletRequest request, 
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        String contextURI = req.getServletPath();
        System.out.println("contextURI " + contextURI);
        MVCController controller = controllerMapping.get(contextURI);
        MVCModel model = controller.processRequest(req, resp);
        req.setAttribute("model", model.getData());
        ServletContext context = req.getServletContext();
        RequestDispatcher requestDispacher =
                context.getRequestDispatcher(model.getView());
        requestDispacher.forward(req, resp);
    }

    @Override
    public void destroy() {

    }
    
}
