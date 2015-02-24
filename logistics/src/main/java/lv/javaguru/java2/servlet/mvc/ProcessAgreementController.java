package lv.javaguru.java2.servlet.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.AgreementDAOImpl;
import lv.javaguru.java2.database.jdbc.CargoDAOImpl;
import lv.javaguru.java2.database.jdbc.VehicleDAOImpl;
import lv.javaguru.java2.domain.Agreement;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.Vehicle;

@Component
@URL(value="/processAgreement")
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

        Cargo cargo = null;
        CargoDAOImpl cargoDAO = new CargoDAOImpl();

        Vehicle vehicle = null;
        VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();


        try {
            agreement = agreementDAO.getById(agreementId);
            cargo = cargoDAO.getById(cargoId);
            vehicle = vehicleDAO.getById(vehicleId);
        } catch (DBException e) {
            System.out.println("Exception while agreementDAO.getById()  in ProcessAgreementController()");
            e.printStackTrace();
        }

        //here we need(or must) to update status also for Cargo and Vehicle items
        if (processType.equals("accept")) {

            agreement.setStatus(status);
            if (cargo !=null && vehicle != null) {
                cargo.setStatus(status);
                vehicle.setStatus(status);
            }
            try {
                agreementDAO.update(agreement);
                cargoDAO.update(cargo);
                vehicleDAO.update(vehicle);
            } catch (DBException e) {
                System.out.println("Exception while agreementDAO.update() or cargoDAO.update() or vehicleDAO.update()  in ProcessAgreementController()");
                e.printStackTrace();
            }
        }


        if (processType.equals("cancel")) {
            try {
                agreementDAO.delete(agreement.getAgreementId());
            } catch (DBException e) {
                System.out.println("Exception while agreementDAO.delete()  in ProcessAgreementController()");
                e.printStackTrace();
            }
        }


        List<Agreement> agreementList = new ArrayList<Agreement>();

        try {
            agreementList = agreementDAO.getAll();
        } catch (DBException e) {
            System.out.println("Exception while getting agreementList ProcessAgreementController");
            e.printStackTrace();
        }


        MVCModel model = new MVCModel("/jsp/agreementOverview.jsp", agreementList);
        return model;



    }
}

