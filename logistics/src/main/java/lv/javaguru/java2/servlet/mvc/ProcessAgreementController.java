package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.AgreementDAOImpl;
import lv.javaguru.java2.domain.Agreement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessAgreementController implements MVCController {


    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {

        Long agreementId = Long.parseLong(request.getParameter("agreementId"));
        Long cargoId = Long.parseLong(request.getParameter("cargoId"));
        Long vehicleId = Long.parseLong(request.getParameter("vehicleId"));
        String processType = request.getParameter("processType");
        String status = "ACCEPTED";
        Agreement agreement = new Agreement();
        AgreementDAOImpl agreementDAO = new AgreementDAOImpl();


        try {
            agreement = agreementDAO.getById(agreementId);
        } catch (DBException e) {
            System.out.println("Exception while agreementDAO.getById()  in ProcessAgreementController()");
            e.printStackTrace();
        }

        //here we need(or must) to update status also for Cargo and Vehicle items
        if (processType.equals("yes")) {

            agreement.setStatus(status);
            try {
                agreementDAO.update(agreement);
            } catch (DBException e) {
                System.out.println("Exception while agreementDAO.update()  in ProcessAgreementController()");
                e.printStackTrace();
            }
        }


        if (processType.equals("no")) {
            try {
                agreementDAO.delete(agreement.getAgreementId());
            } catch (DBException e) {
                System.out.println("Exception while agreementDAO.delete()  in ProcessAgreementController()");
                e.printStackTrace();
            }
        }

        return new MVCModel("/jsp/processAgreement.jsp", processType);
    }
}

