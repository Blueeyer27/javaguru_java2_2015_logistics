package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Cargo;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component("HibernateCargoDAO")
@Transactional
public class CargoDAOImpl extends DAOImpl<Cargo> implements CargoDAO {

    @Override
    public List<Cargo> getByParameters(String vehicleType, Double weightFrom, Double weightTo,
                                       Date loadDateFrom, Date loadDateTo, Date unloadDateFrom, Date unloadDateTo) throws DBException {
        List cargos = getCurrentSession().createCriteria(Cargo.class)
                .add(Restrictions.eq("vehicleType", vehicleType))
                .add(Restrictions.between("weight", weightFrom, weightTo))
                .add(Restrictions.between("loadDate", loadDateFrom, loadDateTo))
                .add(Restrictions.between("unloadDate", unloadDateFrom, unloadDateTo))
                .list();
        return cargos;
    }

    @Override
    public java.sql.Date utilDateToSQL(java.util.Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    @Override
    public java.util.Date stringToDate(String incomingDate) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date date = formater.parse(incomingDate);
            return date;
        } catch (ParseException ex) {
            System.out.println("Invalid Date format in method stringToDate(). Should be dd/MM/yyyy");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public java.util.Date stringToDate2(String incomingDate, int i) {
        SimpleDateFormat formater = new SimpleDateFormat();

        switch (i) {
            case 1:
                formater = new SimpleDateFormat("dd/MM/yyyy");
                break;
            case 2:
                formater = new SimpleDateFormat("yyyy-MM-dd");
                break;
        }

        try {
            java.util.Date date = formater.parse(incomingDate);
            return date;
        } catch (ParseException ex) {
            System.out.println("Invalid or Empty Date format in class CargoDAOImpl -> method stringToDate().");
            ex.printStackTrace();
            return null;
        }
    }
}
