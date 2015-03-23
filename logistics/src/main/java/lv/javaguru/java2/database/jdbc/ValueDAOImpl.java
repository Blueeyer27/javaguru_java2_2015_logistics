package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ValueDAO;
import lv.javaguru.java2.domain.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("JdbcValueDAO")
public class ValueDAOImpl extends DAOImpl<Value> implements ValueDAO{

    @Override
    public String lookupValue(String type, String value) throws DBException {
        return null; // implemented in hibernate
    }

    @Override
    public List<String> getLovStringValuesByType(String type) throws DBException {
        return null; // implemented in hibernate
    }

    private static final String TABLE_NAME = "value";
    private static final String UPDATE_STRING = " set type = ?, value = ? where id = ?";
    private static final String INSERT_STRING = " values (default,?,?)";

    @Override
    public List<Value> getLovByType(String type) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from " + getTableName() +
                            " where type = ? order by value;"); // TEMPORARY: ALPHABETICAL ORDER
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Value> values = new ArrayList<Value>();
            fillObjectsList(values, resultSet);
            return values;
        } catch (Throwable e) {
            System.out.println("Exception while getting values in ValueDAOImpl.getLovByType()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void setInsertArguments(PreparedStatement preparedStatement, Value value) throws SQLException {
        preparedStatement.setString(1, value.getType());
        preparedStatement.setString(2, value.getValue());
    }

    @Override
    public void setId(Value value, long id) {
        value.setValueId(id);
    }

    @Override
    protected Value createObject(ResultSet resultSet) throws SQLException {
        Value value = null;
        if (resultSet.next()) {
            value = new Value();
            value.setValueId(resultSet.getLong("ID"));
            value.setType(resultSet.getString("type"));
            value.setValue(resultSet.getString("value"));

        }
        return value;
    }

    @Override
    public void setUpdateArguments(PreparedStatement preparedStatement, Value value) throws SQLException {
        preparedStatement.setString(1, value.getType());
        preparedStatement.setString(2, value.getValue());
        preparedStatement.setLong(3, value.getValueId());

    }

    @Override
    public void fillObjectsList(List<Value> objectsList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Value value = new Value();
            value.setValueId(resultSet.getLong("id"));
            value.setType(resultSet.getString("type"));
            value.setValue(resultSet.getString("value"));
            objectsList.add(value);
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
