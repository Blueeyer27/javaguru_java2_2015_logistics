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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AgreementOverviewController {

    @Autowired
    @Qualifier("HibAgreementDAO")
    private AgreementDAO agreementDAO;

    @RequestMapping(value = "agreementOverview", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        List<Agreement> agreementList = new ArrayList<Agreement>();

        try {
            agreementList = agreementDAO.getAll();
        } catch (DBException e) {
            System.out.println("Exception while getting agreementList AgreementOverviewController");
            e.printStackTrace();
        }
        model.setViewName("agreementOverview");
        model.addObject("model",agreementList);
        return model;
    }
}
