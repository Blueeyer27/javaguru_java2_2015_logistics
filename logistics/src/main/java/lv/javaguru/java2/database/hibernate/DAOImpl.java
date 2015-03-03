package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.BaseDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.DBConnection;
import lv.javaguru.java2.domain.Value;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class DAOImpl<T> extends DBConnection implements BaseDAO<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void create(T type) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(type);
    }

    @Override
    public T getById(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(Value.class, id);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        T type = (T) session.get(Value.class, id);
        session.delete(type);
    }

    @Override
    public void update(T type) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(type);
    }

    @Override
    public List<T> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Value.class).list();
    }
}