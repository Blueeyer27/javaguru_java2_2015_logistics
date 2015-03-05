package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Company;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component("HibCompanyDAO")
@Transactional
public class CompanyDAOImpl extends DAOImpl<Company> implements CompanyDAO {

    @Override
    public Company getById(Long id) throws DBException {
        return (Company) getCurrentSession().get(Company.class, id);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Company agreement = (Company) session.get(Company.class, id);
        session.delete(agreement);
    }

    @Override
    public List<Company> getAll() throws DBException {
        return getCurrentSession().createCriteria(Company.class).list();
    }

}
