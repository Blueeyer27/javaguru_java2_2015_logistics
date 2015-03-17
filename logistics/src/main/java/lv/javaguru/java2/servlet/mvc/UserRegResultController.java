package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Company;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import lv.javaguru.java2.servlet.model.URL;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by andre on 17.02.2015.
 */
@Controller
@URL(value="/userRegResult")
public class UserRegResultController  {

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @RequestMapping(value = "userRegResult", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();

        HttpSession session = request.getSession(true);
        session.setAttribute("pageName", "UserRegResult");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Long companyid = Long.parseLong(request.getParameter("companyid"));
        Company company = null;
        try {
            company = companyDAO.getById(companyid);
        } catch (DBException e) {
            e.printStackTrace();
        }

        User userExist = checkUserLoginExist(login);

        if (userExist != null ) {

            model.setViewName("errorPage");
            model.addObject("model","LOGIN '" +login+ "' already EXIST! Sorry!");
            return model;
        }
        Long userId = createNewUserInDB(login, hashedPassword, firstname, lastname, email, phone, company);
        User userNewFromDB = getNewUserFromDB(userId);

        model.setViewName("userRegResult");
        model.addObject("model",userNewFromDB);
        return model;
    }

    protected User checkUserLoginExist(String login) {
        User userExist = null;
        try {
            userExist = userDAO.getByLogin(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return userExist;
    }

    protected User getNewUserFromDB(Long userId) {
        User user = null;
        try {
            user = userDAO.getById(userId);
        } catch (DBException e) {
            System.out.println("Exception while getting user from DB UserRegResultController");
            e.printStackTrace();
        }
        return user;
    }

    protected Long createNewUserInDB(String login, String password, String firstname, String lastname, String email, String phone, Company company) {
        User user = new User(login, password, firstname, lastname, email, phone, company);
        Long id = null;
        try {
            userDAO.create(user);
            id = user.getUserId();
        } catch (DBException e) {
            System.out.println("Exception while creating new user UserRegResultController");
            e.printStackTrace();
        }
        return id;
    }
}

