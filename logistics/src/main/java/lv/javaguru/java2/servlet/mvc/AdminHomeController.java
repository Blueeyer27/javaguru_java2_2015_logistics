package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ValueDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminHomeController {

    @Autowired
    @Qualifier("HibValueDAO")
    private ValueDAO valueDAO;

    @RequestMapping(value = "admin", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        HttpSession session = request.getSession(true);
        session.setAttribute("pageName", "AdminLogin");
        User user = (User) (session.getAttribute("user"));
        String admin = getAdminValueFromDB();

        if (admin == null) {
            model.setViewName("errorPage");
            model.addObject("model", "Missing value for admin type in database.");
            return model;
        }
        fillModelForUser(user, model, admin);
        return model;
    }

    private void fillModelForUser(User user, ModelAndView model, String admin) {
        if (user == null) {
            // if no one logged in - go to admin login page
            model.setViewName("adminLoginPage");
        } else {
            if (user.getUserCompanyType().equals(admin)) {
                model.setViewName("adminProfile");
            } else {
                // if regular user logged in
                model.setViewName("errorPage");
                model.addObject("model", "You do not have sufficient permissions to access this page");
            }
        }
    }

    private String getAdminValueFromDB() {
        try {
            return valueDAO.lookupValue("Company Type", "admin");
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
