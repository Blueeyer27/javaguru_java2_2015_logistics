package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Cargo;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dinjvald on 07/02/15.
 */

@Component
public class CargoDAOImpl extends DAOImpl<Cargo> implements CargoDAO {
    private static final String TABLE_NAME = "cargo";
    private static final String UPDATE_STRING = " SET vehicle_type = ?, weight = ?," +
            "load_address = ?, unload_address = ?, load_date = ?, unload_date = ?, status = ? WHERE id = ?";
    private static final String INSERT_STRING = " values (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getUpdateString() {
        return UPDATE_STRING;
    }

    @Override
    public String getInsertString() {
        return INSERT_STRING;
    }

    @Override
    public void setInsertArguments(PreparedStatement preparedStatement, Cargo cargo) throws SQLException {
        preparedStatement.setLong(1, cargo.getUserId());
        preparedStatement.setString(2, cargo.getVehicleType());
        preparedStatement.setDouble(3, cargo.getWeight());
        preparedStatement.setString(4, cargo.getLoadAddress());
        preparedStatement.setString(5, cargo.getUnloadAddress());
        preparedStatement.setDate(6, utilDateToSQL(cargo.getLoadDate()));
        preparedStatement.setDate(7, utilDateToSQL(cargo.getUnloadDate()));
        preparedStatement.setString(8, cargo.getStatus());
    }

    @Override
    public void setId(Cargo cargo, long id) {
        cargo.setCargoId(id);
    }

    @Override
    protected Cargo createObject(ResultSet resultSet) throws SQLException {
        Cargo cargo = null;
        if (resultSet.next()) {
            cargo = new Cargo();
            cargo.setCargoId(resultSet.getLong("id"));
            cargo.setUserId(resultSet.getLong("user_id"));
            cargo.setVehicleType(resultSet.getString("vehicle_type"));
            cargo.setWeight(resultSet.getDouble("weight"));
            cargo.setLoadAddress(resultSet.getString("load_address"));
            cargo.setUnloadAddress(resultSet.getString("unload_address"));
            cargo.setLoadDate(resultSet.getDate("load_date"));
            cargo.setUnloadDate(resultSet.getDate("unload_date"));
            cargo.setStatus(resultSet.getString("status"));
        }
        return cargo;
    }

    @Override
    public void setUpdateArguments(PreparedStatement preparedStatement, Cargo cargo) throws SQLException {
        preparedStatement.setString(1, cargo.getVehicleType());
        preparedStatement.setDouble(2, cargo.getWeight());
        preparedStatement.setString(3, cargo.getLoadAddress());
        preparedStatement.setString(4, cargo.getUnloadAddress());
        preparedStatement.setDate(5, utilDateToSQL(cargo.getLoadDate()));
        preparedStatement.setDate(6, utilDateToSQL(cargo.getUnloadDate()));
        preparedStatement.setString(7, cargo.getStatus());
        preparedStatement.setLong(8, cargo.getCargoId());
    }

    @Override
    public void fillObjectsList(List<Cargo> objectsList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Cargo cargo = new Cargo();
            cargo.setCargoId(resultSet.getLong("id"));
            cargo.setUserId(resultSet.getLong("user_id"));
            cargo.setVehicleType(resultSet.getString("vehicle_type"));
            cargo.setWeight(resultSet.getDouble("weight"));
            cargo.setLoadAddress(resultSet.getString("load_address"));
            cargo.setUnloadAddress(resultSet.getString("unload_address"));
            cargo.setLoadDate(resultSet.getDate("load_date"));
            cargo.setUnloadDate(resultSet.getDate("unload_date"));
            cargo.setStatus(resultSet.getString("status"));
            objectsList.add(cargo);
        }
    }

    @Override
    public java.sql.Date utilDateToSQL(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    @Override
    public java.util.Date stringToDate(String incomingDate) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formater.parse(incomingDate);
        } catch (ParseException ex) {
            System.out.println("Invalid Date format in method stringToDate(). Should be dd/MM/yyyy");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public java.util.Date stringToDate2(String incomingDate, int i) {
        SimpleDateFormat formater = new SimpleDateFormat();

        switch (i) {
            case 1:
                formater = new SimpleDateFormat("dd/MM/yyyy");
                break;
            case 2:
                formater = new SimpleDateFormat("yyyy-MM-dd");
                break;
        }

        try {
            return formater.parse(incomingDate);
        } catch (ParseException ex) {
            System.out.println("Invalid or Empty Date format in class CargoDAOImpl -> method stringToDate().");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Cargo> getByParameters(String vehicleType, Double weightFrom, Double weightTo, java.util.Date loadDateFrom,
                                       java.util.Date loadDateTo, java.util.Date unloadDateFrom, java.util.Date unloadDateTo) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from " + getTableName() +
                    " where vehicle_type = ? and (weight between ? and ?) and " +
                    "(load_date between ? and ?) and (unload_date between ? and ?);");
            preparedStatement.setString(1, vehicleType);
            preparedStatement.setDouble(2, weightFrom);
            preparedStatement.setDouble(3, weightTo);
            preparedStatement.setDate(4, utilDateToSQL(loadDateFrom));
            preparedStatement.setDate(5, utilDateToSQL(loadDateTo));
            preparedStatement.setDate(6, utilDateToSQL(unloadDateFrom));
            preparedStatement.setDate(7, utilDateToSQL(unloadDateTo));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Cargo> objectsList = new ArrayList<Cargo>();
            fillObjectsList(objectsList, resultSet);
            return objectsList;
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list CargoDAOImpl.getByParameters()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }
}
