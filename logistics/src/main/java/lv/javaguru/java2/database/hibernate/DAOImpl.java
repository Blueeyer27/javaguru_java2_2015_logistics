package lv.javaguru.java2.database.hibernate;

import java.util.List;
import lv.javaguru.java2.database.BaseDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.DBConnection;
import lv.javaguru.java2.domain.Value;
import lv.javaguru.java2.domain.Agreement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DAOImpl<T> extends DBConnection implements BaseDAO<T> {

    @Autowired
    public SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(T type) throws DBException {
        getCurrentSession().persist(type);
    }

    @Override
    public T getById(Long id) throws DBException {
        return (T) getCurrentSession().get(Value.class, id);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        T type = (T) session.get(Value.class, id);
        session.delete(type);
    }

    @Override
    public void update(T type) throws DBException {
        getCurrentSession().update(type);
    }

    @Override
    public List<T> getAll() throws DBException {
        return getCurrentSession().createCriteria(Value.class).list();
    }
}
