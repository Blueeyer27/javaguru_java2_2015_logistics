package lv.javaguru.java2.domain;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CompanyDAOImpl;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition = "int(11)")
    private long userId;

    @Column(name = "login", columnDefinition = "char(32)")
    private String login;

    @Column(name = "password", columnDefinition = "char(100)")
    private String password;

    @Column(name = "first_name", columnDefinition = "char(32)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "char(32)")
    private String lastName;

    @Column(name = "e_mail", columnDefinition = "char(32)")
    private String eMail;

    @Column(name = "phone_number", columnDefinition = "char(20)")
    private String phoneNumber;

    @Transient
    private long companyId;

    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    public User() {

    }

    public User(String login, String password, String firstName, String lastName, String eMail, String phoneNumber, Company company) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.company = company;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getUserCompanyType() {
        return getUserCompany().getType();
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    private Company getUserCompany() {
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        Company company = null;
        try {
            company = companyDAO.getById(getCompanyId());
        } catch (DBException e) {
            e.printStackTrace();
        }
        return company;
    }
}
