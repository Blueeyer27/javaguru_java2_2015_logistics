package lv.javaguru.java2.database.hibernate;


import lv.javaguru.java2.database.AgreementDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ValueDAO;
import lv.javaguru.java2.domain.Agreement;
import lv.javaguru.java2.domain.Value;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component("HibAgreementDAO")
@Transactional
public class AgreementDAOImpl extends DAOImpl<Agreement> implements AgreementDAO {

    @Autowired
    private SessionFactory sessionFactory;




}
