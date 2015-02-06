package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 06.02.2015.
 */
public class VehicleDAOImpl extends DAOImpl implements VehicleDAO {
    @Override
    public void create(Vehicle vehicle, User user) throws DBException {

        if (vehicle == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into VEHICLE values (default, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, user.getUserId());
            preparedStatement.setString(2, vehicle.getName());
            preparedStatement.setString(3, vehicle.getplateNumber());
            preparedStatement.setString(4, vehicle.getType());
            preparedStatement.setString(5, vehicle.gettrailerNumber());
            preparedStatement.setDouble(6, vehicle.getCapacity());
            preparedStatement.setString(7, vehicle.getStatus());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                vehicle.setVehicleId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public Vehicle getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from VEHICLE where ID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
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
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from VEHICLE where ID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Vehicle vehicle) throws DBException {
        if (vehicle == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update VEHICLE set NAME = ?, PLATE_NUMBER = ?, TYPE = ?, TRAILER_NUMBER = ?, CAPACITY = ?, STATUS = ? " +
                            "where ID = ?");
            preparedStatement.setString(1, vehicle.getName());
            preparedStatement.setString(2, vehicle.getType());
            preparedStatement.setString(3, vehicle.getplateNumber());
            preparedStatement.setString(4, vehicle.gettrailerNumber());
            preparedStatement.setDouble(5, vehicle.getCapacity());
            preparedStatement.setString(6, vehicle.getStatus());
            preparedStatement.setLong(7, vehicle.getVehicleId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }


    public List<Vehicle> getAll() throws DBException {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from VEHICLE");

            ResultSet resultSet = preparedStatement.executeQuery();
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
                vehicles.add(vehicle);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list UserDAOImpl.getList()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return vehicles;
    }
}
