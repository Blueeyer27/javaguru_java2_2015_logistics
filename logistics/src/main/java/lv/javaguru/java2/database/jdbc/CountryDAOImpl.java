package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CountryDAO;
import lv.javaguru.java2.domain.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 26.02.2015.
 */
public class CountryDAOImpl extends DAOImpl<Country> implements CountryDAO{

    private static final String TABLE_NAME = "countries";
    private static final String UPDATE_STRING = " set NAME = ? where ID = ?";
    private static final String INSERT_STRING = " values (default,?)";

    @Override
    public void setInsertArguments(PreparedStatement preparedStatement, Country country) throws SQLException {
      //preparedStatement.setLong(1, country.getCountryId());
        preparedStatement.setString(1, country.getName());
    }

    @Override
    public void setId(Country country, long id) {
        country.setCountryId(id);
    }

    @Override
    protected Country createObject(ResultSet resultSet) throws SQLException {
        Country country = null;
        if (resultSet.next()) {
            country = new Country();
            country.setCountryId(resultSet.getLong("ID"));
            country.setName(resultSet.getString("Name"));

        }
        return country;
    }

    @Override
    public void setUpdateArguments(PreparedStatement preparedStatement, Country country) throws SQLException {
        preparedStatement.setString(1, country.getName());
        preparedStatement.setLong(2, country.getCountryId());
    }

    @Override
    public void fillObjectsList(List<Country> objectsList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Country country = new Country();
            country.setCountryId(resultSet.getLong("ID"));
            country.setName(resultSet.getString("Name"));
            objectsList.add(country);
        }
    }

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
}
