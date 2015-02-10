package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.domain.Vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class VehicleDAOImpl extends DAOImpl<Vehicle> implements VehicleDAO {
    private static final String TABLE_NAME = "vehicle";
    private static final String UPDATE_STRING = " set NAME = ?, PLATE_NUMBER = ?, " +
            "TYPE = ?, TRAILER_NUMBER = ?, CAPACITY = ?, STATUS = ? where ID = ?";
    private static final String INSERT_STRING = " values (default, ?, ?, ?, ?, ?, ?, ?)";

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
    public void setInsertArguments(PreparedStatement preparedStatement, Vehicle vehicle) throws SQLException {
        preparedStatement.setLong(1, vehicle.getUserId());
        preparedStatement.setString(2, vehicle.getName());
        preparedStatement.setString(3, vehicle.getplateNumber());
        preparedStatement.setString(4, vehicle.getType());
        preparedStatement.setString(5, vehicle.gettrailerNumber());
        preparedStatement.setDouble(6, vehicle.getCapacity());
        preparedStatement.setString(7, vehicle.getStatus());
    }

    @Override
    public void setId(Vehicle vehicle, long id) {
        vehicle.setVehicleId(id);
    }

    @Override
    protected Vehicle createObject(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = null;
        if (resultSet.next()) {
            vehicle = new Vehicle();
            vehicle.setVehicleId(resultSet.getLong("ID"));
            vehicle.setUserId(resultSet.getLong("User_ID"));
            vehicle.setName(resultSet.getString("Name"));
            vehicle.setplateNumber(resultSet.getString("type"));
            vehicle.setType(resultSet.getString("plate_number"));
            vehicle.settrailerNumber(resultSet.getString("trailer_Number"));
            vehicle.setCapacity(resultSet.getDouble("capacity"));
            vehicle.setStatus(resultSet.getString("status"));
        }
        return vehicle;
    }

    @Override
    public void setUpdateArguments(PreparedStatement preparedStatement, Vehicle vehicle) throws SQLException {
        preparedStatement.setString(1, vehicle.getName());
        preparedStatement.setString(2, vehicle.getType());
        preparedStatement.setString(3, vehicle.getplateNumber());
        preparedStatement.setString(4, vehicle.gettrailerNumber());
        preparedStatement.setDouble(5, vehicle.getCapacity());
        preparedStatement.setString(6, vehicle.getStatus());
        preparedStatement.setLong(7, vehicle.getVehicleId());
    }

    @Override
    public void fillObjectsList(List<Vehicle> objectsList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleId(resultSet.getLong("ID"));
            vehicle.setUserId(resultSet.getLong("User_ID"));
            vehicle.setName(resultSet.getString("Name"));
            vehicle.setplateNumber(resultSet.getString("type"));
            vehicle.setType(resultSet.getString("plate_number"));
            vehicle.settrailerNumber(resultSet.getString("trailer_Number"));
            vehicle.setCapacity(resultSet.getDouble("capacity"));
            vehicle.setStatus(resultSet.getString("status"));
            objectsList.add(vehicle);
        }
    }

}
