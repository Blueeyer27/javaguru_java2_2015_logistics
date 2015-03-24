package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
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
public class ManageUsersController {

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @RequestMapping(value = "manageUsers", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<User> users;
        String title;
        String notification = request.getParameter("notification");

        if(request.getParameter("companyId") != null) {
            Long companyId = Long.parseLong(request.getParameter("companyId"));
            Company company = getCompanyById(companyId);
            users = new ArrayList<User>(company.getUserList());
            title = "Company's '" + company.getName() + "' users:";
        } else {
            users = getAllUsers();
            title = "All users:";
        }

        modelMap.put("users", users);
        modelMap.put("title", title);
        modelMap.put("notification", notification);
        model.setViewName("manageUsers");
        model.addObject("model",modelMap);
        return model;
    }

    private Company getCompanyById(Long companyId) {
        Company company = null;
        try {
            company = companyDAO.getById(companyId);
        } catch (DBException e) {
            System.out.println("Exception while getting company from DB " +
                    "in ManageUsersController.getCompanyById()");
            e.printStackTrace();
        }
        return company;
    }

    private List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            users = userDAO.getAll();
        } catch (DBException e) {
            System.out.println("Exception while getting users in  ManageUsersController.getAllUsers()");
            e.printStackTrace();
        }
        return users;
    }
}
