package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManageCompaniesController {

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @RequestMapping(value = "manageCompanies", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Company> allCompanies = new ArrayList<Company>();
        String notification = request.getParameter("model");
        try {
            allCompanies = companyDAO.getAllClientCompanies();
        } catch (DBException e) {
            System.out.println("Exception while getting companies in  ManageCompaniesController");
            e.printStackTrace();
        }
        modelMap.put("allCompanies", allCompanies);
        modelMap.put("notification", notification);
        model.setViewName("manageCompanies");
        model.addObject("model",modelMap);
        return model;
    }
}
