package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 17.02.2015.
 */
@Component
@URL(value="/userReg")
public class UserRegController implements MVCController {

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {


        List<Company> companyList = getCompanyListFromDB();

        return new MVCModel("/jsp/userreg.jsp", companyList);
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

