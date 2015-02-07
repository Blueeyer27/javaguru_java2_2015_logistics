package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.AgreementDAO;
import lv.javaguru.java2.domain.Agreement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by andre on 07.02.2015.
 */

public class AgreementDAOImpl extends DAOImpl implements AgreementDAO {


    @Override
    public void create(Agreement agreement) throws DBException {
        if (agreement == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into agreement values (default, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, agreement.getCargoId());
            preparedStatement.setLong(2, agreement.getVehicleId());
            preparedStatement.setString(3, agreement.getStatus());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                agreement.setAgreementId(resultSet.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute AgreementDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }






    @Override
    public Agreement getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from agreement where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Agreement agreement = null;
            if (resultSet.next()) {
                agreement = new Agreement();
                agreement.setAgreementId(resultSet.getLong("id"));
                agreement.setCargoId(resultSet.getLong("cargo_id"));
                agreement.setVehicleId(resultSet.getLong("vehicle_id"));
                agreement.setStatus(resultSet.getString("status"));
            }
            return agreement;
        } catch (Throwable e) {
            System.out.println("Exception while execute AgreementDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }


    @Override
    public List<Agreement> getAll() throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from agreement");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Agreement> agreements = new ArrayList<Agreement>();
            while (resultSet.next()) {
                Agreement agreement = new Agreement();
                agreement.setAgreementId(resultSet.getLong("id"));
                agreement.setCargoId(resultSet.getLong("cargo_id"));
                agreement.setVehicleId(resultSet.getLong("vehicle_id"));
                agreement.setStatus(resultSet.getString("status"));
                agreements.add(agreement);
            }
            return agreements;
        } catch (Throwable e) {
            System.out.println("Exception while execute AgreementDAOImpl.getAll()");
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
            PreparedStatement preparedStatement =
                    connection.prepareStatement("delete from agreement where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute AgreementDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }



    @Override
    public void update(Agreement agreement) throws DBException {
        if (agreement == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("update agreement set  cargo_id = ?, " +
                            "vehicle_id = ?, status = ? where id = ?");
            preparedStatement.setLong  (1, agreement.getCargoId());
            preparedStatement.setLong  (2, agreement.getVehicleId());
            preparedStatement.setString(3, agreement.getStatus());
            preparedStatement.setLong(4, agreement.getAgreementId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute  AgreementDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }


    }

}
