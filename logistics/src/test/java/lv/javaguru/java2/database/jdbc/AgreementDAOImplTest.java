package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Agreement;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by andre on 07.02.2015.
 */

public class AgreementDAOImplTest extends DAOImplTest{

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private AgreementDAOImpl agreementDAO = new AgreementDAOImpl();


    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }



    @Test
    public void testCreate() throws DBException {
        Agreement agreement = createAgreement(111L, 222L, "ON");
        agreementDAO.create(agreement);

        Agreement agreementFromDB = agreementDAO.getById((agreement.getAgreementId()));
        assertNotNull(agreementFromDB);
        assertEquals(agreement.getAgreementId(), agreementFromDB.getAgreementId());
        assertEquals(agreement.getCargoId(), agreementFromDB.getCargoId());
        assertEquals(agreement.getVehicleId(), agreementFromDB.getVehicleId());
        assertEquals(agreement.getStatus(), agreementFromDB.getStatus());
    }

    @Test
    public void testMultipleAgreementCreation() throws DBException {
        List<Agreement> agreements = agreementDAO.getAll();
        Agreement agreement1 = createAgreement(1L, 2L, "ON");
        Agreement agreement2 = createAgreement(3L, 4L, "ON");
        agreementDAO.create(agreement1);
        agreementDAO.create(agreement2);
        assertEquals(agreementDAO.getAll().size(), agreements.size() + 2);
    }


    @Test
    public void testUpdate() throws DBException {
        Agreement agreement = createAgreement(5L, 6L, "ON");
        agreementDAO.create(agreement);
        Agreement agreementFromDB = agreementDAO.getById((agreement.getAgreementId()));
        assertEquals(agreement.getCargoId(), agreementFromDB.getCargoId());

        agreement.setCargoId(501);
        agreement.setStatus("OFF");
        agreementDAO.update(agreement);

        Agreement updatedAgreementFromDB = agreementDAO.getById((agreement.getAgreementId()));

        assertNotEquals(agreement.getCargoId(), agreementFromDB.getCargoId());
        assertNotEquals(agreement.getStatus(), agreementFromDB.getStatus());
        assertEquals(agreement.getCargoId(), updatedAgreementFromDB.getCargoId());
        assertEquals(agreement.getStatus(), updatedAgreementFromDB.getStatus());
    }

    @Test
    public void testMultipleAgreementDeletion() throws DBException {

        Agreement agreement1 = createAgreement(11L, 22L, "ON");
        Agreement agreement2 = createAgreement(33L, 44L, "ON");
        Agreement agreement3 = createAgreement(55L, 66L, "ON");
        agreementDAO.create(agreement1);
        agreementDAO.create(agreement2);
        agreementDAO.create(agreement3);

        int size = agreementDAO.getAll().size();

        agreementDAO.delete(agreement1.getAgreementId());
        assertEquals(null, agreementDAO.getById(agreement1.getAgreementId()));
        assertEquals(size - 1, agreementDAO.getAll().size());

        agreementDAO.delete(agreement2.getAgreementId());
        assertEquals(null, agreementDAO.getById(agreement2.getAgreementId()));
        assertEquals(size - 2, agreementDAO.getAll().size());

        agreementDAO.delete(agreement3.getAgreementId());
        assertEquals(null, agreementDAO.getById(agreement3.getAgreementId()));
        assertEquals(size - 3, agreementDAO.getAll().size());

    }

}
