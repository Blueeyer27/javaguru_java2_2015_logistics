package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.BaseDAO;
import lv.javaguru.java2.database.DBException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class DAOImpl<T> implements BaseDAO<T> {

    private static final String DB_CONFIG_FILE = "database.properties";

    private String dbUrl = null;
    private String userName = null;
    private String password = null;

    public DAOImpl() {
        registerJDBCDriver();
        initDatabaseConnectionProperties();
    }

    private void registerJDBCDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Exception while registering JDBC driver!");
            e.printStackTrace();
        }
    }

    private void initDatabaseConnectionProperties() {
        Properties properties = new Properties();
        try {
            properties.load(DAOImpl.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE));

            dbUrl = properties.getProperty("dbUrl");
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
        } catch (IOException e){
            System.out.println("Exception while reading JDBC configuration from file = " + DB_CONFIG_FILE);
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws DBException {
        try{
            return DriverManager.getConnection(dbUrl, userName, password);
        } catch (SQLException e) {
            System.out.println("Exception while getting connection to database");
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    protected void closeConnection(Connection connection) throws DBException {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception while closing connection to database");
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public void create(T type) throws DBException {
        if (type == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into "+ getTableName() + getInsertString(),
                            PreparedStatement.RETURN_GENERATED_KEYS);

            setInsertArguments(preparedStatement, type);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                setId(type, rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute DAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public abstract void setInsertArguments(PreparedStatement preparedStatement, T type) throws SQLException;

    public abstract void setId(T type, long id);

    @Override
    public T getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from " + getTableName() +" where id = ?");
            preparedStatement.setLong(1, id);

            System.out.println(preparedStatement.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            return createObject(resultSet);
        } catch (Throwable e) {
            System.out.println("Exception while execute DAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    protected abstract T createObject(ResultSet resultSet) throws SQLException;

    @Override
    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from " + getTableName() + "  where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute DAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(T type) throws DBException {
        if (type == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update " + getTableName() + getUpdateString());

            setUpdateArguments(preparedStatement, type);


            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute DAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public abstract void setUpdateArguments(PreparedStatement preparedStatement, T type) throws SQLException;

    @Override
    public List<T> getAll() throws DBException {

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from " + getTableName());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> objectsList = new ArrayList<T>();
            fillObjectsList(objectsList, resultSet);
            return objectsList;
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list DAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public abstract void fillObjectsList(List<T> objectsList, ResultSet resultSet) throws SQLException;

    public abstract String getTableName();

    public abstract String getUpdateString();

    public abstract String getInsertString();

}
