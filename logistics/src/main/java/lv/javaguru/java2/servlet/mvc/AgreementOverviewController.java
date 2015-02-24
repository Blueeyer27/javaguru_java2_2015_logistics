package lv.javaguru.java2.servlet.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.AgreementDAOImpl;
import lv.javaguru.java2.domain.Agreement;

@Component
public class AgreementOverviewController implements MVCController {

    @Override
    public MVCModel processRequest(HttpServletRequest request, 
                                   HttpServletResponse response) {

        Map<String, Object> modelHashMap = new HashMap<String, Object> ();

        AgreementDAOImpl agreementDAO = new AgreementDAOImpl();

        List<Agreement> agreementList = new ArrayList<Agreement>();

        try {
            agreementList = agreementDAO.getAll();
        } catch (DBException e) {
            System.out.println("Exception while getting agreementList AgreementOverviewController");
            e.printStackTrace();
        }

        MVCModel model = new MVCModel("/jsp/agreementOverview.jsp", agreementList);
        return model;
    }
}
