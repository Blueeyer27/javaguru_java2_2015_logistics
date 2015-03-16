package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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

/**
 * Created by andre on 17.02.2015.
 */
@Component
@URL(value="/userRegResult")
public class UserRegResultController implements MVCController {

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {

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

        if (userExist != null )
            return new MVCModel("/jsp/errorPage.jsp", "LOGIN '" +login+ "' already EXIST! Sorry!");

        Long userId = createNewUserInDB(login, hashedPassword, firstname, lastname, email, phone, company);
        User userNewFromDB = getNewUserFromDB(userId);

        return new MVCModel("/jsp/userRegResult.jsp", userNewFromDB);
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

