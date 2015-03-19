package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.User;

import java.sql.Date;
import java.util.List;

public interface CargoDAO extends BaseDAO<Cargo> {

    List<Cargo> getByParameters(String vehicleType, Double weightFrom, Double weightTo, java.util.Date loadDateFrom,
                                java.util.Date loadDateTo, java.util.Date unloadDateFrom, java.util.Date unloadDateTo) throws DBException;

    List<Cargo> getUserCargoesByStatus(User user, String cargoStatus) throws DBException;

    Date utilDateToSQL(java.util.Date date);

    java.util.Date stringToDate(String incomingDate);

    java.util.Date stringToDate2(String incomingDate, int i);
}
