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
public class CompanyUpdateController {

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @RequestMapping(value = "companyUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model = new ModelAndView("redirect:manageCompanies");
        Long companyId = Long.parseLong(request.getParameter("companyId"));

        String updatedName = request.getParameter("name");
        String updatedRegNumber = request.getParameter("regNumber");
        String updatedRegAddress = request.getParameter("regAddress");
        String updatedActualAddress = request.getParameter("actualAddress");
        String updatedBank = request.getParameter("bank");
        String updatedIban = request.getParameter("iban");
        String updatedCountry = request.getParameter("country");
        String updatedType = request.getParameter("type");

        Company company = getCompanyFromDB(companyId);
        updateCompanyInDB(company, updatedName, updatedRegNumber, updatedRegAddress, updatedActualAddress,
                updatedBank, updatedIban, updatedCountry, updatedType);
        String notification = "Company with Id = '" + companyId + "' was updated!";
        model.addObject("model" , notification);
        return model;
    }

    protected Company getCompanyFromDB(Long companyId) {
        Company company = null;
        try {
            company = companyDAO.getById(companyId);
        } catch (DBException e) {
            System.out.println("Exception while getting company from DB " +
                    "in CompanyUpdateController.getCompanyFromDB()");
            e.printStackTrace();
        }
        return company;
    }

    protected void updateCompanyInDB(Company company, String name, String regNumber,
                                     String regAddress, String actualAddress, String bank,
                                     String iban, String country, String type) {
        company.setName(name);
        company.setRegNumber(regNumber);
        company.setRegAddress(regAddress);
        company.setActualAddress(actualAddress);
        company.setBank(bank);
        company.setIban(iban);
        company.setCountry(country);
        company.setType(type);
        try {
            companyDAO.update(company);
        } catch (DBException e) {
            System.out.println("Exception while updating company " +
                    "in CompanyUpdateController.updateCompanyInDB()");
            e.printStackTrace();
        }
    }
}

