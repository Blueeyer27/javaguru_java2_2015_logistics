package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.*;
import lv.javaguru.java2.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by andre on 07.02.2015.
 */
@WebAppConfiguration
public class AgreementDAOImplTest extends DAOImplTest{

    @Autowired
    PlatformTransactionManager transactionManager;


    @Autowired
    @Qualifier("HibAgreementDAO")

    private AgreementDAO agreementDAO;
    @Autowired
    @Qualifier("HibVehicleDAO")
    private VehicleDAO vehicleDAO;

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @Autowired
    @Qualifier("HibernateCargoDAO")
    private CargoDAO cargoDAO;

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }


    @Test
    @Transactional
    public void testCreate() throws DBException {

        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
        companyDAO.create(company);
        userDAO.create(user);

        Cargo cargo = new Cargo(user, "tilt", 21.5, "LV Maskavas", "RU Moscow",
                cargoDAO.stringToDate("09/02/2015"), cargoDAO.stringToDate("15/02/2015"), "ready");
        cargoDAO.create(cargo);

        Vehicle vehicle = new Vehicle(user, "MAN" , "tilt", "GG4107", "DZ855", 18.5, "PENDING");
        vehicleDAO.create(vehicle);

        Agreement agreement = new Agreement(cargo, vehicle, "NEW");
        agreementDAO.create(agreement);

        Agreement agreementFromDB = agreementDAO.getById((agreement.getAgreementId()));
        assertNotNull(agreementFromDB);
        assertEquals(agreement.getAgreementId(), agreementFromDB.getAgreementId());
        assertEquals(agreement.getCargoId(), agreementFromDB.getCargoId());
        assertEquals(agreement.getVehicleId(), agreementFromDB.getVehicleId());
        assertEquals(agreement.getStatus(), agreementFromDB.getStatus());
    }


    @Test
    @Transactional
    public void testMultipleAgreementCreation() throws DBException {

        Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
        companyDAO.create(company);
        User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
        userDAO.create(user);

        Cargo cargo = new Cargo(user, "tilt", 21.5, "LV Maskavas", "RU Moscow",
                cargoDAO.stringToDate("09/02/2015"), cargoDAO.stringToDate("15/02/2015"), "ready");
        cargoDAO.create(cargo);

        Vehicle vehicle = new Vehicle(user, "MAN" , "tilt", "GG4107", "DZ855", 18.5, "PENDING");
        vehicleDAO.create(vehicle);

        List<Agreement> agreements = agreementDAO.getAll();
        Agreement agreement1 = new Agreement(cargo, vehicle, "NEW");
        Agreement agreement2 = new Agreement(cargo, vehicle, "NEW");
        agreementDAO.create(agreement1);
        agreementDAO.create(agreement2);
        assertEquals(agreementDAO.getAll().size(), agreements.size() + 2);
    }


    @Test
    @Transactional
    public void testUpdate() throws DBException {

        TransactionTemplate tt = new TransactionTemplate(transactionManager);
        final AtomicLong agreementId = new AtomicLong();
        final AtomicLong cargoId_1 = new AtomicLong();
        final AtomicLong cargoId_2 = new AtomicLong();

        tt.execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus paramTransactionStatus) {

                Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                        "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
                User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
                Cargo cargo = new Cargo(user, "tilt", 21.5, "LV Maskavas", "RU Moscow",
                        cargoDAO.stringToDate("09/02/2015"), cargoDAO.stringToDate("15/02/2015"), "ready");
                Cargo cargoUpdated = new Cargo(user, "tilt", 26.5, "RU Rublevka", "RU Moscow",
                        cargoDAO.stringToDate("09/02/2015"), cargoDAO.stringToDate("15/02/2015"), "ready");
                Vehicle vehicle = new Vehicle(user, "MAN" , "tilt", "GG4107", "DZ855", 18.5, "PENDING");
                Agreement agreement = new Agreement(cargo, vehicle, "NEW");

                try {
                    companyDAO.create(company);
                    userDAO.create(user);
                    cargoDAO.create(cargo);
                    vehicleDAO.create(vehicle);
                    agreementDAO.create(agreement);
                    cargoDAO.create(cargoUpdated);
                } catch (DBException e) {
                    throw new RuntimeException(e);
                }

                Agreement agreementFromDB = null;
                try {
                    agreementFromDB = agreementDAO.getById((agreement.getAgreementId()));
                } catch (DBException e) {
                    e.printStackTrace();
                }

                assertEquals(agreement.getCargo().getCargoId(), agreementFromDB.getCargo().getCargoId());
                assertEquals(agreement.getStatus(), agreementFromDB.getStatus());

                agreementId.set(agreement.getAgreementId());
                cargoId_1.set(agreement.getCargo().getCargoId());

                agreement.setStatus("SUPER NEW");
                agreement.setCargo(cargoUpdated);

                try {
                    agreementDAO.update(agreement);
                } catch (DBException e) {
                    throw new RuntimeException(e);
                }

            }
        });


        Agreement agreement = agreementDAO.getById(agreementId.get());
        cargoId_2.set(agreement.getCargo().getCargoId());

        assertNotEquals(cargoId_1.get(), cargoId_2.get());

        System.out.println("============================================ " + cargoId_1.get());
        System.out.println("============================================ " + cargoId_2.get());

    }


            @Test
            @Transactional
            public void testMultipleAgreementDeletion() throws DBException {

                Company company = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                        "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvija", "Transporter");
                companyDAO.create(company);

                User user = new User("qwerty", "pass1", "Foo", "Bar", "qwerty@email.com", "+371111167890", company);
                userDAO.create(user);

                Cargo cargo = new Cargo(user, "tilt", 21.5, "LV Maskavas", "RU Moscow",
                        cargoDAO.stringToDate("09/02/2015"), cargoDAO.stringToDate("15/02/2015"), "ready");
                cargoDAO.create(cargo);

                Vehicle vehicle = new Vehicle(user, "MAN", "tilt", "GG4107", "DZ855", 18.5, "PENDING");
                vehicleDAO.create(vehicle);

                Agreement agreement1 = new Agreement(cargo, vehicle, "NEW");
                Agreement agreement2 = new Agreement(cargo, vehicle, "OLD");
                Agreement agreement3 = new Agreement(cargo, vehicle, "PAST");
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
