package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component("HibernateUserDAO")
@Transactional
public class UserDAOImpl extends DAOImpl<User> implements UserDAO {

    @Override
    public User getByLogin(String login) throws DBException {
        return (User) getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("login", login)).uniqueResult();
    }
}
