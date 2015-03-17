package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import lv.javaguru.java2.servlet.model.URL;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Company;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by andre on 17.02.2015.
 */
@Controller
public class CompanyRegController {

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @RequestMapping(value = "companyReg", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        String name = request.getParameter("name");
        String regNumber = request.getParameter("regNumber");
        String regAddress = request.getParameter("regAddress");
        String actualAddress = request.getParameter("actualAddress");
        String bank = request.getParameter("bank");
        String iban = request.getParameter("iban");
        String country = request.getParameter("country");
        String type = request.getParameter("type");
        Long companyId = createNewCompanyInDB(name, regNumber, regAddress, actualAddress, bank, iban, country, type);
        Company companyNewFromDB = getNewCompanyFromDB(companyId);

        model.setViewName("companyreg");
        model.addObject("model",companyNewFromDB);
        return model;
    }

    protected Company getNewCompanyFromDB(Long companyId) {
        Company company = null;
        try {
            company = companyDAO.getById(companyId);
        } catch (DBException e) {
            System.out.println("Exception while getting company from DB CompanyRegResultController");
            e.printStackTrace();
        }
        return company;
    }

    protected Long createNewCompanyInDB(String name, String regNumber, String regAddress, String actualAddress,
                                        String bank, String iban, String country, String type) {
        Company companyNew = new Company(name, regNumber, regAddress, actualAddress, bank, iban, country, type);
        Long id = null;
        try {
            companyDAO.create(companyNew);
            id = companyNew.getCompanyId();
        } catch (DBException e) {
            System.out.println("Exception while creating new company mvc/CompanyRegController");
            e.printStackTrace();
        }
        return id;
    }
}

