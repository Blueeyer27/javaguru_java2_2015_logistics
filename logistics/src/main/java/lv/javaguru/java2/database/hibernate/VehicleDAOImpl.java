package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.VehicleDAO;
import lv.javaguru.java2.domain.Vehicle;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component("HibVehicleDAO")
@Transactional
public class VehicleDAOImpl extends DAOImpl<Vehicle> implements VehicleDAO {

    @Override
    public Vehicle getById(Long id) throws DBException {
        return (Vehicle) getCurrentSession().get(Vehicle.class, id);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Vehicle vehicle = (Vehicle) session.get(Vehicle.class, id);
        session.delete(vehicle);
    }

    @Override
    public List<Vehicle> getAll() throws DBException {
        return getCurrentSession().createCriteria(Vehicle.class).list();
    }

    @Override
    public List<Vehicle> getByParameters(String vehicleType, Double capacityFrom, Double capacityTo) throws DBException {
        List vehicles = getCurrentSession().createCriteria(Vehicle.class)
                .add(Restrictions.eq("type", vehicleType))
                .add(Restrictions.between("capacity", capacityFrom, capacityTo))
                .list();
        return vehicles;
    }
}
