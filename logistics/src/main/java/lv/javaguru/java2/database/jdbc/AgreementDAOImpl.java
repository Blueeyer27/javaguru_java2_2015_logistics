package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.AgreementDAO;
import lv.javaguru.java2.domain.Agreement;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("JdbcAgreementDAO")
public class AgreementDAOImpl extends DAOImpl<Agreement> implements AgreementDAO {

    private static final String TABLE_NAME = "agreement";
    private static final String UPDATE_STRING = " set cargo_id = ?, " +
            "vehicle_id = ?, status = ? where id = ?";
    private static final String INSERT_STRING = " values (default, ?, ?, ?)";

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
    public void setInsertArguments(PreparedStatement preparedStatement, Agreement agreement) throws SQLException {
        preparedStatement.setLong(1, agreement.getCargoId());
        preparedStatement.setLong(2, agreement.getVehicleId());
        preparedStatement.setString(3, agreement.getStatus());
    }

    @Override
    public void setId(Agreement agreement, long id) {
        agreement.setAgreementId(id);
    }

    @Override
    protected Agreement createObject(ResultSet resultSet) throws SQLException {
        Agreement agreement = null;
        if (resultSet.next()) {
            agreement = new Agreement();
            agreement.setAgreementId(resultSet.getLong("id"));
            agreement.setCargoId(resultSet.getLong("cargo_id"));
            agreement.setVehicleId(resultSet.getLong("vehicle_id"));
            agreement.setStatus(resultSet.getString("status"));
        }
        return agreement;
    }

    @Override
    public void setUpdateArguments(PreparedStatement preparedStatement, Agreement agreement) throws SQLException {
        preparedStatement.setLong(1, agreement.getCargoId());
        preparedStatement.setLong(2, agreement.getVehicleId());
        preparedStatement.setString(3, agreement.getStatus());
        preparedStatement.setLong(4, agreement.getAgreementId());
    }

    @Override
    public void fillObjectsList(List<Agreement> objectsList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Agreement agreement = new Agreement();
            agreement.setAgreementId(resultSet.getLong("id"));
            agreement.setCargoId(resultSet.getLong("cargo_id"));
            agreement.setVehicleId(resultSet.getLong("vehicle_id"));
            agreement.setStatus(resultSet.getString("status"));
            objectsList.add(agreement);
        }
    }

}
