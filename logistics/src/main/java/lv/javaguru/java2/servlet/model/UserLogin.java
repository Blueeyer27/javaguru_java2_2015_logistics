package lv.javaguru.java2.servlet.model;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import java.util.List;

/**
 * Created by user on 11.02.2015.
 */
public class UserLogin {

    private UserDAOImpl userDAO = new UserDAOImpl();
    private boolean exist=false;

    public boolean userLoginCheck(List<String> parameteres) throws DBException {
        String login = parameteres.get(0);
        String password = parameteres.get(1);

        List<User> users = userDAO.getAll();
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) exist=true;
        }

        return exist;
    }


}
