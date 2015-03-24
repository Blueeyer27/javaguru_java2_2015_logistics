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

@Controller
public class CompanyDeleteController {

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @RequestMapping(value = "companyDelete", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model;
        Long companyId = Long.parseLong(request.getParameter("companyId"));
        Company company = getCompanyFromDB(companyId);

        if(company.getUserList().size() == 0){
            deleteCompanyFromDB(companyId);
            String notification = "Company with Id = '" + companyId + "' was deleted!";
            model = new ModelAndView("redirect:manageCompanies");
            model.addObject("model" , notification);
        } else {
            model = new ModelAndView();
            model.setViewName("errorPage");
            model.addObject("model","You can not delete company with users! \n"
                    + "Users count in company '" + company.getName() + "' : "
                    + company.getUserList().size());
        }
        return model;
    }

    protected void deleteCompanyFromDB(Long companyId) {
        try {
            companyDAO.delete(companyId);
        } catch (DBException e) {
            System.out.println("Exception while getting company from DB " +
                    "in CompanyDeleteController.deleteCompanyFromDB()");
            e.printStackTrace();
        }
    }

    protected Company getCompanyFromDB(Long companyId) {
        Company company = null;
        try {
            company = companyDAO.getById(companyId);
        } catch (DBException e) {
            System.out.println("Exception while getting company from DB " +
                    "in CompanyDeleteController.getCompanyFromDB()");
            e.printStackTrace();
        }
        return company;
    }
}

