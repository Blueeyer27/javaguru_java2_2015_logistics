package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Agreement;
import java.util.List;

/**
 * Created by andre on 07.02.2015.
 */
public interface AgreementDAO {

    void create(Agreement agreement) throws DBException;

    Agreement getById(Long id) throws DBException;

    List<Agreement> getAll() throws DBException;

    void delete(Long id) throws DBException;

    void update(Agreement agreement) throws DBException;

}
