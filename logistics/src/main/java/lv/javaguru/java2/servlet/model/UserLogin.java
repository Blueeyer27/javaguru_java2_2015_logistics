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

    public boolean userLoginCheck(List<String> parameteres) throws DBException {
        String login = parameteres.get(0);
        String password = parameteres.get(1);

        List<User> users = userDAO.getAll();
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password))
                return true;
                break;
        }
        return false;
    }

    public User getUserByLogin(String login) throws DBException {
        User user = null;
        List<User> users = userDAO.getAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(login)) {
                user = users.get(i);
                break;
            }
        }
        return user;
    }
}
