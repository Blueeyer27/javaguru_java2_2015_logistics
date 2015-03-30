package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.VehicleDAO;
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
import java.util.HashMap;
import java.util.Map;


/**
 * Created by andre on 29.03.2015.
 */


@Controller
public class resetPasswordController {

    public static final String PENDING = "PENDING";

    @Autowired
    @Qualifier("HibVehicleDAO")
    private VehicleDAO vehicleDAO;

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;


    @RequestMapping(value = "resetPassword", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        session.setAttribute("pageName", "resetPassword");

        return getModelAndView(request);


    }



    protected ModelAndView getModelAndView(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        Map<String, Object> modelHashMap = new HashMap<String, Object>();
        String info = "enter data, please!";
        String color = "green";


        if("POST".equals(request.getMethod())) {

            String oldpassword = request.getParameter("oldpassword");
            String newpassword1 = request.getParameter("newpassword1");
            String newpassword2 = request.getParameter("newpassword2");
            String login = request.getParameter("login");

            if(oldpassword.isEmpty() || newpassword1.isEmpty() || newpassword2.isEmpty() ) {
                info = "please, fill all data!";
                color = "red";
            }
            else if (!newpassword1.equals(newpassword2)) {
                info = "new password and confirm password - not equal!";
                color = "red";
            }
            else if (oldpassword.length()>15 || newpassword1.length()>15 || newpassword2.length()>15) {
                info = "password max length is 15 simbols!";
                color = "red";
            }

            else if (request.getParameter("login") != null) {

                User user = getUser(login);

                if (!BCrypt.checkpw(oldpassword, user.getPassword())) {
                    info = "old password incorrect!";
                    color = "red";
                }
                else {
                    updatePassword(newpassword1, user);
                    info = "DONE! You have succesfully changed password!";
                    color = "green";
                }
            }
        }


        modelHashMap.put("info", info);
        modelHashMap.put("color", color);
        model.setViewName("resetPassword");
        model.addObject("model", modelHashMap);
        return model;
    }




    private void updatePassword(String newpassword1, User user) {
        user.setPassword(BCrypt.hashpw(newpassword1, BCrypt.gensalt(12)));
        try {
            userDAO.update(user);
        } catch (DBException e) {
            System.out.println("Exception while update password -> ResetPasswordController");
            e.printStackTrace();
        }
    }




    private User getUser(String login) {

        User user =null;
        try {
            user = userDAO.getByLogin(login);
        } catch (DBException e) {
            System.out.println("Exception while getting user -> ResetPasswordController");
            e.printStackTrace();
        }
        return user;
    }




}
