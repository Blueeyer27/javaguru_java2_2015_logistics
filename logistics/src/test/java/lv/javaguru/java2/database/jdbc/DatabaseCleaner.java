package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viktor on 01/07/2014.
 */
public class DatabaseCleaner extends DAOImpl {

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("user");
        tableNames.add("vehicle");
        tableNames.add("agreement");
        tableNames.add("cargo");
        tableNames.add("company");
        return tableNames;
    }

    public void cleanDatabase() throws DBException {
        for(String tableName : getTableNames()) {
            Connection connection = getConnection();
            try {
                connection = getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("delete from " + tableName);
                preparedStatement.executeUpdate();
            } catch (Throwable e) {
                System.out.println("Exception while execute cleanDatabase() for table " + tableName);
                e.printStackTrace();
                throw new DBException(e);
            } finally {
                closeConnection(connection);
            }
        }
    }

    @Override
    public void setInsertArguments(PreparedStatement preparedStatement, Object type) throws SQLException {

    }

    @Override
    public void setId(Object type, long id) {

    }

    @Override
    protected Object createObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public void setUpdateArguments(PreparedStatement preparedStatement, Object type) throws SQLException {

    }

    @Override
    public void fillObjectsList(List objectsList, ResultSet resultSet) throws SQLException {

    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String getUpdateString() {
        return null;
    }

    @Override
    public String getInsertString() {
        return null;
    }
}
