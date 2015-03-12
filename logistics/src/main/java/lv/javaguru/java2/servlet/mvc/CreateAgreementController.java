package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import lv.javaguru.java2.database.AgreementDAO;
import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.Vehicle;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.AgreementDAOImpl;
import lv.javaguru.java2.domain.Agreement;

@Component
@URL(value="/createAgreement")
public class CreateAgreementController implements MVCController {

    @Autowired
    @Qualifier("HibAgreementDAO")
    private AgreementDAO agreementDAO;

    @Autowired
    @Qualifier("HibernateCargoDAO")
    private CargoDAO cargoDAO;

    @Autowired
    @Qualifier("HibVehicleDAO")
    private VehicleDAO vehicleDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {

        Long cargoId = Long.parseLong(request.getParameter("cargoId"));
        Long vehicleId = Long.parseLong(request.getParameter("vehicleId"));
        String status = "New";

        Cargo cargo = null;
        try {
            cargo = cargoDAO.getById(cargoId);
        } catch (DBException e) {
            System.out.println("Exception while getting user from DB CargoRegResult");
            e.printStackTrace();
        }

        Vehicle vehicle = null;
        try {
            vehicle = vehicleDAO.getById(vehicleId);
        } catch (DBException e) {
            System.out.println("Exception while getting user from DB CargoRegResult");
            e.printStackTrace();
        }


        Long agreementId = createNewAgreementInDB(cargo, vehicle, status);
        Agreement agreementNewFromDB = getNewAgreementFromDB(agreementId);


        return new MVCModel("/jsp/newAgreement.jsp", agreementNewFromDB);
    }


    protected Agreement getNewAgreementFromDB(Long agreementId) {
        Agreement agreement = null;
        try {
            agreement = agreementDAO.getById(agreementId);
        } catch (DBException e) {
            System.out.println("Exception while getting agreement from DB CreateAgreementController");
            e.printStackTrace();
        }
        return agreement;
    }



    protected Long createNewAgreementInDB(Cargo cargo, Vehicle vehicle, String status) {
        Agreement agreement = new Agreement(cargo, vehicle, status);
        Long id = null;
        try {
            agreementDAO.create(agreement);
            id = agreement.getAgreementId();
        } catch (DBException e) {
            System.out.println("Exception while creating new agreement in CreateAgreementController");
            e.printStackTrace();
        }
        return id;
    }
}

