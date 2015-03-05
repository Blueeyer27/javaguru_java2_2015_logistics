package lv.javaguru.java2.servlet.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.AgreementDAO;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Agreement;

@Component
@URL(value="/agreementOverview")
public class AgreementOverviewController implements MVCController {

    @Autowired
    @Qualifier("HibAgreementDAO")
    private AgreementDAO agreementDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request, 
                                   HttpServletResponse response) {

        List<Agreement> agreementList = new ArrayList<Agreement>();

        try {
            agreementList = agreementDAO.getAll();
        } catch (DBException e) {
            System.out.println("Exception while getting agreementList AgreementOverviewController");
            e.printStackTrace();
        }

        return new MVCModel("/jsp/agreementOverview.jsp", agreementList);
    }
}
