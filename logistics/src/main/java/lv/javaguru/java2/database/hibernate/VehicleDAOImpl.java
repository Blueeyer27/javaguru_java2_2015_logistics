package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Vehicle;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component("HibVehicleDAO")
@Transactional
public class VehicleDAOImpl extends DAOImpl<Vehicle> implements VehicleDAO {

    @Override
    public List<Vehicle> getByParameters(String vehicleType, Double capacityFrom, Double capacityTo) throws DBException {
        List vehicles = getCurrentSession().createCriteria(Vehicle.class)
                .add(Restrictions.eq("type", vehicleType))
                .add(Restrictions.between("capacity", capacityFrom, capacityTo))
                .list();
        return vehicles;
    }

    @Override
    public List<Vehicle> getByType(String vehicleType) throws DBException {
        List vehicles = getCurrentSession().createCriteria(Vehicle.class)
                .add(Restrictions.eq("type", vehicleType))
                .list();
        return vehicles;
    }

    @Override
    public List<Vehicle> getUserVehiclesByStatus(User user, String vehicleStatus) throws DBException {
        List vehicles = getCurrentSession().createCriteria(Vehicle.class)
                .add(Restrictions.eq("status", vehicleStatus))
                .add(Restrictions.eq("user", user))
                .list();
        return vehicles;
    }
}
