package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Vehicle;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 19.02.2015.
 */
@Component
@URL(value="/getallcompanywithuserlist")
public class GetAllCompanyWithUserListController implements MVCController {

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;


    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Company> companyList = new ArrayList<Company>();
        try {
            companyList = companyDAO.getAll();
        } catch (DBException e) {
            e.printStackTrace();
        }

        return new MVCModel("/jsp/getAllCompanyWithUserList.jsp", companyList);
    }
}
