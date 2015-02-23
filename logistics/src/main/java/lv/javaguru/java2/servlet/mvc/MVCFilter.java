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

        controllerMapping.put("/userReg", new UserRegController());
        controllerMapping.put("/userRegResult", new UserRegResultController());
        controllerMapping.put("/userLogin", new UserLoginController());


        controllerMapping.put("/cargoReg", new CargoRegController());
        controllerMapping.put("/cargoRegResult", new CargoRegResultController());
        controllerMapping.put("/cargoSearchResult", new CargoSearchResultController());
        controllerMapping.put("/sendRequestCargo", new SendRequestCargoController());

        controllerMapping.put("/vehicleSearchResult", new VehicleSearchResultController());
        controllerMapping.put("/vehiclereg", new VehicleRegController());
        controllerMapping.put("/vehicleregresult", new VehicleRegResultController());
        controllerMapping.put("/getallvehicles", new GetAllVehiclesController());
        controllerMapping.put("/sendRequestVehicle", new SendRequestVehicleController());

        controllerMapping.put("/createAgreement", new CreateAgreementController());
        controllerMapping.put("/agreementOverview", new AgreementOverviewController());
        controllerMapping.put("/processAgreement", new ProcessAgreementController());

        controllerMapping.put("/companyReg", new CompanyRegController());

    }

    public void doFilter(ServletRequest request,
                         ServletResponse response) throws IOException, ServletException {


    }


    @Override
    public void doFilter(ServletRequest request, 
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        String contextURI = req.getServletPath();

        if (controllerMapping.keySet().contains(contextURI)){

            MVCController controller = controllerMapping.get(contextURI);
            MVCModel model = controller.processRequest(req, resp);

            req.setAttribute("model", model.getData());

            ServletContext context = req.getServletContext();



            RequestDispatcher requestDispacher =
                    context.getRequestDispatcher(model.getView());
            requestDispacher.forward(req, resp);

        }
        else filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
    
}
