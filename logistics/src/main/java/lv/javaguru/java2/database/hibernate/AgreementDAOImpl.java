package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.AgreementDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Agreement;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component("HibAgreementDAO")
@Transactional
public class AgreementDAOImpl extends DAOImpl<Agreement> implements AgreementDAO {

    @Override
    public Agreement getById(Long id) throws DBException {
        return (Agreement) getCurrentSession().get(Agreement.class, id);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Agreement agreement = (Agreement) session.get(Agreement.class, id);
        session.delete(agreement);
    }

    @Override
    public List<Agreement> getAll() throws DBException {
        return getCurrentSession().createCriteria(Agreement.class).list();
    }

}
