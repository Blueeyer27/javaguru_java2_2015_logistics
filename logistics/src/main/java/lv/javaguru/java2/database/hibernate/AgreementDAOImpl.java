package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.AgreementDAO;
import lv.javaguru.java2.domain.Agreement;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("HibAgreementDAO")
@Transactional
public class AgreementDAOImpl extends DAOImpl<Agreement> implements AgreementDAO {

}
