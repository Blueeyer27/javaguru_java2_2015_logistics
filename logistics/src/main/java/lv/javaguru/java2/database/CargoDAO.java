package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Cargo;

import java.sql.Date;
import java.util.List;

public interface CargoDAO extends BaseDAO<Cargo> {

    List<Cargo> getByParameters(String vehicleType, Double weightFrom, Double weightTo, java.util.Date loadDateFrom,
                                java.util.Date loadDateTo, java.util.Date unloadDateFrom, java.util.Date unloadDateTo) throws DBException;
}
