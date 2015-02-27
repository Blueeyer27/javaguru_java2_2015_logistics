package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.domain.Company;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CompanyDAOImpl extends DAOImpl<Company> implements CompanyDAO {

    private static final String TABLE_NAME = "company";
    private static final String UPDATE_STRING =  " set  name = ?, " +
            "reg_number = ?, reg_address = ?, actual_address = ?, " +
            "bank = ?, iban = ?, country = ?, type = ? where id = ?";
    private static final String INSERT_STRING = " values (default, ?, ?, ?, ?, ?, ?, ?, ?)";

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
    public void setInsertArguments(PreparedStatement preparedStatement, Company company) throws SQLException {
        preparedStatement.setString(1, company.getName());
        preparedStatement.setString(2, company.getRegNumber());
        preparedStatement.setString(3, company.getRegAddress());
        preparedStatement.setString(4, company.getActualAddress());
        preparedStatement.setString(5, company.getBank());
        preparedStatement.setString(6, company.getIban());
        preparedStatement.setLong(7, company.getCountryId());
        preparedStatement.setString(8, company.getType());
    }

    @Override
    public void setId(Company company, long id) {
        company.setCompanyId(id);
    }

    @Override
    protected Company createObject(ResultSet resultSet) throws SQLException {
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
            company.setCountryId(resultSet.getLong("country"));
            company.setType(resultSet.getString("type"));
        }
        return company;
    }

    @Override
    public void setUpdateArguments(PreparedStatement preparedStatement, Company company) throws SQLException {
        preparedStatement.setString(1, company.getName());
        preparedStatement.setString(2, company.getRegNumber());
        preparedStatement.setString(3, company.getRegAddress());
        preparedStatement.setString(4, company.getActualAddress());
        preparedStatement.setString(5, company.getBank());
        preparedStatement.setString(6, company.getIban());
        preparedStatement.setLong(7, company.getCountryId());
        preparedStatement.setString(8, company.getType());
        preparedStatement.setLong(9, company.getCompanyId());
    }

    @Override
    public void fillObjectsList(List<Company> objectsList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Company company = new Company();
            company.setCompanyId(resultSet.getLong("id"));
            company.setName(resultSet.getString("name"));
            company.setRegNumber(resultSet.getString("reg_number"));
            company.setRegAddress(resultSet.getString("reg_address"));
            company.setActualAddress(resultSet.getString("actual_address"));
            company.setBank(resultSet.getString("bank"));
            company.setIban(resultSet.getString("iban"));
            company.setCountryId(resultSet.getLong("country"));
            company.setType(resultSet.getString("type"));
            objectsList.add(company);
        }
    }
}
