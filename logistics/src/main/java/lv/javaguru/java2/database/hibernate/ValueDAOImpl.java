package lv.javaguru.java2.database.hibernate;


import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ValueDAO;
import lv.javaguru.java2.domain.Value;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component("HibValueDAO")
@Transactional
public class ValueDAOImpl extends DAOImpl<Value> implements ValueDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Value> getLovByType(String type) throws DBException {
        return null;
    }



}
