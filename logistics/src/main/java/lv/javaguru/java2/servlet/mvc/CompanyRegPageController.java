package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.ValueDAOImpl;
import lv.javaguru.java2.domain.Value;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
@URL(value="/companyRegPage")
public class CompanyRegPageController implements MVCController {

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {

        List<Value> countries = new ArrayList<Value>();
        ValueDAOImpl valueDAO = new ValueDAOImpl();
        try {
            countries = valueDAO.getLovByType("Country");
        } catch (DBException e) {
            e.printStackTrace();
        }
        return new MVCModel("/jsp/companyregpage.jsp", countries);
    }
}

