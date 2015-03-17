package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ValueDAO;
import lv.javaguru.java2.domain.Value;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CompanyRegPageController {

    @Autowired
    @Qualifier("HibValueDAO")
    private ValueDAO valueDAO;

    @RequestMapping(value = "companyRegPage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model = new ModelAndView();

        List<Value> countries = new ArrayList<Value>();
        try {
            countries = valueDAO.getLovByType("Country");
        } catch (DBException e) {
            e.printStackTrace();
        }
        model.setViewName("companyregpage");
        model.addObject("model",countries);
        return model;
    }
}

