package lv.javaguru.java2.servlet.model;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.*;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Vehicle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 12.02.2015.
 */
public class RegistrationMethods {

    //методы для регистрации

    private VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();

    private UserDAOImpl userDAO = new UserDAOImpl();

    private CompanyDAOImpl companyDAO = new CompanyDAOImpl();

    private CargoDAOImpl cargoDAO = new CargoDAOImpl();

    //private AgreementDAOImpl agreementDAO = new AgreementDAOImpl();


    public void vehicleCreate(List<String> parameteres) throws DBException {
        int userid = Integer.parseInt(parameteres.get(0));
        String name = parameteres.get(1);
        String type = parameteres.get(2);
        String plateNumber = parameteres.get(3);
        String trailerNumber = parameteres.get(4);
        String capacityString = parameteres.get(5);
        String status = parameteres.get(6);
        Double capacity = Double.parseDouble(capacityString);

        Vehicle vehicle = new Vehicle(userid, name, type, plateNumber, trailerNumber, capacity, status);
        vehicleDAO.create(vehicle);

    }

    public void userCreate(List<String> parameteres) throws DBException {
        String login = parameteres.get(0);
        String password = parameteres.get(1);
        String firstname = parameteres.get(2);
        String lastname = parameteres.get(3);
        String email = parameteres.get(4);
        String phone = parameteres.get(5);
        int companyid = Integer.parseInt(parameteres.get(6));

        User userNew = new User(login, password, firstname, lastname, email, phone, companyid);
        userDAO.create(userNew);

    }

    public void companyCreate(List<String> parameteres) throws DBException {
        String name = parameteres.get(0);
        String regNumber = parameteres.get(1);
        String regAddress = parameteres.get(2);
        String actualAddress = parameteres.get(3);
        String bank = parameteres.get(4);
        String iban = parameteres.get(5);
        String country = parameteres.get(6);
        String type = parameteres.get(7);

        Company company = new Company(name, regNumber, regAddress, actualAddress, bank, iban, country, type);
        companyDAO.create(company);
    }


    public void cargoCreate(List<String> parameteres) throws DBException {

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int userid = Integer.parseInt(parameteres.get(0));
        String type = parameteres.get(1);
        Double weight = Double.parseDouble(parameteres.get(2));
        String loadaddress = parameteres.get(3);
        String unloadaddress = parameteres.get(4);


        Date loaddate = cargoDAO.stringToDate2(parameteres.get(5));
        Date unloaddate = cargoDAO.stringToDate2(parameteres.get(6));


        String status = parameteres.get(7);

        Cargo cargo = new Cargo(userid, type, weight, loadaddress, unloadaddress, loaddate, unloaddate, status);
        //Cargo cargo = new Cargo(123, "fruits", 200.0, "saharova6", "saharova9", loaddate, loaddate, "pending");
        cargoDAO.create(cargo);

    }



}
