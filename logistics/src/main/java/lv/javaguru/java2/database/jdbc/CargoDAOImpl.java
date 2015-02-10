package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.domain.Cargo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dinjvald on 07/02/15.
 */
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

    public java.sql.Date utilDateToSQL(java.util.Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
}
