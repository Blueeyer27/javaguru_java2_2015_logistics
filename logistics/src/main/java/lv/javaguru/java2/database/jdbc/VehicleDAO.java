package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Vehicle;

import java.util.List;

/**
 * Created by user on 06.02.2015.
 */
public interface VehicleDAO {

    void create(Vehicle vehicle, User user) throws DBException;

    Vehicle getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(Vehicle vehicle) throws DBException;

    List<Vehicle> getAll() throws DBException;

}
