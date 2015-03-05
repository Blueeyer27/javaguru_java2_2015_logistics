package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Cargo;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component("HibernateCargoDAO")
@Transactional
public class CargoDAOImpl extends DAOImpl<Cargo> implements CargoDAO {

    @Override
    public List<Cargo> getByParameters(String vehicleType, Double weightFrom, Double weightTo,
                                       Date loadDateFrom, Date loadDateTo, Date unloadDateFrom, Date unloadDateTo) throws DBException {
        List cargos = getCurrentSession().createCriteria(Cargo.class)
                .add(Restrictions.eq("vehicle_type", vehicleType))
                .add(Restrictions.between("weight", weightFrom, weightTo))
                .add(Restrictions.between("load_date", loadDateFrom, loadDateTo))
                .add(Restrictions.between("unload_date", unloadDateFrom, unloadDateTo))
                .list();
        return cargos;
    }

    @Override
    public java.sql.Date utilDateToSQL(Date date) {
        return (java.sql.Date) getCurrentSession().get(Cargo.class, date);
    }

    @Override
    public Date stringToDate(String incomingDate) {
        return (Date) getCurrentSession().get(Cargo.class, incomingDate);
    }

    @Override
    public Date stringToDate2(String incomingDate, int i) {
        return (Date) getCurrentSession().get(Cargo.class, incomingDate);
    }



    @Override
    public Cargo getById(Long id) throws DBException {
        return (Cargo) getCurrentSession().get(Cargo.class, id);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Cargo cargo = (Cargo) session.get(Cargo.class, id);
        session.delete(cargo);
    }

    @Override
    public List<Cargo> getAll() throws DBException {
        return getCurrentSession().createCriteria(Cargo.class).list();
    }

}
