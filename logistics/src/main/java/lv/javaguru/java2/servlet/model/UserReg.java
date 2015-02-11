package lv.javaguru.java2.servlet.model;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;


import java.util.List;

/**
 * Created by user on 11.02.2015.
 */
public class UserReg {

    private UserDAOImpl userDAO = new UserDAOImpl();

    public void userCreate(List<String> parameteres) throws DBException {
        String login = parameteres.get(0);
        String password = parameteres.get(1);
        String firstname = parameteres.get(2);
        String lastname = parameteres.get(3);
        String email = parameteres.get(4);
        String phone = parameteres.get(5);
        int companyid = Integer.parseInt(parameteres.get(6));

        User userNew = new User(login, password, firstname, lastname, email, phone, companyid);

        //Vehicle vehicle = new Vehicle(userid, name, type, plateNumber, trailerNumber, capacity, status);
        //vehicle.setVehicleId(13);
       // vehicle.setUserId(11);
       //vehicle.setStatus("Pending");
        userDAO.create(userNew);

    }


}
