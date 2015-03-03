package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Cargo;

import java.util.Date;
import java.util.List;

public class CargoDAOImpl extends DAOImpl<Cargo> implements CargoDAO {

    @Override
    public List<Cargo> getByParameters(String vehicleType, Double weightFrom, Double weightTo, Date loadDateFrom, Date loadDateTo, Date unloadDateFrom, Date unloadDateTo) throws DBException {
        return null;
    }

    @Override
    public java.sql.Date utilDateToSQL(Date date) {
        return null;
    }

    @Override
    public Date stringToDate(String incomingDate) {
        return null;
    }

    @Override
    public Date stringToDate2(String incomingDate, int i) {
        return null;
    }
}
