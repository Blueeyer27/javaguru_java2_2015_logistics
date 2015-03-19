package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Vehicle;

import java.util.List;

public interface VehicleDAO extends BaseDAO<Vehicle> {

    List<Vehicle> getByParameters(String vehicleType, Double capacityFrom, Double capacityTo) throws DBException;

    List<Vehicle> getByType(String vehicleType) throws DBException;

    List<Vehicle> getUserVehiclesByStatus(User user, String vehicleStatus) throws DBException;

}
