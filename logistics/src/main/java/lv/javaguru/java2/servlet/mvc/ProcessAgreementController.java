package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.AgreementDAO;
import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.domain.Agreement;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProcessAgreementController {

    @Autowired
    @Qualifier("HibAgreementDAO")
    private AgreementDAO agreementDAO;

    @Autowired
    @Qualifier("HibernateCargoDAO")
    private CargoDAO cargoDAO;

    @Autowired
    @Qualifier("HibVehicleDAO")
    private VehicleDAO vehicleDAO;

    public static final String ACCEPT = "accept";
    public static final String CANCEL = "cancel";

    @RequestMapping(value = "processAgreement", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        Long agreementId = Long.parseLong(request.getParameter("agreementId"));
        Long cargoId = Long.parseLong(request.getParameter("cargoId"));
        Long vehicleId = Long.parseLong(request.getParameter("vehicleId"));
        String processType = request.getParameter("processType");

        Agreement agreement = null;
        Cargo cargo = null;
        Vehicle vehicle = null;

        try {
            agreement = agreementDAO.getById(agreementId);
            cargo = cargoDAO.getById(cargoId);
            vehicle = vehicleDAO.getById(vehicleId);
        } catch (DBException e) {
            System.out.println("Exception while agreementDAO.getById()  in ProcessAgreementController()");
            e.printStackTrace();
        }

        updateObjectsStatuses(agreement, cargo, vehicle, processType);

        List<Agreement> agreementList = getAgreementListFromDB();

        model.setViewName("agreementOverview");
        model.addObject("model",agreementList);
        return model;
    }

    private void updateObjectsStatuses(Agreement agreement, Cargo cargo,
                                       Vehicle vehicle, String processType) {
        if(processType.equals(ACCEPT)) {
            acceptAgreement(agreement, cargo, vehicle);
        } else if(processType.equals(CANCEL)) {
            cancelAgreement(agreement);
        } else
            System.out.println("Unknown Action in ProcessAgreementController.updateObjectsStatuses()");
    }

    protected List<Agreement> getAgreementListFromDB() {
        List<Agreement> agreementList = null;
        try {
            agreementList = agreementDAO.getAll();
        } catch (DBException e) {
            System.out.println("Exception while getting agreementList ProcessAgreementController");
            e.printStackTrace();
        }
        return agreementList;
    }

    private void acceptAgreement(Agreement agreement, Cargo cargo, Vehicle vehicle) {
        agreement.setStatus("Accepted");
        if (cargo !=null && vehicle != null) {
            cargo.setStatus("Processed");
            vehicle.setStatus("In Use");
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

    private void cancelAgreement(Agreement agreement) {
        try {
            agreementDAO.delete(agreement.getAgreementId());
        } catch (DBException e) {
            System.out.println("Exception while agreementDAO.delete()  in ProcessAgreementController()");
            e.printStackTrace();
        }
    }
}

