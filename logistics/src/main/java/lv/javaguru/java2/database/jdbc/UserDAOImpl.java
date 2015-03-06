package lv.javaguru.java2.database.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lv.javaguru.java2.database.DBException;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;

@Component
public class UserDAOImpl extends DAOImpl<User> implements UserDAO {

    @Override
    public User getByLogin(String login) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from " + getTableName() +" where login = ?");
            preparedStatement.setString(1, login);
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

    private static final String TABLE_NAME = "user";
    private static final String UPDATE_STRING = " set login = ?, password = ? , " +
            "first_name = ? , last_name = ? , e_mail = ? , " +
            "phone_number = ? , company_id = ? where id = ?";
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
    public void setInsertArguments(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getEMail());
        preparedStatement.setString(6, user.getPhoneNumber());
        preparedStatement.setLong(7, user.getCompanyId());
    }

    @Override
    public void setId(User user, long id) {
        user.setUserId(id);
    }

    @Override
    protected User createObject(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setUserId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEMail(resultSet.getString("e_mail"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
            user.setCompanyId(resultSet.getLong("company_id"));
        }
        return user;
    }

    @Override
    public void setUpdateArguments(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getEMail());
        preparedStatement.setString(6, user.getPhoneNumber());
        preparedStatement.setLong(7, user.getCompanyId());
        preparedStatement.setLong(8, user.getUserId());
    }

    @Override
    public void fillObjectsList(List<User> objectsList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEMail(resultSet.getString("e_mail"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
            user.setCompanyId(resultSet.getLong("company_id"));
            objectsList.add(user);
        }
    }

}
