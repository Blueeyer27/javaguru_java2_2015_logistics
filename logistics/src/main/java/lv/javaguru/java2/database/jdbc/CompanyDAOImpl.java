package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.domain.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl extends DAOImpl implements CompanyDAO {

    @Override
    public void create(Company company) throws DBException {
        if (company == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into company values (default, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getRegNumber());
            preparedStatement.setString(3, company.getRegAddress());
            preparedStatement.setString(4, company.getActualAddress());
            preparedStatement.setString(5, company.getBank());
            preparedStatement.setString(6, company.getIban());
            preparedStatement.setString(7, company.getCountry());
            preparedStatement.setString(8, company.getType());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                company.setCompanyId(resultSet.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute CompanyDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Company getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from company where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Company company = null;
            if (resultSet.next()) {
                company = new Company();
                company.setCompanyId(resultSet.getLong("id"));
                company.setName(resultSet.getString("name"));
                company.setRegNumber(resultSet.getString("reg_number"));
                company.setRegAddress(resultSet.getString("reg_address"));
                company.setActualAddress(resultSet.getString("actual_address"));
                company.setBank(resultSet.getString("bank"));
                company.setIban(resultSet.getString("iban"));
                company.setCountry(resultSet.getString("country"));
                company.setType(resultSet.getString("type"));
            }
            return company;
        } catch (Throwable e) {
            System.out.println("Exception while execute CompanyDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Company> getAll() throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from company");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Company> companies = new ArrayList<Company>();
            while (resultSet.next()) {
                Company company = new Company();
                company.setCompanyId(resultSet.getLong("id"));
                company.setName(resultSet.getString("name"));
                company.setRegNumber(resultSet.getString("reg_number"));
                company.setRegAddress(resultSet.getString("reg_address"));
                company.setActualAddress(resultSet.getString("actual_address"));
                company.setBank(resultSet.getString("bank"));
                company.setIban(resultSet.getString("iban"));
                company.setCountry(resultSet.getString("country"));
                company.setType(resultSet.getString("type"));
                companies.add(company);
            }
            return companies;
        } catch (Throwable e) {
            System.out.println("Exception while execute CompanyDAOImpl.getAll()");
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
                    connection.prepareStatement("delete from company where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute CompanyDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Company company) throws DBException {
        if (company == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("update company set  name = ?, " +
                            "reg_number = ?, reg_address = ?, actual_address = ?, " +
                            "bank = ?, iban = ?, country = ?, type = ? where id = ?");
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getRegNumber());
            preparedStatement.setString(3, company.getRegAddress());
            preparedStatement.setString(4, company.getActualAddress());
            preparedStatement.setString(5, company.getBank());
            preparedStatement.setString(6, company.getIban());
            preparedStatement.setString(7, company.getCountry());
            preparedStatement.setString(8, company.getType());
            preparedStatement.setLong(9, company.getCompanyId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute CompanyDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

}