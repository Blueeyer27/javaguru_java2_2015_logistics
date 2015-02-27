package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import lv.javaguru.java2.database.AgreementDAO;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.AgreementDAOImpl;
import lv.javaguru.java2.domain.Agreement;

@Component
@URL(value="/createAgreement")
public class CreateAgreementController implements MVCController {

    @Autowired
    private AgreementDAO agreementDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {

        Long cargoId = Long.parseLong(request.getParameter("cargoId"));
        Long vehicleId = Long.parseLong(request.getParameter("vehicleId"));
        String status = "New";


        Long agreementId = createNewAgreementInDB(cargoId, vehicleId, status);
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



    protected Long createNewAgreementInDB(Long cargoId, Long vehicleId, String status) {
        Agreement agreement = new Agreement(cargoId, vehicleId, status);
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

