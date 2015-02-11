package lv.javaguru.java2.servlet.model;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.VehicleDAOImpl;
import lv.javaguru.java2.domain.Vehicle;

import java.util.List;

/**
 * Created by user on 11.02.2015.
 */
public class VehicleReg {

    private VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();

    public void vehicleCreate(List<String> parameteres) throws DBException {
        int userid = Integer.parseInt(parameteres.get(0));
        String name = parameteres.get(1);
        String type = parameteres.get(2);
        String plateNumber = parameteres.get(3);
        String trailerNumber = parameteres.get(4);
        String capacityString = parameteres.get(5);
        String status = parameteres.get(6);
         Double capacity = Double.parseDouble(capacityString);

        Vehicle vehicle = new Vehicle(userid, name, type, plateNumber, trailerNumber, capacity, status);
        //vehicle.setVehicleId(13);
       // vehicle.setUserId(11);
       //vehicle.setStatus("Pending");
        vehicleDAO.create(vehicle);

    }


}
