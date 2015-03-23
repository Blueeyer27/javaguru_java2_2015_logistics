package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ValueDAO;
import lv.javaguru.java2.domain.Company;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component("HibCompanyDAO")
@Transactional
public class CompanyDAOImpl extends DAOImpl<Company> implements CompanyDAO {

    @Autowired
    @Qualifier("HibValueDAO")
    private ValueDAO valueDAO;

    @Override
    public List<Company> getAllClientCompanies() throws DBException {
        List<String> companyTypes = getClientCompanyTypes();
        List companies = getCurrentSession().createCriteria(Company.class)
                .add(Restrictions.in("type", companyTypes))
                .list();
        return companies;
    }

    private List<String> getClientCompanyTypes() throws DBException {
        List<String> companyTypes = new ArrayList<String>();
        companyTypes.add(valueDAO.lookupValue("Company Type", "cargo"));
        companyTypes.add(valueDAO.lookupValue("Company Type", "transport"));
        return companyTypes;
    }
}
