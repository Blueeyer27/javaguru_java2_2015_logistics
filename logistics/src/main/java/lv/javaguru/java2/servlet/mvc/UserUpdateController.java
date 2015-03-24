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

@Controller
public class UserUpdateController {

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @RequestMapping(value = "userUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model = new ModelAndView("redirect:manageUsers");
        Long userId = Long.parseLong(request.getParameter("userId"));
        Long updatedCompanyId = Long.parseLong(request.getParameter("company"));
        String updatedLogin = request.getParameter("login");
        String updatedName = request.getParameter("name");
        String updatedSurname = request.getParameter("surname");
        String updatedEmail = request.getParameter("eMail");
        String updatedPhone = request.getParameter("phoneNumber");

        Company company = getCompanyFromDB(updatedCompanyId);
        User user = getUserFromDB(userId);

        updateUserInDB(user, updatedLogin, updatedName, updatedSurname,
                updatedEmail, updatedPhone, company);
        String notification = "User with Id = '" + userId + "' was updated!";
        model.addObject("model" , notification);
        return model;
    }

    private User getUserFromDB(Long userId) {
        User user = null;
        try {
            user = userDAO.getById(userId);
        } catch (DBException e) {
            System.out.println("Exception while getting company from DB " +
                    "in UserUpdateController.getUserFromDB()");
            e.printStackTrace();
        }
        return user;
    }

    protected Company getCompanyFromDB(Long companyId) {
        Company company = null;
        try {
            company = companyDAO.getById(companyId);
        } catch (DBException e) {
            System.out.println("Exception while getting company from DB " +
                    "in UserUpdateController.getCompanyFromDB()");
            e.printStackTrace();
        }
        return company;
    }

    protected void updateUserInDB(User user, String login, String name, String surname,
                                  String eMail, String phone, Company company) {
        user.setLogin(login);
        user.setFirstName(name);
        user.setLastName(surname);
        user.setEMail(eMail);
        user.setPhoneNumber(phone);
        user.setCompany(company);
        try {
            userDAO.update(user);
        } catch (DBException e) {
            System.out.println("Exception while updating company " +
                    "in UserUpdateController.updateUserInDB()");
            e.printStackTrace();
        }
    }
}

