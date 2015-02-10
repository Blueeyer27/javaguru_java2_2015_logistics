package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinjvald on 07/02/15.
 */
public class CargoDAOImpl extends DAOImpl implements CargoDAO {
    @Override
    public void create(Cargo cargo, User user) throws DBException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO cargo VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, " +
                    "?, ?)", preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, user.getUserId());
            preparedStatement.setString(2, cargo.getVehicleType());
            preparedStatement.setDouble(3, cargo.getWeight());
            preparedStatement.setString(4, cargo.getLoadAddress());
            preparedStatement.setString(5, cargo.getUnloadAddress());
            preparedStatement.setDate(6, utilDateToSQL(cargo.getLoadDate()));
            preparedStatement.setDate(7, utilDateToSQL(cargo.getUnloadDate()));
            preparedStatement.setString(8, cargo.getStatus());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) cargo.setCargoId(resultSet.getLong(1));
        } catch (Throwable e) {
            System.out.println("Exception while execute CargoDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public Cargo getById(Long id) throws DBException {

        Connection connection = null;
        PreparedStatement preparedStatement;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM cargo WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
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
        } catch (Throwable e) {
            System.out.println("Exception while execute CargoDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void delete(Long id) throws DBException {

        Connection connection = null;
        PreparedStatement preparedStatement;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM cargo WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute CargoDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Cargo cargo) throws DBException {

        if (cargo == null) return;

        Connection connection = null;
        PreparedStatement preparedStatement;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("UPDATE cargo SET vehicle_type = ?, weight = ?," +
                    "load_address = ?, unload_address = ?, load_date = ?, unload_date = ?, status = ? WHERE id = ?");
            preparedStatement.setString(1, cargo.getVehicleType());
            preparedStatement.setDouble(2, cargo.getWeight());
            preparedStatement.setString(3, cargo.getLoadAddress());
            preparedStatement.setString(4, cargo.getUnloadAddress());
            preparedStatement.setDate(5, utilDateToSQL(cargo.getLoadDate()));
            preparedStatement.setDate(6, utilDateToSQL(cargo.getUnloadDate()));
            preparedStatement.setString(7, cargo.getStatus());
            preparedStatement.setLong(8, cargo.getCargoId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute CargoDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }


    }

    @Override
    public List<Cargo> getAll() throws DBException {

        List <Cargo> cargos = new ArrayList <Cargo>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM cargo");
            ResultSet resultSet = preparedStatement.executeQuery();
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
                cargos.add(cargo);
            }
            return cargos;
        }catch (Throwable e) {
            System.out.println("Exception while execute CargoDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public java.sql.Date utilDateToSQL(java.util.Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
}
