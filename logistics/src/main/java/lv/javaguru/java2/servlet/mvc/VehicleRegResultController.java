package lv.javaguru.java2.servlet.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import lv.javaguru.java2.servlet.model.URL;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Vehicle;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by user on 17.02.2015.
 */
@Component
public class VehicleRegResultController {

    public static final String PENDING = "PENDING";

    @Autowired
    @Qualifier("HibVehicleDAO")
    private VehicleDAO vehicleDAO;

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @RequestMapping(value = "vehicleRegResult", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String plateNumber = request.getParameter("platenumber");
        String trailerNumber = request.getParameter("trailernumber");
        Double capacity = Double.parseDouble(request.getParameter("capacity"));
        Long userid = Long.parseLong(request.getParameter("userid"));
        String status = PENDING;

        User user = null;
        try {
            user = userDAO.getById(userid);
        } catch (DBException e) {
            System.out.println("Exception while getting user from DB CargoRegResult");
            e.printStackTrace();
        }

        long vehicleId = createVehicleInDatabase(new Vehicle(user, name, type, plateNumber, trailerNumber, capacity, status));

        Vehicle vehicleFromDb = getCreatedVehicleFromDatabase(vehicleId);

        model.setViewName("vehicleregresult");
        model.addObject("model",vehicleFromDb);

        return model;
    }

    protected long createVehicleInDatabase(Vehicle vehicle){
        long vehicleId;
        try {
            vehicleDAO.create(vehicle);
        } catch (DBException e) {
            e.printStackTrace();
        }
        vehicleId = vehicle.getVehicleId();
        return vehicleId;
    }

    protected Vehicle getCreatedVehicleFromDatabase(long vehicleId){
        Vehicle vehicleFromDb = null;
        try {
           vehicleFromDb = vehicleDAO.getById(vehicleId);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return vehicleFromDb;
    }

    protected List<Vehicle> getAllVehiclesFromDb(){
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        try {
            vehicleList = vehicleDAO.getAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return vehicleList;
    }
}
