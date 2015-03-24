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
public class UserDeleteController {

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @RequestMapping(value = "userDelete", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView model;
        Long userId = Long.parseLong(request.getParameter("userId"));
        User user = getUserFromDB(userId);

        if(user.getVehicleList().size() == 0 && user.getCargoList().size() == 0){
            deleteUserFromDB(userId);
            String notification = "User with Id = '" + userId + "' was deleted!";
            model = new ModelAndView("redirect:manageUsers");
            model.addObject("notification" , notification);
        } else {
            model = new ModelAndView();
            model.setViewName("errorPage");
            model.addObject("model","You can not delete user with cargoes or vehicles! \n"
                    + user.getFirstName() + " " + user.getFirstName() +
                    "'s cargoes count: " + user.getCargoList().size() + ", "
                    + "vehicles count: " + user.getVehicleList().size() + ".");
        }
        return model;
    }

    protected void deleteUserFromDB(Long userId) {
        try {
            userDAO.delete(userId);
        } catch (DBException e) {
            System.out.println("Exception while getting company from DB " +
                    "in UserDeleteController.deleteUserFromDB()");
            e.printStackTrace();
        }
    }

    protected User getUserFromDB(Long userId) {
        User user = null;
        try {
            user = userDAO.getById(userId);
        } catch (DBException e) {
            System.out.println("Exception while getting company from DB " +
                    "in UserDeleteController.getUserFromDB()");
            e.printStackTrace();
        }
        return user;
    }
}

