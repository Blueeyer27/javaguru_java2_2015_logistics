package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component("HibernateUserDAO")
@Transactional
public class UserDAOImpl extends DAOImpl<User> implements UserDAO {

    @Override
    public User getById(Long id) throws DBException {
//        return super.getById(id);
        return (User) getCurrentSession().get(User.class, id);

    }

    @Override
    public void delete(Long id) throws DBException {
//        super.delete(id);
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        session.delete(user);

    }

    @Override
    public List<User> getAll() throws DBException {
//        return super.getAll();
        return getCurrentSession().createCriteria(User.class).list();
    }
}
