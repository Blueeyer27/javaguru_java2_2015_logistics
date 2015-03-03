package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.domain.Vehicle;

import java.util.List;

public class VehicleDAOImpl extends DAOImpl<Vehicle> implements VehicleDAO {

    @Override
    public List<Vehicle> getByParameters(String vehicleType, Double capacityFrom, Double capacityTo) throws DBException {
        return null;
    }
}
