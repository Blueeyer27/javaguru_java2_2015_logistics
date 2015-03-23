package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
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
        ModelAndView model = new ModelAndView("redirect:manageCompanies");
        Long companyId = Long.parseLong(request.getParameter("companyId"));
        deleteCompanyFromDB(companyId);
        String notification = "Company with Id = '" + companyId + "' was deleted!";
        model.addObject("model" , notification);
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
}

