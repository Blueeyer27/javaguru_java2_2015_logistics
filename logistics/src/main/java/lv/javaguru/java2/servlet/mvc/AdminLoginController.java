package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.*;
import lv.javaguru.java2.domain.User;
import org.mindrot.jbcrypt.BCrypt;
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
public class AdminLoginController {

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibValueDAO")
    private ValueDAO valueDAO;

    @RequestMapping(value = "adminLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        HttpSession session = request.getSession(true);
        session.setAttribute("pageName", "AdminLogin");
        User user = getUserFromRequest(request);;
        checkUserTypeAndPassword(user, model, session);
        return model;
    }

    private void checkUserTypeAndPassword(User user, ModelAndView model, HttpSession session) {
        model.setViewName("adminProfile");
        if (user != null && isAdmin(user)) {
            model.setViewName("adminProfile");
            setSessionAttributes(session, user);
        } else {
            model.setViewName("errorPage");
            model.addObject("model","Wrong login or password entered. Try again!");
        }
    }

    private boolean isAdmin(User user) {
        String admin = null;
        try {
            admin = valueDAO.lookupValue("Company Type", "admin");
        } catch (DBException e) {
            System.out.println("Exception in AdminLoginController.isAdmin()");
            e.printStackTrace();
        }
        return user.getUserCompanyType().equals(admin);
    }

    private void setSessionAttributes(HttpSession session, User user) {
        session.setAttribute("user", user);
        session.setAttribute("userType", user.getUserCompanyType());
    }

    protected User getUserFromRequest(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        return getUserFromDBIfExists(login, password);
    }

    private User getUserFromDBIfExists(String login, String password) {
        try {
            User user = userDAO.getByLogin(login);
            if (user != null && user.getLogin().equals(login) && BCrypt.checkpw(password, user.getPassword())) {
                return user;
            }
        } catch (DBException e) {
            System.out.println("Exception while getting user AdminLoginController.getUserIfExist()");
            e.printStackTrace();
        }
        return null;
    }
}
