package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.domain.Agreement;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Vehicle;

public class DAOImplTest {

    protected Company createCompany(String name, String regNumber, String regAddress,
                                  String actualAddress, String bank, String iban,
                                  String country, String type) {
        Company company = new Company();
        company.setName(name);
        company.setRegNumber(regNumber);
        company.setRegAddress(regAddress);
        company.setActualAddress(actualAddress);
        company.setBank(bank);
        company.setIban(iban);
        company.setCountry(country);
        company.setType(type);
        return company;
    }

    protected User createUser(String login, String password, String firstName, String lastName,
                            String eMail, String phoneNumber, Long companyId) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEMail(eMail);
        user.setPhoneNumber(phoneNumber);
        user.setCompanyId(companyId);
        return user;
    }

    protected Vehicle createVehicle(long userId, String name, String plateNumber, String type, String trailerNumber, double capacity, String status){
        Vehicle vehicle = new Vehicle();
        vehicle.setUserId(userId);
        vehicle.setName(name);
        vehicle.setplateNumber(plateNumber);
        vehicle.setType(type);
        vehicle.settrailerNumber(trailerNumber);
        vehicle.setCapacity(capacity);
        vehicle.setStatus(status);
        return vehicle;
    }

    protected Agreement createAgreement(Long cargoID, Long vehicleID, String status) {
        Agreement agreement = new Agreement();
        agreement.setCargoId(cargoID);
        agreement.setVehicleId(vehicleID);
        agreement.setStatus(status);
        return agreement;
    }

}
