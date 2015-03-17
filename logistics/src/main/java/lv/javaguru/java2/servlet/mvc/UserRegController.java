package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by andre on 17.02.2015.
 */
@Controller
public class UserRegController {

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @RequestMapping(value = "userReg", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        List<Company> companyList = getCompanyListFromDB();

        model.setViewName("userreg");
        model.addObject("model",companyList);

        return model;
    }

    protected List<Company> getCompanyListFromDB() {
        List<Company> companyList = null;
        try {
            companyList = companyDAO.getAll();
        } catch (DBException e) {
            System.out.println("Exception while getting companyList from DB UserRegController");
            e.printStackTrace();
        }
        return companyList;
    }
}

