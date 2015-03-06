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

}
