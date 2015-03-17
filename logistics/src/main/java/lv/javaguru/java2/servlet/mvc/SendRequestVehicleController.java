package lv.javaguru.java2.servlet.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.servlet.model.URL;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CargoDAOImpl;
import lv.javaguru.java2.database.jdbc.VehicleDAOImpl;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SendRequestVehicleController {

    @RequestMapping(value = "sendRequestVehicle", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        Long vehicleId = Long.parseLong(request.getParameter("id"));
        Map<String, Object> modelHashMap = new HashMap<String, Object> ();
        VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
        CargoDAOImpl cargoDAO = new CargoDAOImpl();
        Vehicle vehicle = null;
        List<Cargo> cargoList = new ArrayList<Cargo>();
        Cargo cargo =null;

        try {
            vehicle = vehicleDAO.getById(vehicleId);
        } catch (DBException e) {
            System.out.println("Exception while getting vehicleDAO.getById(vehicleId) SendRequestVehicleController");
            e.printStackTrace();
        }



        try {
//            cargoList = cargoDAO.getAll();
            cargoList = cargoDAO.getByParameters(vehicle.getType(), cargo.MIN_WEIGHT,vehicle.getCapacity(),
                    cargoDAO.stringToDate2(cargo.MIN_DATE, 2), cargoDAO.stringToDate2(cargo.MAX_DATE, 2),
                    cargoDAO.stringToDate2(cargo.MIN_DATE, 2), cargoDAO.stringToDate2(cargo.MAX_DATE, 2));
        } catch (DBException e) {
            System.out.println("Exception while getting cargoDAO.getByParameters() SendRequestVehicleController");
            e.printStackTrace();
        }


        modelHashMap.put("vehicle", vehicle);
        modelHashMap.put("cargoList", cargoList);

        model.setViewName("sendRequestVehicle");
        model.addObject("model",modelHashMap);

        return model;
    }
}
