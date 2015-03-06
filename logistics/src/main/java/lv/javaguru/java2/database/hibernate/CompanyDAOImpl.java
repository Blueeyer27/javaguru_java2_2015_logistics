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

}
