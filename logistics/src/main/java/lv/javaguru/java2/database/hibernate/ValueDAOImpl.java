package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ValueDAO;
import lv.javaguru.java2.domain.Value;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component("HibValueDAO")
@Transactional
public class ValueDAOImpl extends DAOImpl<Value> implements ValueDAO {

    @Override
    public List<Value> getLovByType(String type) throws DBException {
        List listOfValues = getCurrentSession().createCriteria(Value.class)
                .add(Restrictions.eq("type", type))
                .addOrder(Order.asc("value"))
                .list();
        return listOfValues;
    }
}
