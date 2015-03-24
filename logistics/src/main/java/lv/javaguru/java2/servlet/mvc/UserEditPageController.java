package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.ValueDAO;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserEditPageController {

    @Autowired
    @Qualifier("HibValueDAO")
    private ValueDAO valueDAO;

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @RequestMapping(value = "userEdit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Long userId = Long.parseLong(request.getParameter("userId"));
        User user = getUserById(userId);

        if(user == null) {
            model.setViewName("errorPage");
            model.addObject("model","Error! User not found in database..");
        } else {
            modelMap.put("user", user);
            modelMap.put("companies", getAllCompanies());
            model.setViewName("userEditPage");
            model.addObject("model",modelMap);
        }
        return model;
    }

    private List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<Company>();
        try {
            companies = companyDAO.getAllClientCompanies();
        } catch (DBException e) {
            System.out.println("Exception while getting companies in " +
                    "UserEditPageController.getAllCompanies()");
            e.printStackTrace();
        }
        return companies;
    }

    private List<String> getStringListOfValues(String type) {
        List<String> listOfValues = new ArrayList<String>();
        try {
            listOfValues = valueDAO.getLovStringValuesByType(type);
        } catch (DBException e) {
            e.printStackTrace();
            System.out.println("Exception while get List of Values " +
                    "from database in where type = " + type);
        }
        return listOfValues;
    }

    private User getUserById(Long userId) {
        User user = null;
        try {
            user = userDAO.getById(userId);
        } catch (DBException e) {
            System.out.println("Exception while get user from database in " +
                    "UserEditPageController.getUserById()");
            e.printStackTrace();
        }
        return user;
    }
}

