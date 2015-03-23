package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ValueDAO;
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
public class CompanyEditPageController {

    @Autowired
    @Qualifier("HibValueDAO")
    private ValueDAO valueDAO;

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @RequestMapping(value = "companyEdit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Long companyId = Long.parseLong(request.getParameter("companyId"));
        Company company = getCompanyById(companyId);

        if(company == null) {
            model.setViewName("errorPage");
            model.addObject("model","Error! Company not found in database..");
        } else {
            modelMap.put("company", company);
            modelMap.put("countries", getStringListOfValues("Country"));
            modelMap.put("companyTypes", getStringListOfValues("Company Type"));
            model.setViewName("companyEditPage");
            model.addObject("model",modelMap);
        }
        return model;
    }

    private List<String> getStringListOfValues(String type) {
        List<String> listOfValues = new ArrayList<String>();
        try {
            listOfValues = valueDAO.getLovStringValuesByType(type);
        } catch (DBException e) {
            e.printStackTrace();
            System.out.println("Exception while get List of Values " +
                    "from database in where type = " + type);
        }
        return listOfValues;
    }

    private Company getCompanyById(Long companyId) {
        Company company = null;
        try {
            company = companyDAO.getById(companyId);
        } catch (DBException e) {
            System.out.println("Exception while get company from database in " +
                    "EditCompanyInfoController.getCompanyById()");
            e.printStackTrace();
        }
        return company;
    }
}

